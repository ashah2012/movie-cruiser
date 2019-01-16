import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule} from '@angular/common/http';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule, MatIcon }  from '@angular/material';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthenticateService } from "./authenticate.service";
import { AuthenticationRoutingModule } from "./authentication-router.module";

@NgModule({
  imports: [
    
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    MatDialogModule,
    MatInputModule,
    AuthenticationRoutingModule
  ],
  declarations: [LoginComponent, RegisterComponent],
  providers: [AuthenticateService]

})
export class AuthenticationModule { }
