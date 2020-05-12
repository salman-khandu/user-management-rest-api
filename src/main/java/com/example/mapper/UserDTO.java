package com.example.mapper;

import java.util.List;

public class UserDTO {

	private String name;
	private String age;
	private List<RoleDTO> rolesDTOs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<RoleDTO> getRolesDTOs() {
		return rolesDTOs;
	}

	public void setRolesDTOs(List<RoleDTO> rolesDTOs) {
		this.rolesDTOs = rolesDTOs;
	}

}
