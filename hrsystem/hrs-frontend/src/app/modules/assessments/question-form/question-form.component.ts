import { CdkStepper } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';

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
