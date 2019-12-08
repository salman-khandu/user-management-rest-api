package com.example.user.service;

import com.example.base.dto.DataGridResponseDTO;
import com.example.base.dto.DataGridSearchCriteria;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.exception.UserNotFoundException;

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
	 */
	public void create(UserDTO userDTO);

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

	public DataGridResponseDTO<?> list(DataGridSearchCriteria<UserSearchCriteriaDTO> userSearchCriteriaDTO);
}
