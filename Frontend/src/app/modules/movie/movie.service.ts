import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Observable } from 'rxjs/Observable';
import { Movie } from './movie';

@Injectable()
export class MovieService {

  tmdbEndPoint: string;
  imagePrefix :string;
  apiKey: string;
  watchlistEndpoint: string;
  springEndpoint: string;
  searchMovie: string;

  constructor(private http : HttpClient) { 
    this.tmdbEndPoint = 'https://api.themoviedb.org/3/movie/';
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';
    this.apiKey = '?api_key=1c3700f4a712115d5700c689ea63b7c1';
    this.watchlistEndpoint = 'http://localhost:3000/watchlist';
    this.springEndpoint = 'http://localhost:8082/api/movie';
    this.searchMovie = 'https://api.themoviedb.org/3/search/movie';
  }

  getMovies(type : string, page : number = 1): Observable<Array<Movie>> {
    const getMoviesEndpoint = this.tmdbEndPoint + type + this.apiKey + '&page=' + page; 
        return this.http.get(getMoviesEndpoint).pipe(
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
  }



  transformPosterPath(movies): Array<Movie> {
    return movies.map(movie =>{
      movie.poster_path = this.imagePrefix + movie.poster_path;
        return movie;
    });
  }

  pickMovieResults(response){
    return response['results'];
  }


  addMoviesToWatchlist(movie){
    //return this.http.post(this.watchlistEndpoint, movie);
    return this.http.post(this.springEndpoint, movie);
  }


  getWatchlistedMovies(): Observable<Array<Movie>>{
    //return this.http.get<Array<Movie>>(this.watchlistEndpoint);
    return this.http.get<Array<Movie>>(this.springEndpoint + '/movies');
  }
  
  deleteFromWatchlist(movie: Movie){
    const url = this.springEndpoint + "/" + movie.id;
    return this.http.delete(url,{responseType: 'text'});
  }

  updatComments(movie: Movie){
    const url = this.springEndpoint + "/" + movie.id;
    return this.http.put(url,movie) ;
  }

  searchMovies(searhKey: string): Observable<Array<Movie>>{
    if(searhKey.length > 0 ){
      const url = this.searchMovie + this.apiKey + '&language=en-US&page=1&query=' + searhKey;
      console.log('search Url', url);
      return this.http.get(url).pipe(
       
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
    }
  }
}
