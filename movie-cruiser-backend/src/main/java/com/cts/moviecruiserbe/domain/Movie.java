/**
 * 
 */
package com.cts.moviecruiserbe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author abhishekS
 * 
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068794529166578538L;

	public Movie() {

	}

	
	
	public Movie(Integer id, String movieId, String movieName, String moviePosterPath, Date releaseDate,
			String userComments, String overview, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.movieName = movieName;
		this.moviePosterPath = moviePosterPath;
		this.releaseDate = releaseDate;
		this.userComments = userComments;
		this.overview = overview;
		this.userId = userId;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String movieId;

	@Column
	private String movieName;

	@Column
	private String moviePosterPath;

	@Column
	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@Column
	private String userComments;

	@Lob
	@Column(length = 100000)
	private String overview;

	@Column(name = "user_id")
	private String userId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter("movie_id")
	public String getMovieId() {
		return movieId;
	}

	@JsonSetter("movie_id")
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@JsonGetter("title")
	public String getMovieName() {
		return movieName;
	}

	@JsonSetter("title")
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@JsonGetter("poster_path")
	public String getMoviePosterPath() {
		return moviePosterPath;
	}

	@JsonSetter("poster_path")
	public void setMoviePosterPath(String moviePosterPath) {
		this.moviePosterPath = moviePosterPath;
	}

	@JsonGetter("release_date")
	public Date getReleaseDate() {
		return releaseDate;
	}

	@JsonSetter("release_date")
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@JsonGetter("movieComments")
	public String getUserComments() {
		return userComments;
	}

	@JsonGetter("movieComments")
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	@JsonGetter("overview")
	public String getOverview() {
		return overview;
	}

	@JsonSetter("overview")
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	@JsonGetter("user_id")
	public String getUserId() {
		return userId;
	}
	
	@JsonSetter("user_id")
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
