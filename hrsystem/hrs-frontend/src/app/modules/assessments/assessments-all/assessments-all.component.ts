import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/core/_models/User';
import { first } from "rxjs";
import { data } from "autoprefixer";
import { Assessment } from 'src/app/core/_models/Assessment';
import { AuthService } from 'src/app/core/_services/auth.service';
import { AssessmentsService } from 'src/app/core/_services/assessments.service';
import { AlertService } from 'src/app/core/alert/alert.service';

@Component({
  selector: 'app-assessments-all',
  templateUrl: './assessments-all.component.html',
  styleUrls: ['./assessments-all.component.scss']
})
export class AssessmentsAllComponent implements OnInit {

  private user: User;
  assessments?: Assessment[];
  isAssessmentsEmtpty: Boolean = true;

  constructor(
    private authService: AuthService,
    private assessmentService: AssessmentsService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.getAssessments();
  }

  createAssessment(){
    this.assessmentService.createAssessment(this.user.user.id.toString())
    .pipe(first())
    .subscribe({
      next:data =>{

        this.alertService.success('assessment added');
      },
      error:error =>{

        this.alertService.error(error.message);
      }
    });
  }

  public getAssessments() {

    this.authService.userStored();
    this.user = this.authService.userValue;
    this.assessmentService
    .getAllAssessmentsByUser(this.user.user.id)
    .pipe(first())
    .subscribe({
      next: data => {
        console.log(data);
        this.assessments = data;
        if (data.length > 0) {
          this.isAssessmentsEmtpty = false;
        }
      },
      error: error => {
        console.log('err: ' + error.message);
        this.alertService.error(error.message);
        // this.handleErrors.handleErrors(error.status,{ id: 'login-err', fade: true, autoClose: true})
        // this.loading = false;
      }
    });
  }

  public takeAssessment(id: number){
    console.log("clicked "+ id);
  }
}
