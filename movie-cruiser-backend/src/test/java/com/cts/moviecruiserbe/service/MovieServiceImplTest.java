/**
 * 
 */
package com.cts.moviecruiserbe.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.moviecruiserbe.domain.Movie;
import com.cts.moviecruiserbe.exceptions.MovieAlreadyExistsException;
import com.cts.moviecruiserbe.exceptions.MovieNotFoundException;
import com.cts.moviecruiserbe.repository.MovieRepository;

/**
 * @author ubuntu
 *
 */
public class MovieServiceImplTest {

	@Mock
	private transient MovieRepository movieRepo;

	@InjectMocks
	private transient MovieServiceImpl movieService;

	private transient Movie movie;

	transient Optional<Movie> options;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!",
				"The joker returns", "user123");
		options = Optional.of(movie);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepo.save(movie)).thenReturn(movie);
		Movie saveMovie = movieService.saveMovie(movie);
		assertEquals("Movie Save Failed", saveMovie.getMovieId(), movie.getMovieId());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		Movie saveMovie = movieService.saveMovie(movie);
		assertEquals("Movie updating failed", saveMovie, movie);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setUserComments("not so good movie!");
		Movie movie1 = movieService.updateMovie(movie);
		assertEquals("Updating Movie failed", "not so good movie!", movie1.getUserComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testDeleteMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		Boolean flag = movieService.deleteMovieById(movie.getId());
		assertTrue("Deleting movie failed", flag);
		verify(movieRepo, times(1)).findById(movie.getId());
		verify(movieRepo, times(1)).delete(movie);
	}

	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		Movie foundMovie = movieService.getMovieById(1);
		assertEquals("Fetching movie by id failed", foundMovie, movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testgetAllMovies() {
		List<Movie> movies = new ArrayList<>(1);
		movies.add(movie);
		when(movieRepo.findAll()).thenReturn(movies);
		List<Movie> allMovies = movieService.getAllMovies();
		assertEquals(allMovies.size(), movies.size());
		verify(movieRepo, times(1)).findAll();
	}

	@Test
	public void testGetMyMovies() {
		List<Movie> movies = new ArrayList<>(1);
		movies.add(movie);
		when(movieRepo.findByUserId("user123")).thenReturn(movies);
		List<Movie> myMovies = movieService.getMyMovie("user123");
		assertEquals(myMovies.size(), movies.size());
		verify(movieRepo, times(1)).findByUserId("user123");
	}

}
