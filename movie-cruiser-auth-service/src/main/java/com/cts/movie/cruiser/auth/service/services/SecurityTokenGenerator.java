/**
 * 
 */
package com.cts.movie.cruiser.auth.service.services;

import java.util.Map;

import com.cts.movie.cruiser.auth.service.model.User;

/**
 * @author ubuntu
 *
 */
public interface SecurityTokenGenerator {

	public Map<String, String> generateToken(User user);
	
}
