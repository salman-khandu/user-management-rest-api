package com.example.base.email;

public enum EmailTemplateEnum {

	GREETING("emailTempalteTQG.html");

	EmailTemplateEnum(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
}
