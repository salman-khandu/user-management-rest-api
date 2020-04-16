/**
 * 
 */
package com.example.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.user.model.User;
import com.example.user.projection.SimpleUserClass;

/**
 * Represent DAO layer of {@link User}
 * 
 * @author Salman.Khandu
 *
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	Optional<User> findOneByEmailIgnoreCaseAndIdNot(String userName, Long id);

	Optional<User> findOneByEmailIgnoreCase(String userName);

	Optional<SimpleUserClass> findSimpleUserByName(String usename);

	<T> T findByName(String usename, Class<T> type);

	@Query("SELECT COUNT(1) AS totalUser FROM User AS u WHERE u.name = ?1")
	IUserCount countTotalUserByName(String username);

}
