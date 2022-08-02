import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuardGuard } from './modules/auth/guard.guard';
import { HomeComponent } from './modules/auth/home/home.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { UserRegistration } from './modules/auth/user-registration/UserRegistration';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [GuardGuard]
  },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: UserRegistration },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: false,
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
