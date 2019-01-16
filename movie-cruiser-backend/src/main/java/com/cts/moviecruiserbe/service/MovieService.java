/**
 * 
 */
package com.cts.moviecruiserbe.service;

import java.util.List;

import com.cts.moviecruiserbe.domain.Movie;
import com.cts.moviecruiserbe.exceptions.MovieAlreadyExistsException;
import com.cts.moviecruiserbe.exceptions.MovieNotFoundException;

/**
 * @author ubuntu
 *
 */
public interface MovieService {
	
	/**
	 * 
	 * @param movie
	 * @return savedMovie
	 * @throws MovieAlreadyExistsException
	 */
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	/**
	 * 
	 * @param movie
	 * @return updateMovie
	 * @throws MovieNotFoundException
	 */
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	/**
	 * 
	 * @param movieId
	 * @return 
	 * @throws MovieNotFoundException
	 */
	public Boolean deleteMovieById(Integer movieId) throws MovieNotFoundException;
	
	/**
	 * 
	 * @param movieId
	 * @return movie
	 * @throws MovieNotFoundException
	 */
	public Movie getMovieById(Integer movieId) throws MovieNotFoundException;
	
	/**
	 * 
	 * @return allMovies
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Movie> getMyMovie(String userId);
}
