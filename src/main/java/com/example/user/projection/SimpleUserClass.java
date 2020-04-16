package com.example.user.projection;

public class SimpleUserClass {

	private String name;
	private String email;
	private String phoneNumber;

	public SimpleUserClass(String name, String email, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}