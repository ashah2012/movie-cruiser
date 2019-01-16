import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';

import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { MovieService } from './movie.service';


import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie-router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { SearchComponent } from './components/search/search.component';

import { TokenInterceptor } from './interceptor.services';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MovieRouterModule,
    FormsModule,
    MatDialogModule,
    MatInputModule
    
  ],
  declarations: [ThumbnailComponent,  ContainerComponent, WatchlistComponent, TmdbContainerComponent, MovieDialogComponent, SearchComponent],
  exports: [ThumbnailComponent,  MovieRouterModule,MovieDialogComponent, SearchComponent],
  providers: [ MovieService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi:true
  }],
  entryComponents: [MovieDialogComponent]
})
export class MovieModule { }
