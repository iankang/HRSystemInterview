import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuardGuard } from './core/_guards/guard.guard';
import { HomeComponent } from './modules/auth/home/home.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { UserRegistration } from './core/_models/UserRegistration';

const routes: Routes = [
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
