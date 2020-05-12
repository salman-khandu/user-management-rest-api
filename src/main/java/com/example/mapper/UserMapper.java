package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {

	@Mapping(source = "roles", target = "rolesDTOs")
	UserDTO toDTO(final User user);
}
