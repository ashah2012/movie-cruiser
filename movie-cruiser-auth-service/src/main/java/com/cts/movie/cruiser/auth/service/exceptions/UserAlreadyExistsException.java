/**
 * 
 */
package com.cts.movie.cruiser.auth.service.exceptions;

/**
 * @author ubuntu
 *
 */
public class UserAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9102078929226484827L;

	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
