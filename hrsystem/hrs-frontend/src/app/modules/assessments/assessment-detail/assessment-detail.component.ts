import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AssessmentsService } from 'src/app/core/_services/assessments.service';
import { first } from "rxjs";
import { AlertService } from 'src/app/core/alert/alert.service';
import { Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-assessment-detail',
  templateUrl: './assessment-detail.component.html',
  styleUrls: ['./assessment-detail.component.scss']
})
export class AssessmentDetailComponent implements OnInit {

  id: number;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  isEditable = false;

  constructor(
    private route: ActivatedRoute,
    private assessmentService: AssessmentsService,
    private alertService: AlertService,
    private _formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.getAssessment();
  }

  public getAssessment(){
    this.assessmentService.getAssesmentById(this.id)
    .pipe(first())
    .subscribe({
      next:data =>{
        console.log(JSON.stringify(data));
      },
      error:error =>{
        console.log('error: '+error);
      }
    });
  }

  

}
