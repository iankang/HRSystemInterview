import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Role, User } from 'src/app/core/_models/User';

import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';
import { LoginRequest } from './login/LoginRequest';
import { UserRegistration } from './user-registration/UserRegistration';


const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  BASE_URL = "https://developers.lunna.chat:8084"
  AUTH_API = `${this.BASE_URL}/auth/signin`;
  SIGN_UP = `${this.BASE_URL}/auth/signup`;
  RESET_PASSWORD = `${this.BASE_URL}/auth/password/reset`;
  VERIFY_ACCOUNT = `${this.BASE_URL}/auth/email/verification`;
  RESEND_EMAIL = `${this.BASE_URL}/auth/email/verification/resend`;
  PASSWORD_CHANGE = `${this.BASE_URL}/auth/password/reset/verification`;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  isLoggedIn!: boolean;
  userSubject: BehaviorSubject<User>;
  user: Observable<User>;

  redirectUrl: string | null = null;
  roles = new Set();
  showAdminBoard: boolean =false;
  showModeratorBoard: boolean = false;
  isAdmin: boolean = false;

  constructor(private http: HttpClient, private router: Router,) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem(USER)!!));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  public userStored(): User| null {
    let user = JSON.parse(localStorage.getItem(USER)!!);
    if(user) {
      this.isLoggedIn = true;
      user?.user?.roles?.forEach((value: Role) => {
        this.roles.add(value.name);
      });
      console.log('roles: ' + this.roles.toString());
      this.showAdminBoard = this.roles?.has('ROLE_ADMIN');
      this.showModeratorBoard = this.roles?.has('ROLE_MODERATOR');
      if (this.showModeratorBoard || this.showAdminBoard) {
        this.isAdmin = true;
      }
      return user;
    }
    return null;
  }


  login(identifier: string, password: string): Observable<any> {
    return this.http.post<User>(this.AUTH_API, {identifier, password}, this.httpOptions)
      .pipe(map(user => {
        console.log(user);
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      }));
  }

  loginB(loginRequest: LoginRequest): Observable<any> {
    const body = JSON.stringify(loginRequest)
    console.log('body: ' + body);
    return this.http.post(this.AUTH_API, body, this.httpOptions);
  }

  register(userRegistration: UserRegistration) {
    return this.http.post(this.SIGN_UP, userRegistration);
  }


  registerB(userRegistration: UserRegistration): Observable<any> {
    const body = JSON.stringify(userRegistration)
    console.log('body: ' + body);
    return this.http.post(this.SIGN_UP, body, this.httpOptions);
  }



  logout() {
    console.log('logging out')
    // localStorage.removeItem('user');
    this.isLoggedIn = false;
    this.isAdmin = false;
    localStorage.clear();
    this.userSubject?.next(null);
    this.router.navigate(['/']);
  }
}
