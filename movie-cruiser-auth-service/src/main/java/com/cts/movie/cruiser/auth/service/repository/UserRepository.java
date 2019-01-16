/**
 * 
 */
package com.cts.movie.cruiser.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.movie.cruiser.auth.service.model.User;

/**
 * @author ubuntu
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUserIdAndPassword(String userId, String password);
	
}
