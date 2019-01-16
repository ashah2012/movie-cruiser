package com.cts.moviecruiserbe.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.moviecruiserbe.domain.Movie;
import com.cts.moviecruiserbe.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mvc;

	@MockBean
	private transient MovieService movieService;

	private transient Movie movie;

	private static List<Movie> movies;

	@Before
	public void setUp() {
		movies = new ArrayList<>();
		movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movies.add(movie);
		movie = new Movie(2, "m1234", "The Dark Knight Returns", "http://imdb.com/1234", new Date(), "Must watch before you die!", "The joker returns", "user1234");
		movies.add(movie);
		//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk
	}

	@Test
	public void testSaveMovie() throws JsonProcessingException, Exception {
		final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk";
		when(movieService.saveMovie(movie)).thenReturn(movie);
		mvc.perform(post("/api/movie").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonify(movie))).andExpect(status().isCreated());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testUpdateMovie() throws JsonProcessingException, Exception {
		final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk";
		movie.setUserComments("not so good!");
		when(movieService.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/movie/{id}",1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonify(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testDeleteMovieById() throws Exception {
		final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk";
		when(movieService.deleteMovieById(1)).thenReturn(Boolean.TRUE);
		mvc.perform(delete("/api/movie/{id}",1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testGetMovieById() throws Exception {
		final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk";
		when(movieService.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/movie/{id}",1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(movieService, times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testGetAllMovies() throws Exception {
		final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiaWF0IjoxNTQzNzM0MDA1fQ.da-X57uzSp62alKHfVGqp2Ap6co_LoOgGtFpVvSnohk";
		when(movieService.getAllMovies()).thenReturn(null);
		mvc.perform(get("/api/movie").header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(movieService, times(1)).getAllMovies();
		verifyNoMoreInteractions(movieService);
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
