import { CdkStepper } from '@angular/cdk/stepper';
import { Component, Input, OnInit } from '@angular/core';
import { Questions } from 'src/app/core/_models/Questions';

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.scss']
})
export class QuestionFormComponent extends CdkStepper {

  
  ngOnInit(): void {
  }

  onClick(index: number): void {
    this.selectedIndex = index;
  }
}
