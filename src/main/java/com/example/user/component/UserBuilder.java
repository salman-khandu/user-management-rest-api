/**
 * 
 */
package com.example.user.component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.role.dto.RoleDTO;
import com.example.role.model.Role;
import com.example.user.dto.UserInterestDTO;
import com.example.user.model.User;
import com.example.user.model.UserInterest;

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
	private Set<Role> roles;
	private List<UserInterest> interests;

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
		return new User(this.id, this.name, this.email, this.phoneNumber, this.roles, this.interests);
	}

	public UserBuilder role(Set<RoleDTO> roleDTOs) {

		this.roles = roleDTOs.stream().map(roleDTO -> new Role(null, roleDTO.getName())).collect(Collectors.toSet());

		return this;
	}

	public UserBuilder interests(List<UserInterestDTO> interestdtos) {
		this.interests = interestdtos.stream().map(interestDTO -> new UserInterest(null, interestDTO.getInterest()))
				.collect(Collectors.toList());
		return this;
	}

}
