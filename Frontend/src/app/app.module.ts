import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MovieModule } from './modules/movie/movie.module';
import { AppComponent } from './app.component';
import {AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthguardService } from './authguard.service';
const appRoutes: Routes = [
  {
  path:'',
  redirectTo:'/login',
  pathMatch:'full',
  
  }
];


@NgModule({
  declarations: [
    AppComponent
    
    
  ],
  imports: [
    MovieModule,
    AuthenticationModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
