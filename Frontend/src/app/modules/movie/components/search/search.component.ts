import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-search',
  templateUrl: './search.component.html',
  styles: []
})
export class SearchComponent implements OnInit {
  movies:Array<Movie>;

  constructor(private movieService: MovieService) { 
    this.movies = [];
  }

  ngOnInit() {
  }

  onEnter(searchKey){
    console.log('search', searchKey);
    this.movieService.searchMovies(searchKey).subscribe((movies)=>{
      this.movies = movies;
    });
  }

}
