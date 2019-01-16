/**
 * 
 */
package com.cts.moviecruiserbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.moviecruiserbe.domain.Movie;

/**
 * @author ubuntu
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	
	
	public List<Movie> findByUserId(String userId);
}
