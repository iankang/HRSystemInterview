import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentsAllComponent } from './assessments-all.component';

describe('AssessmentsAllComponent', () => {
  let component: AssessmentsAllComponent;
  let fixture: ComponentFixture<AssessmentsAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssessmentsAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssessmentsAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
