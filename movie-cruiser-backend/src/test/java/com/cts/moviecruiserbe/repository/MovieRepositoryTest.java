/**
 * 
 */
package com.cts.moviecruiserbe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.moviecruiserbe.domain.Movie;

/**
 * @author ubuntu
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private transient MovieRepository movieRepository;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Test
	public void testSaveMovie() throws Exception {
		Movie movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movieRepository.save(movie);
		final Movie savedMovie = movieRepository.getOne(1);
		assertThat(savedMovie.getId()).isEqualTo(1);
	}

	@Test
	public void testUpdateMovie() throws Exception {
		Movie movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movieRepository.save(movie);
		Optional<Movie> optional = movieRepository.findById(movie.getId());
		if (optional.isPresent()) {
			movie.setMovieName("The Dark Knight Returns");
			movieRepository.save(movie);
		}
		final Movie savedMovie = movieRepository.getOne(1);
		assertThat(savedMovie.getMovieName()).isEqualTo("The Dark Knight Returns");
	}

	@Test
	public void testDeleteMovieById() throws Exception{
		Movie movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movieRepository.save(movie);
		movieRepository.deleteById(movie.getId());
		Optional<Movie> optional = movieRepository.findById(movie.getId());
		assertThat(!optional.isPresent());
		
	}
	
	@Test
	public void testGetMoviebyId() throws Exception{
		Movie movie = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movieRepository.save(movie);
		final Movie savedMovie = movieRepository.getOne(1);
		assertThat(savedMovie.getId()).isEqualTo(1);
	}
	
	@Test
	public void testGetAllMovies() throws Exception{
		Movie movie1 = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		Movie movie2 = new Movie(2, "m1234", "The Dark Knight Returns", "http://imdb.com/1234", new Date(), "Must watch before you die!", "The joker returns", "user1234");
		movieRepository.save(movie1);
		movieRepository.save(movie2);
		
		List<Movie> movies = movieRepository.findAll();
		assertThat(movies.size()).isEqualTo(2);
	}
	
	@Test
	public void testMoviesByUserId() throws Exception{
		Movie movie1 = new Movie(1, "m123", "The Dark Knight", "http://imdb.com/123", new Date(), "Must watch before you die!", "The joker returns", "user123");
		Movie movie2 = new Movie(2, "m1234", "The Dark Knight Returns", "http://imdb.com/1234", new Date(), "Must watch before you die!", "The joker returns", "user123");
		movieRepository.save(movie1);
		movieRepository.save(movie2);
		List<Movie> movies = movieRepository.findByUserId("user123");
		assertThat(movies.size()).isEqualTo(2);
	}
}
