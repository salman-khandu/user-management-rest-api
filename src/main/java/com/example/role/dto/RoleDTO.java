package com.example.role.dto;

import javax.validation.constraints.NotBlank;

public class RoleDTO {

	@NotBlank(message = "{role.required}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
