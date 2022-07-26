import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/core/_services/token-storage.service';
import { first } from "rxjs";
import { data } from "autoprefixer";
import { User } from 'src/app/core/_models/User';
import { AlertService } from 'src/app/core/alert/alert.service';
import { AuthService } from 'src/app/core/_services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  form!: FormGroup;
  errorMessage = '';
  roles: string[] = [];
  returnUrl: string | undefined;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private alertService: AlertService
  ) {

    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
   }

  // ngOnInit(): void {
    

  //   if (this.tokenStorage.getToken()) {

  //     this.roles = this.tokenStorage.getUser().roles;
  //     this.returnUrl = this.route.snapshot.queryParams['returnUrl']
  //   }

  //   this.authService.userStored();
  //   // if ( this.authService.isAdmin ){
  //   //   this.router.navigate(['/admin']);
  //   // }
  //   this.router.navigate(['/']);
  // }

  get f() {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    // this.alertService.clear();
    console.log('cleared service')

    if (this.form.invalid) {
      console.log('invalid')
      return;
    }

    this.loading = true;
    this.authService.login(
      this.f['email'].value,
      this.f['password'].value
    ).pipe(first())
      .subscribe({
        next: (user: User) => {
          console.log(data.toString());
          var returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.authService.userStored();
          for (let role of user.user.roles) {
            if (role.name === 'ROLE_ADMIN') {
              console.log('nimepata');
             this.router.navigate(['/admin']);
            } else {
              this.router.navigateByUrl(returnUrl);
            }
          }
          this.reloadPage()

        },
        error: error => {
          console.log('err: ' + error.message);
          this.alertService.error(error);
          // this.handleErrors.handleErrors(error.status,{ id: 'login-err', fade: true, autoClose: true})
          this.loading = false;
        }
      });
  }

  reloadPage(): void {
    if (this.returnUrl){
      this.router.navigate([this.returnUrl]);
    }
  }
}
