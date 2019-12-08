/**
 * 
 */
package com.example.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.user.dto.UserDTO;
import com.example.user.repository.UserRepository;
import com.example.user.validation.constraint.EmailAlreadyUsed;

/**
 * @author Salman.Khandu
 *
 */
public class EmailAlreadyUsedValidator implements ConstraintValidator<EmailAlreadyUsed, UserDTO> {

	private UserRepository userRepository;

	public EmailAlreadyUsedValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(UserDTO userDto, ConstraintValidatorContext context) {

		if (userDto.getId() != null) {
			return !userRepository
					.findOneByEmailIgnoreCaseAndIdNot(userDto.getEmail().trim().toLowerCase(), userDto.getId())
					.isPresent();
		} else {
			return !userRepository.findOneByEmailIgnoreCase(userDto.getEmail().trim().toLowerCase()).isPresent();
		}

	}

}
