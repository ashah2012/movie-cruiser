import { Component, OnInit, Input, Inject } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';


import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styles: []
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<MovieDialogComponent>, 
  @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService) { 

    this.comments = data.obj.movieComments;
    this.movie = data.obj;
    this.actionType = data.actionType;
  }

  ngOnInit() {
   
  }

  onNoClick(){
    this.dialogRef.close();
  }

  updateComments(){
    
    this.movie.movieComments = this.comments;
    this.dialogRef.close();
    this.movieService.updatComments(this.movie).subscribe((movie)=>{
      this.snackBar.open("Movie updated to watchlist successfully", "", {
        duration:2000
      });
    });
  }

}
