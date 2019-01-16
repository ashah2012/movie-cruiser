package com.cts.movie.cruiser.auth.service.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.movie.cruiser.auth.service.exceptions.UserAlreadyExistsException;
import com.cts.movie.cruiser.auth.service.exceptions.UserNotFoundException;
import com.cts.movie.cruiser.auth.service.model.User;
import com.cts.movie.cruiser.auth.service.repository.UserRepository;
import com.cts.movie.cruiser.auth.service.services.UserServiceImpl;

public class UserServiceTest {

	@Mock
	private UserRepository userRepo;

	private User user;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private Optional<User> options;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("John1", "John", "Doe", "password", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserNotFoundException, UserAlreadyExistsException {

		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Can Register user", true, flag);
		verify(userRepo, times(1)).save(user);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserNotFoundException, UserAlreadyExistsException {

		when(userRepo.findById(user.getUserId())).thenReturn(options);
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);

	}

	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("John1", user.getUserId());
		verify(userRepo, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepo.findById("son1")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
