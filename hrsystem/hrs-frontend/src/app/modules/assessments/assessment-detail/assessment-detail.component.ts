import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AssessmentsService } from 'src/app/core/_services/assessments.service';
import { first } from "rxjs";
import { AlertService } from 'src/app/core/alert/alert.service';
import { Validators, FormBuilder } from '@angular/forms';
import { Questions } from 'src/app/core/_models/Questions';

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
  questions:Questions[];
  currentQuestion : Questions;
  currentIndex: number = 0;

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
        this.questions = data;
        this.currentQuestion = data[this.currentIndex];
      },
      error:error =>{
        console.log('error: '+error);
      }
    });
  }
  public getPreviousQuestion(){
    console.log('currentIndex: ', this.currentIndex);
    if(this.currentIndex < this.questions.length  && this.currentIndex > 0){
      this.currentIndex-=1;
      this.currentQuestion = this.questions[this.currentIndex];
   
    } else {
      this.currentIndex = 0;
    }
    
  }
  public getNextQuestion(){
    console.log('currentIndex: ', this.currentIndex);
    if(this.currentIndex < this.questions.length  && this.currentIndex >= 0){
      this.currentIndex+=1;
      this.currentQuestion = this.questions[this.currentIndex];
 
    } else {
      this.currentIndex = 0;
    }
   
  }
 
}
