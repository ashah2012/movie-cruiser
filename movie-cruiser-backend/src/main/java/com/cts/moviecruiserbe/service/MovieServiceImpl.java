/**
 * 
 */
package com.cts.moviecruiserbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.moviecruiserbe.domain.Movie;
import com.cts.moviecruiserbe.exceptions.MovieAlreadyExistsException;
import com.cts.moviecruiserbe.exceptions.MovieNotFoundException;
import com.cts.moviecruiserbe.repository.MovieRepository;

/**
 * @author ubuntu
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Movie saved;
		Optional<Movie> optional = movieRepository.findById(movie.getId());
		if (optional.isPresent()) {
			throw new MovieAlreadyExistsException("Movie Already Exist [movie : " + movie.getMovieName() + "]");
		} else {
			saved = movieRepository.save(movie);
		}

		return saved;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		//Boolean flag;
		Movie saved;
		Optional<Movie> optional = movieRepository.findById(movie.getId());
		if (optional.isPresent()) {
			saved =  movieRepository.save(movie);
		} else {
			throw new MovieNotFoundException("Movie not found to update");
		}

		return saved;
	}

	@Override
	public Boolean deleteMovieById(Integer movieId) throws MovieNotFoundException {
		Boolean flag;
		Optional<Movie> optional = movieRepository.findById(movieId);
		if (optional.isPresent()) {
			movieRepository.delete(optional.get());
			flag = true;
		} else {
			throw new MovieNotFoundException("Movie not found to delete");
		}
		return flag;

	}


	@Override
	public Movie getMovieById(Integer movieId) throws MovieNotFoundException {
		Movie movieFound;
		Optional<Movie> optional = movieRepository.findById(movieId);
		if (optional.isPresent()) {
			movieFound = optional.get();
		} else {
			throw new MovieNotFoundException("Movie not found by id [movie : " + movieId + "]");
		}
		return movieFound;
	}

	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	@Override
	public List<Movie> getMyMovie(String userId) {
		return movieRepository.findByUserId(userId);
	}

}
