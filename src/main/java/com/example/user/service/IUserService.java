package com.example.user.service;

import javax.management.MalformedObjectNameException;

import com.example.base.dto.DataGridResponseDTO;
import com.example.base.dto.DataGridSearchCriteria;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.exception.UserNotFoundException;
import com.example.user.model.User;
import com.example.user.projection.NameOnly;

/**
 * 
 * @author Salman.Khandu
 *
 */
public interface IUserService {

	/**
	 * Create New User.
	 * 
	 * @param userDTO
	 * @throws MalformedObjectNameException
	 */
	public void create(UserDTO userDTO) throws MalformedObjectNameException;

	/**
	 * Get User by Id.
	 * 
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserResponseDTO get(Long id) throws UserNotFoundException;

	/**
	 * Update User
	 * 
	 * @param id
	 * @param userDTO
	 * @throws UserNotFoundException
	 */
	public void update(Long id, UserDTO userDTO) throws UserNotFoundException;

	/**
	 * Delete user
	 * 
	 * @param id
	 * @throws UserNotFoundException
	 */
	public void delete(Long id) throws UserNotFoundException;

	public User getUser(Long id);

	public DataGridResponseDTO<?> list(DataGridSearchCriteria<UserSearchCriteriaDTO> userSearchCriteriaDTO);

	public NameOnly getProjectionUser(Long id) throws MalformedObjectNameException;

	public void deleteCollectionTest(Long id);
}
