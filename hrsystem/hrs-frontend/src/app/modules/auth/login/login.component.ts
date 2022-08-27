import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/core/_services/token-storage.service';
import { AuthService } from '../auth.service';
import { first } from "rxjs";
import { data } from "autoprefixer";
import { User } from 'src/app/core/_models/User';
import { AlertService } from 'src/app/core/alert/alert.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

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
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })

    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getUser().roles;
      this.returnUrl = this.route.snapshot.queryParams['returnUrl']
    }

    this.returnUrl = '/assessments';
  }

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
          // const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          // this.router.navigateByUrl(returnUrl);
          this.authService.userStored();
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
    this.router.navigate([this.returnUrl]);
  }
}
