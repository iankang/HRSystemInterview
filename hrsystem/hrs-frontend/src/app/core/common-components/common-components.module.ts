import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';

import {MatListModule} from '@angular/material/list';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { ContentHeaderComponent } from './content-header/content-header.component';


@NgModule({
  declarations: [
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    ContentHeaderComponent
  ],
  imports: [
    MatListModule,
    MatFormFieldModule,
    MatIconModule,
    MatExpansionModule,
    CommonModule
  ],
  exports:[
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    ContentHeaderComponent
  ]
})
export class CommonComponentsModule { }
