
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';

import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from './login/login.component';


const authRoutes : Routes = [
  {
    path:'',
    children:[
      {
        path:'',
        redirectTo:'/login',
        pathMatch:'full'
      },{
        path:'login',
        component: LoginComponent
      },{
        path:'register',
        component: RegisterComponent
      }
      
    ]
  }
];

@NgModule({
   imports:[
     RouterModule.forRoot(authRoutes),
   ],
   exports:[
     RouterModule,
     MatInputModule,
     FormsModule
   ]
  })
  export class AuthenticationRoutingModule { }

