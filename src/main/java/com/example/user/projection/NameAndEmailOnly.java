package com.example.user.projection;

public class NameAndEmailOnly {
	private String email;
	private String name;

	public NameAndEmailOnly(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}


	public String getName() {
		return name;
	}


}