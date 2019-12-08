/**
 * 
 */
package com.example.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.user.model.User;

/**
 * Represent DAO layer of {@link User}
 * 
 * @author Salman.Khandu
 *
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	Optional<User> findOneByEmailIgnoreCaseAndIdNot(String userName, Long id);

	Optional<User> findOneByEmailIgnoreCase(String userName);
}
