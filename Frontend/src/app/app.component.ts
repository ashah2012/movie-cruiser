import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from './modules/authentication/authenticate.service';

@Component({
  selector: 'app-root',
  template: `
  <mat-toolbar color="primary">
    <span>Movie Cruiser Application</span>
    <button mat-button [routerLink] = "['/movies/popular']">Popular Movies</button>
    <button mat-button [routerLink] = "['/movies/top_rated']">Top Rated Movies</button>
    <button mat-button [routerLink] = "['/movies/watchlist']">Watchlist</button>
    <button class="search-button" mat-button [routerLink] = "['/movies/search']">Search</button>
    <button mat-button (click) = "logout()">Logout</button>
  </mat-toolbar>
  

  <router-outlet></router-outlet> 
  `,
  styles: []
})
export class AppComponent {
  title = 'app';

  constructor(private authService : AuthenticateService , private routes : Router){

  }

logout(){
 
  this.authService.deleteToken();
  this.routes.navigate(['/login']);

}

}
