import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './core/_models/User';
import { AuthService } from './core/_services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'hrs-frontend';
  currentUser: User;

  constructor(
      private router: Router,
      private authenticationService: AuthService
  ) {
     
  }


}
