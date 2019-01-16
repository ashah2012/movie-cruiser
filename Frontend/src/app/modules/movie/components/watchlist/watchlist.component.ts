import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  template: `
   <movie-container [movies]="movies" [useWatchlistApi]="useWatchlistApi"></movie-container>
  `,
  styles: []
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>;
  useWatchlistApi = true;

  constructor(private movieService : MovieService, private snackBar : MatSnackBar) {
    this.movies = [];

   }

  ngOnInit() {
    let message = "Watch list is Empty";
    this.movieService.getWatchlistedMovies().subscribe((movies)=>{
     if(movies.length==0){
       this.snackBar.open(message,'',{
         duration:1000
       });
     }
      this.movies.push(...movies);
    });

  }

}
