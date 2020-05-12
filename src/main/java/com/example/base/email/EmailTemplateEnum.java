package com.example.base.email;

public enum EmailTemplateEnum {

	GREETING("email");

	EmailTemplateEnum(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
}
