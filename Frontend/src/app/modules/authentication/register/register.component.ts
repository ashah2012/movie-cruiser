import { Component, OnInit } from '@angular/core';
import { User } from "./../user";
import { Router } from "@angular/router";
import { AuthenticateService } from '../authenticate.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  springEndpoint: string;
  token: string;
  user = new User();
  constructor(private authService: AuthenticateService, private router: Router) {

    this.springEndpoint = 'http://localhost:8089/user';



  }

  ngOnInit() {
   
  }

  registerUser() {
    this.authService.registerUser(this.user).subscribe((data) => {
      console.log('user data', data);
      this.router.navigate(['/login']);
    });
  }

  resetInput(){
    
  }

}
