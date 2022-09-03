import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssessmentsAllComponent } from './assessments-all/assessments-all.component';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import { AssessmentDetailComponent } from './assessment-detail/assessment-detail.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { RouterModule } from '@angular/router';
import {MatStepperModule} from '@angular/material/stepper';
import { MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { QuestionFormComponent } from './question-form/question-form.component';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {MatRadioModule} from '@angular/material/radio';
@NgModule({
  declarations: [
    AssessmentsAllComponent,
    AssessmentDetailComponent,
    QuestionFormComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    MatSlideToggleModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    CdkStepperModule,
    MatRadioModule
  ]
})
export class AssessmentsModule { }
