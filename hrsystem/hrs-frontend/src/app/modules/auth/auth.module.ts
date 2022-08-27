import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { LandingComponent } from './landing/landing.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatGridListModule } from '@angular/material/grid-list';
import { AssessmentsModule } from '../assessments/assessments.module';
import { AlertModule } from 'src/app/core/alert/alert.module';


@NgModule({
  declarations: [
    HomeComponent,
    LoginComponent,
    UserRegistrationComponent,
    LandingComponent
  ],
  imports: [
    CommonModule,
    MatSidenavModule,
    FormsModule,
    ReactiveFormsModule,
    MatGridListModule,
    AssessmentsModule,
    AlertModule,
    AuthRoutingModule,
  ]
})
export class AuthModule { }
