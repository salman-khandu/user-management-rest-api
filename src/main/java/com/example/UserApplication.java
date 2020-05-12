package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mapper.Role;
import com.example.mapper.User;
import com.example.mapper.UserDTO;
import com.example.mapper.UserMapper;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Autowired
	private UserMapper userMapper;

	@PostConstruct
	public void test() {

		User user = new User();
		user.setAge("28");
		user.setName("Alex");
	    List<Role> roles = new ArrayList<>();
	    Role role = new Role();
	    role.setId("1");
	    role.setName("ROLE_ADMIN");
		roles.add(role);
		user.setRoles(roles);
		
		UserDTO userDTO = this.userMapper.toDTO(user);
		System.out.println(user.getAge());
		System.out.println(user.getName());
		userDTO.getRolesDTOs().forEach(roleDTO-> System.out.println(roleDTO.getId()+ "" + roleDTO.getName()));
	}

}
