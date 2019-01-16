/**
 * 
 */
package com.cts.movie.cruiser.auth.service.services;

import com.cts.movie.cruiser.auth.service.exceptions.UserAlreadyExistsException;
import com.cts.movie.cruiser.auth.service.exceptions.UserNotFoundException;
import com.cts.movie.cruiser.auth.service.model.User;

/**
 * @author ubuntu
 *
 */
public interface UserService {

	/**
	 * Saves a new user.
	 * 
	 * @param user
	 * @return
	 * @throws UserAlreadyExistsException
	 * @throws UserNotFoundException
	 */
	public boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException;

	/**
	 * Validates a user.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

}
