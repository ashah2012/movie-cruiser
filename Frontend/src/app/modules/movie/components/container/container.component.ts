import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: `./container.component.html`,
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  
  @Input()
  movies : Array<Movie>;


  @Input()
  useWatchlistApi:boolean
  
  constructor(private movieService : MovieService, private snackBar : MatSnackBar){}
    
  

  ngOnInit() {
      
  }

  // called from child using event binders
  addToWatchlist(movie) {
    let message = movie.title + ' was added to your watchlist.';
    this.movieService.addMoviesToWatchlist(movie).subscribe((movie) =>{
     this.snackBar.open(message,'',{
       duration : 2000
     });
    });
  }

   // called from child using event binders
  deleteFromWatchlist(movie){
    let message = movie.title + ' was deleted from your watchlist.';

    for(var i=0; i< this.movies.length; i++){
      if(this.movies[i].title === movie.title){
        this.movies.splice(i,1);
      }
    }

    this.movieService.deleteFromWatchlist(movie).subscribe((movie)=>{
      this.snackBar.open(message,'',{
        duration : 2000
      });
    });
  }

}
