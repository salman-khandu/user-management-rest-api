package com.example.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.MalformedObjectNameException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.example.base.HikariPoolMXBeanUtils;
import com.example.base.dto.DataGridResponseDTO;
import com.example.base.dto.DataGridSearchCriteria;
import com.example.base.dto.PageInfo;
import com.example.base.security.dto.SignUpRequest;
import com.example.role.model.Role;
import com.example.role.repository.RoleRepository;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.exception.UserNotFoundException;
import com.example.user.model.User;
import com.example.user.projection.NameAndEmailOnly;
import com.example.user.projection.NameOnly;
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
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public UserService(UserRepository userRepository, ApplicationContext applicationContext,
			PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.applicationContext = applicationContext;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws MalformedObjectNameException
	 */
	@Override
	@Transactional
	public void create(UserDTO userDTO) throws MalformedObjectNameException {
		User user = map(userDTO);
		this.userRepository.save(user);
		HikariPoolMXBeanUtils.logHikariPoolStates();
	}

	private User map(UserDTO userDTO) {
		return null;
//		UserBuilder userBuilder = this.applicationContext.getBean(UserBuilder.class);
//		return userBuilder.withName(userDTO.getName()).withEmail(userDTO.getEmail())
//				.withPhoneNumber(userDTO.getPhoneNumber()).role(userDTO.getRoleDTOs())
//				.interests(userDTO.getUserInterestDTOs()).build();
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
	@Transactional
	public void delete(Long id) throws UserNotFoundException {
		try {
			// this.userRepository.deleteById(id);
			Optional<User> user = this.userRepository.findById(id);
			User user1 = user.get();
			Role role1 = user1.getRoles().iterator().next();
			user1.getRoles().remove(role1);
			this.userRepository.save(user.get());
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

	@Override
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		System.out.println(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		User user = this.userRepository.findById(id).get();
		org.hibernate.engine.spi.PersistenceContext persistenceContext = getHibernatePersistenceContext();
		EntityEntry entityEntry = persistenceContext.getEntry(user);
		Assert.isNull(entityEntry.getLoadedState(), "Loaded State null in cases of read only transaction");
		return user;
	}

	@Override
	@Transactional
	public NameOnly getProjectionUser(Long id) throws MalformedObjectNameException {
		User user = this.userRepository.findById(id).get();
//		org.hibernate.engine.spi.PersistenceContext persistenceContext = getHibernatePersistenceContext();
//		EntityEntry entityEntry = persistenceContext.getEntry(user);
//		Assert.notNull(entityEntry.getLoadedState(),
//				"Loaded State exist in cases of default transaction mode (read/write mode)");

		user.setEmail("test11");
		// this.userRepository.findByUserNameNative("salman");
		this.userRepository.findSimpleUserByName("salman").get();

		System.out.println("Test");
		HikariPoolMXBeanUtils.logHikariPoolStates();
		this.userRepository.countTotalUserByName("salman").getTotalUser();
		System.out.println("Test");
		HikariPoolMXBeanUtils.logHikariPoolStates();
		this.userRepository.findByName("salman", NameAndEmailOnly.class);
		System.out.println("Test");
		HikariPoolMXBeanUtils.logHikariPoolStates();
		return this.userRepository.findByName("salman", NameOnly.class);
	}

	private org.hibernate.engine.spi.PersistenceContext getHibernatePersistenceContext() {
		SharedSessionContractImplementor session = entityManager.unwrap(SharedSessionContractImplementor.class);
		return session.getPersistenceContext();
	}

	@Override
	@Transactional
	public void deleteCollectionTest(Long id) {
		User user = this.userRepository.findById(id).get();
		System.out.println("Set Role Removing");
		user.getRoles().remove(user.getRoles().iterator().next());
	}

	@Override
	@Transactional
	public void signUp(SignUpRequest signUpRequest) {
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(this.passwordEncoder.encode(signUpRequest.getPassword()));
		Optional<Set<Role>> roles = this.roleRepository.findByNameIn(signUpRequest.getRoles());
		if (roles.isPresent()) {
			roles.get().forEach(user::addRole);
			user.setRoles(roles.get());
		}
		this.userRepository.save(user);
	}
}
