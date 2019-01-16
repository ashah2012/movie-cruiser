package com.cts.movie.cruiser.auth.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.movie.cruiser.auth.service.model.User;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class UserRepositoryTest {

	@Autowired
	private transient UserRepository userRepo;
	


	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User("John1", "John", "Doe", "password", new Date());
	}

	@Test
	public void testRegisteredUserSuccess() {
		userRepo.save(user);
		Optional<User> optionalUser = userRepo.findById(user.getUserId());
		assertThat(optionalUser.get().getFirstName().equals(user.getFirstName()));
	}

}
