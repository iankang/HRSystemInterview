import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import {MatSidenavModule} from '@angular/material/sidenav';


@NgModule({
  declarations: [
    HomeComponent,
    LoginComponent,
    UserRegistrationComponent
  ],
  imports: [
    CommonModule,
    MatSidenavModule,
    AuthRoutingModule
  ]
})
export class AuthModule { }
