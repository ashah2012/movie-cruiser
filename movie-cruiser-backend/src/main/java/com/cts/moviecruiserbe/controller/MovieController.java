/**
 * 
 */
package com.cts.moviecruiserbe.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.moviecruiserbe.domain.Movie;
import com.cts.moviecruiserbe.exceptions.MovieAlreadyExistsException;
import com.cts.moviecruiserbe.exceptions.MovieNotFoundException;
import com.cts.moviecruiserbe.service.MovieService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author ubuntu
 *
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	/**
	 * Saves a new movie
	 * 
	 * @param movie
	 * @return response entity
	 */
	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody final Movie movie, HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		final String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId : " + userId);
				
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException alreadyExistsException) {
			responseEntity = new ResponseEntity<String>(
					"{ \"message\" : \"" + alreadyExistsException.getMessage() + "\" } ", HttpStatus.CONFLICT);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + exception.getMessage() + "\" } ",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * Updates a movie
	 * 
	 * @param id
	 * @param movie
	 * @return response entity
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable final Integer id, @RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(
					"{ \"message\" : \"" + movieNotFoundException.getMessage() + "\" } ", HttpStatus.CONFLICT);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + exception.getMessage() + "\" } ",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * Updates a movie
	 * 
	 * @param id
	 * @param movie
	 * @return response entity
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			if (movieService.deleteMovieById(id)) {
				responseEntity = new ResponseEntity<Movie>(HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<Movie>(HttpStatus.CONFLICT);
			}
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(
					"{ \"message\" : \"" + movieNotFoundException.getMessage() + "\" } ", HttpStatus.NOT_FOUND);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + exception.getMessage() + "\" } ",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * Updates a movie
	 * 
	 * @param id
	 * @param movie
	 * @return response entity
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie movie = movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException movieNotFoundException) {
			responseEntity = new ResponseEntity<String>(
					"{ \"message\" : \"" + movieNotFoundException.getMessage() + "\" } ", HttpStatus.NOT_FOUND);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + exception.getMessage() + "\" } ",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * Get all movies
	 * 
	 * @return response entity
	 */
	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		ResponseEntity<?> responseEntity;
		List<Movie> movies = movieService.getAllMovies();
		responseEntity = new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "/movies")
	public ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest req, final ServletResponse res){

		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		final String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		
		List<Movie> myMovies = movieService.getMyMovie(userId);
		ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<List<Movie>>(myMovies, HttpStatus.OK);
		System.out.println("UserId :: + " +userId);
		return responseEntity;
	}

}
