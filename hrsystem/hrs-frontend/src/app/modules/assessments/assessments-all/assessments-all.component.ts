import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/core/_models/User';
import { AuthService } from '../../auth/auth.service';
import { AssessmentsService } from '../assessments.service';
import { first } from "rxjs";
import { data } from "autoprefixer";
import { Assessment } from 'src/app/core/_models/Assessment';

@Component({
  selector: 'app-assessments-all',
  templateUrl: './assessments-all.component.html',
  styleUrls: ['./assessments-all.component.scss']
})
export class AssessmentsAllComponent implements OnInit {

  private user: User;
  assessments?: Assessment[];

  constructor(
    private authService: AuthService,
    public assessmentService: AssessmentsService
  ) { }

  ngOnInit(): void {

    this.authService.userStored();
    this.user = this.authService.userValue;
    this.assessmentService
    .getAllAssessmentsByUser(this.user.user.id)
    .pipe(first())
    .subscribe({
      next: data => {
        console.log(data);
        this.assessments = data;

      },
      error: error => {
        console.log('err: ' + error.message);
        // this.handleErrors.handleErrors(error.status,{ id: 'login-err', fade: true, autoClose: true})
        // this.loading = false;
      }
    });
  }

  createAssessment(){
    this.assessmentService.createAssessment(this.user.user.id.toString());
  }

}
