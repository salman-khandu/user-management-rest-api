package com.example.base.security.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SignUpRequest {

	@NotBlank(message = "{email.required}")
	@Email(message = "{email.invalid}")
	private String email;

	@NotBlank(message = "{password.required}")
	private String password;

	@NotNull
	private List<@NotBlank String> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
