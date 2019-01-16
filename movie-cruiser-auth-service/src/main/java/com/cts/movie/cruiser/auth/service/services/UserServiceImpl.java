/**
 * 
 */
package com.cts.movie.cruiser.auth.service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.movie.cruiser.auth.service.exceptions.UserAlreadyExistsException;
import com.cts.movie.cruiser.auth.service.exceptions.UserNotFoundException;
import com.cts.movie.cruiser.auth.service.model.User;
import com.cts.movie.cruiser.auth.service.repository.UserRepository;

/**
 * @author ubuntu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	
	private final UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if(optionalUser.isPresent()) {
			throw new UserAlreadyExistsException("User with Id : " + user.getUserId() + " already exists.");
		}
		userRepository.save(user);
		return true;
	}

	
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(null == user) {
			throw new UserNotFoundException("UserId and Password mismatch.");
		}
		return user;
	}

}
