package com.cts.movie.cruiser.auth.service.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.movie.cruiser.auth.service.model.User;
import com.cts.movie.cruiser.auth.service.services.SecurityTokenGenerator;
import com.cts.movie.cruiser.auth.service.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@Autowired
	private transient MockMvc mockMVC;

	@MockBean
	private UserService userService;

	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;

	private User user;

	@InjectMocks
	UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("John1", "John", "Doe", "password", new Date());
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(userService.saveUser(user)).thenReturn(true);
		mockMVC.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonify(user))).andExpect(status().isCreated())
				.andDo(print());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testLoginUser() throws Exception {

		String userId = "John1";
		String password = "password";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMVC.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(jsonify(user))).andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
	}

	private String jsonify(Object obj) {
		String json = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
