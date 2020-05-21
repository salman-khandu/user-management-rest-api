package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mapper.Role;
import com.example.mapper.RoleDTO;
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

		List<User> userList = new ArrayList<>();
		User user = new User();
		user.setAge("28");
		user.setName("Zem");
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setId("1");
		role.setName("SUPER_ADMIN");
		roles.add(role);

		role = new Role();
		role.setId("2");
		role.setName("USER");
		roles.add(role);

		role = new Role();
		role.setId("3");
		role.setName("ADMIN");
		roles.add(role);
		user.setRoles(roles);

		userList.add(user);
		
		
		user = new User();
		user.setAge("27");
		user.setName("Cux");
		roles = new ArrayList<>();
		role = new Role();
		role.setId("1");
		role.setName("STORE");
		roles.add(role);

		role = new Role();
		role.setId("1");
		role.setName("MANAGER");
		roles.add(role);
		
		role = new Role();
		role.setId("1");
		role.setName("REGION_MANAGER");
		roles.add(role);
		
		role = new Role();
		role.setId("1");
		role.setName("ADMIN");
		roles.add(role);
		
		user.setRoles(roles);
		userList.add(user);

		user = new User();
		user.setAge("25");
		user.setName("Alex");
		user.setRoles(roles);
		userList.add(user);

		//mapping entity to dto with sorting
		List<UserDTO> sortedList = userList.stream().map(u1 -> this.userMapper.toDTO(u1))
				.sorted(Comparator.comparing(UserDTO::getName))
				.map(this::sortRole).collect(Collectors.toList());
		
		//print result to check
		sortedList.stream().forEach(u1 ->{
			String roleString = u1.getRolesDTOs().stream().map(rrr-> rrr.getName()).collect(Collectors.joining(","));
			System.out.println(u1.getName() +" ==> " + roleString + "\n\n");
		});
	}

	private UserDTO sortRole(UserDTO userDTO) {
		userDTO.setRolesDTOs(userDTO.getRolesDTOs().stream().sorted(Comparator.comparing(RoleDTO::getName))
				.collect(Collectors.toList()));
		return userDTO;

	}

}
