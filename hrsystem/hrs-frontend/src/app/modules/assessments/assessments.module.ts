import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssessmentsRoutingModule } from './assessments-routing.module';
import { AssessmentsAllComponent } from './assessments-all/assessments-all.component';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import { AssessmentDetailComponent } from './assessment-detail/assessment-detail.component';


@NgModule({
  declarations: [
    AssessmentsAllComponent,
    AssessmentDetailComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    AssessmentsRoutingModule
  ]
})
export class AssessmentsModule { }
