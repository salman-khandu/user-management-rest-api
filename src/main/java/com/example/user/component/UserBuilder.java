/**
 * 
 */
package com.example.user.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.user.model.User;

/**
 * @author Salman.Khandu
 *
 */

@Component
@Scope("prototype")
public class UserBuilder {

	private Long id;
	private String name;
	private String email;
	private String phoneNumber;

	public UserBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public User build() {
		return new User(this.id, this.name, this.email, this.phoneNumber);
	}

}
