package com.example.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.base.dto.DataGridResponseDTO;
import com.example.base.dto.DataGridSearchCriteria;
import com.example.base.dto.PageInfo;
import com.example.user.component.UserBuilder;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.exception.UserNotFoundException;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.IUserService;
import com.example.user.specification.UserSpecification;

/**
 * Service layer of {@link User}
 * 
 * @author Salman.Khandu
 *
 */

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private ApplicationContext applicationContext;

	public UserService(UserRepository userRepository, ApplicationContext applicationContext) {
		this.userRepository = userRepository;
		this.applicationContext = applicationContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(UserDTO userDTO) {
		UserBuilder userBuilder = this.applicationContext.getBean(UserBuilder.class);
		User user = userBuilder.withName(userDTO.getName()).withEmail(userDTO.getEmail())
				.withPhoneNumber(userDTO.getPhoneNumber()).build();
		this.userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	@Transactional(readOnly = true)
	public UserResponseDTO get(Long id) throws UserNotFoundException {

		return this.userRepository.findById(id)
				.map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getPhoneNumber()))
				.orElseThrow(() -> new UserNotFoundException("User not exist"));
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	@Transactional
	public void update(Long id, UserDTO userDTO) throws UserNotFoundException {
		User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not exist"));
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		this.userRepository.save(user);
	}

	@Override
	public void delete(Long id) throws UserNotFoundException {
		try {
			this.userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new UserNotFoundException("User Not found");
		}

	}

	@Override
	public DataGridResponseDTO<?> list(DataGridSearchCriteria<UserSearchCriteriaDTO> userSearchCriteriaDTO) {
		PageInfo pageInfo = userSearchCriteriaDTO.getPageInfo();
		PageRequest pageable = PageRequest.of(pageInfo.getCurrentPageNumber(), pageInfo.getPageSize(),
				pageInfo.getSort().buildSpringSortObject());
		Specification<User> specification = UserSpecification.build(userSearchCriteriaDTO.getSearchCriteria());
		return buildDataGridResponseDTO(pageInfo, this.userRepository.findAll(specification, pageable));
	}

	private DataGridResponseDTO<?> buildDataGridResponseDTO(PageInfo pageInfo, Page<User> userPage) {
		DataGridResponseDTO<List<UserResponseDTO>> productGridResponseDTO = new DataGridResponseDTO<>();
		if (!CollectionUtils.isEmpty(userPage.getContent())) {
			final List<UserResponseDTO> userResponseList = userPage.getContent().stream()
					.map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getPhoneNumber()))
					.collect(Collectors.toList());
			productGridResponseDTO.setData(userResponseList);
			PageInfo paginationDTO = new PageInfo(userPage.getTotalPages(), userPage.getTotalElements(),
					pageInfo.getCurrentPageNumber(), pageInfo.getPageSize());
			productGridResponseDTO.setPaginationDTO(paginationDTO);
		}
		return productGridResponseDTO;
	}

}
