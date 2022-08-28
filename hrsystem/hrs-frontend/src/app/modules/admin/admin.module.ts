import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminComponent } from './admin/admin.component';
import { CommonComponentsModule } from 'src/app/core/common-components/common-components.module';
import { AdminLandingComponent } from './admin-landing/admin-landing.component';


@NgModule({
  declarations: [
    AdminComponent,
    AdminLandingComponent
  ],
  imports: [
    CommonModule,
    CommonComponentsModule
  ]
})
export class AdminModule { }
