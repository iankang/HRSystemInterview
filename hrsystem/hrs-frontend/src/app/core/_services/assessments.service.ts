import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Assessment } from 'src/app/core/_models/Assessment';


import { environment } from "src/environments/environment";
import { Questions } from '../_models/Questions';

@Injectable({
  providedIn: 'root'
})
export class AssessmentsService {

  ALL_ASSESSMENTS_URL = environment.apiUrl+`/api/assessments/byUser/`;
  CREATE_ASSESSMENTS_URL = environment.apiUrl+`/api/assessments`;
  GET_QUESTIONS_URL = environment.apiUrl+ `/api/assessments/getQuestions/`;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getAllAssessmentsByUser(userId: number):Observable<Assessment[]>{
    return this.http.get<Assessment[]>(this.ALL_ASSESSMENTS_URL+userId, this.httpOptions);
  }

  createAssessment(userId: string){
    let formData = new FormData();
    formData.append("userId", userId);
    return this.http.post<Assessment>(this.CREATE_ASSESSMENTS_URL, formData);
  }

  getAssesmentById(assessmentId: number) {
   
    return this.http.get<Questions>(this.GET_QUESTIONS_URL+assessmentId);
  }
}
