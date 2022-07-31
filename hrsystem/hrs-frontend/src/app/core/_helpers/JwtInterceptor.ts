import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

import {Observable} from "rxjs";
import { environment } from "src/environments/environment";
import { AuthService } from "src/app/modules/auth/auth.service";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
 
  constructor(private accountService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add auth header with jwt if user is logged in and request is to the api url
    const user = this.accountService.userValue;
    const isLoggedIn = user && user.access_token;
    const isApiUrl = request.url.startsWith(environment.apiUrl);

    if (isLoggedIn && isApiUrl) {
      console.log('intercept: logged in')
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${user.access_token}`
        }
      });
    }

    return next.handle(request);
  }
}
