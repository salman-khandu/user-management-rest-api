package com.example.role.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.role.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Set<Role>> findByNameIn(List<String> names);

}
 