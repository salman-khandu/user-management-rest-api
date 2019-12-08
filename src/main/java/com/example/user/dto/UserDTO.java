/**
 * 
 */
package com.example.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.user.validation.constraint.EmailAlreadyUsed;
import com.example.user.validation.group.PrimaryValidationGroup;

/**
 * @author Salman.Khandu
 *
 */

@EmailAlreadyUsed(groups = PrimaryValidationGroup.class)
public class UserDTO {

	private Long id;

	@NotBlank(message = "{name.required}")
	private String name;

	@NotBlank(message = "{email.required}")
	@Email(message = "{email.invalid}")
	private String email;

	@NotBlank(message = "{phoneNumber.required}")
	private String phoneNumber;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
