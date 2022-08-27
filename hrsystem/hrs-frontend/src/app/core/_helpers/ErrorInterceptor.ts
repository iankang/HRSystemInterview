import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";

import {catchError, Observable, throwError} from "rxjs";
import { AuthService } from "../_services/auth.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor{

  constructor(private accountService:AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError( err => {
      if([401, 403].includes(err.status) && this.accountService.userValue){
        console.log('errors 401 or 403')
        this.accountService.logout()
      }
        console.log('interceptedError '+ err.status)
        const error = err.error?.message || err.statusText;
        return throwError(err);
      }));
  }
}
