import { Component, OnInit } from '@angular/core';
import { User } from "./../user";
import { Router } from "@angular/router";
import { AuthenticateService } from '../authenticate.service';


@Component({
  selector: 'app-login',
  templateUrl: `./login.component.html`,
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  springEndpoint: string;
  token: string;
  user = new User();
  constructor(private authService: AuthenticateService, private router: Router) {
    this.springEndpoint = 'http://localhost:8089/user';
  }

  ngOnInit() {
  }


  loginUser() {
    this.authService.loginUser(this.user).subscribe((data) => {
      console.log('user data', data);

      if (data['token']) {
        this.authService.setToken(data['token']);
        this.router.navigate(['/movies/popular']);

      }


    });
  }

}
