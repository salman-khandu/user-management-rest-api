package com.example.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleDTO toDTO(final Role role);
}
