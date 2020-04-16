package com.example.user.controller;

import javax.management.MalformedObjectNameException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base.HikariPoolMXBeanUtils;
import com.example.base.dto.DataGridSearchCriteria;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.exception.UserNotFoundException;
import com.example.user.model.User;
import com.example.user.projection.NameOnly;
import com.example.user.service.IUserService;
import com.example.user.validation.group.sequence.ValidationSequence;
import com.zaxxer.hikari.pool.HikariPool;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private IUserService userService;

	@Autowired
	Validator validator;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Validated(ValidationSequence.class) @RequestBody UserDTO userDTO) throws MalformedObjectNameException {
		HikariPoolMXBeanUtils.logHikariPoolStates();
		this.userService.create(userDTO);
		HikariPoolMXBeanUtils.logHikariPoolStates();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> get(@PathVariable Long id) throws UserNotFoundException {
		User user = this.userService.getUser(id);
		System.out.println(user.getRoles());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("/summary/{id}")
	public ResponseEntity<?> userSummary(@PathVariable Long id) throws UserNotFoundException {
		NameOnly user = this.userService.getProjectionUser(id);
		System.out.println(user.getName());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @Validated(ValidationSequence.class) @RequestBody UserDTO userDTO)
			throws UserNotFoundException {
		this.userService.update(id, userDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws UserNotFoundException {
		this.userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Responsible for getting users from system with pagination.
	 * 
	 * @return
	 */
	@PostMapping("/list")
	public ResponseEntity<?> list(@RequestBody DataGridSearchCriteria<UserSearchCriteriaDTO> userSearchCriteriaDTO) {
		return new ResponseEntity<>(this.userService.list(userSearchCriteriaDTO), HttpStatus.OK);
	}
}
