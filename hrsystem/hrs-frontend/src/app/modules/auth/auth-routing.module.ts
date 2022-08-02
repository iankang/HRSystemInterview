import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssessmentsAllComponent } from '../assessments/assessments-all/assessments-all.component';
import { GuardGuard } from './guard.guard';
import { HomeComponent } from './home/home.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [GuardGuard],
    children: [
      {
      path: 'landing', component: LandingComponent,
      },
      {
        path: 'assessments', component: AssessmentsAllComponent
      }
  ]
  },

  { path: 'login', component: LoginComponent },
  { path: 'registration', component: UserRegistrationComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
