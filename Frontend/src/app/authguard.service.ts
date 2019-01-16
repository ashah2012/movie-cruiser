import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthenticateService } from './modules/authentication/authenticate.service';


@Injectable()
export class AuthguardService implements CanActivate {

  constructor(private routes: Router, private authService : AuthenticateService) { }

  canActivate(){
    if(!this.authService.isTokenExpired()){
      console.log('in canActivate');
      return true;
        }
        this.routes.navigate(['/login']);
        return false;
  }

}
