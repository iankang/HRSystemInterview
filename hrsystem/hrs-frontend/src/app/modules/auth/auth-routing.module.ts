import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from 'src/app/core/_guards/adminrole.guard';
import { GuardGuard } from 'src/app/core/_guards/guard.guard';
import { AdminComponent } from '../admin/admin/admin.component';
import { AssessmentDetailComponent } from '../assessments/assessment-detail/assessment-detail.component';
import { AssessmentsAllComponent } from '../assessments/assessments-all/assessments-all.component';

import { HomeComponent } from './home/home.component';
import { LandingComponent } from './landing/landing.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivateChild: [GuardGuard],
    children: [
      {
      path: '', component: LandingComponent,
      },
      {
        path:'admin',
        component: AdminComponent,
        canActivate:[RoleGuard],
        data:{
          expectedRole: 'ROLE_ADMIN'
        }
      },
      {
        path:'assessments',
        component: AssessmentsAllComponent,
        canActivateChild:[GuardGuard],
        children:[
         
        ]
      },
      {
        path:'assessment/:id',
        component: AssessmentDetailComponent
      }
  ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
