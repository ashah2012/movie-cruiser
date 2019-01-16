
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';



import { TmdbContainerComponent } from "./components/tmdb-container/tmdb-container.component";
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { AuthguardService } from './../../authguard.service';
const movieRoutes : Routes = [
  {
    path:'movies',
    children:[
      {
        path:'',
        redirectTo:'/movies/popular',
        pathMatch:'full',
        canActivate: [AuthguardService]
      },{
        path:'popular',
        component: TmdbContainerComponent,
        canActivate: [AuthguardService],
        data:{
          movieType:'popular'
        }
      },{
        path:'top_rated',
        component: TmdbContainerComponent,
        canActivate: [AuthguardService],
        data:{
          movieType:'top_rated'
        }
      },
      {
        path:'watchlist',
        component: WatchlistComponent,
        canActivate: [AuthguardService]
      },
      {
        path:'search',
        component: SearchComponent,
        canActivate: [AuthguardService],
        
      }
    ]
  }
];

@NgModule({
   imports:[
     RouterModule.forChild(movieRoutes),
   ],
   exports:[
     RouterModule
   ]
  })
  export class MovieRouterModule { }

