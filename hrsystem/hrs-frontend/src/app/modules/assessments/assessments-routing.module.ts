import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssessmentsAllComponent } from './assessments-all/assessments-all.component';

const routes: Routes = [
  {path: 'assessments',component: AssessmentsAllComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssessmentsRoutingModule { }
