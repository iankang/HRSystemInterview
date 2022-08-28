import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/_services/auth.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.authService.userValue;
    if(this.authService.isAdmin){
      console.log('landingComponent isAdmin');
      this.router.navigate(['/admin'])
    } 
    console.log('normal user');
    this.router.navigate(['/assesments']);
    
  }

}
