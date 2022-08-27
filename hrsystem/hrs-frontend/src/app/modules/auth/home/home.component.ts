import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/_services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  username: string | undefined = "";
  email: string | undefined = "";
  profilePic: string | undefined;

  constructor(
    public router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.authService.userStored();
  }

  logout():void{
    this.authService.logout();
    window.location.reload();
  }

}
