import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../_services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(
    private router:Router,
    private authService:AuthService
  ) { }

  ngOnInit(): void {
  }

  public logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
