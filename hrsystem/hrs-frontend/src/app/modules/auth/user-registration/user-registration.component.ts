import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthService } from '../auth.service';
import { UserRegistration } from './UserRegistration';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss']
})
export class UserRegistrationComponent implements OnInit {

  form: FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  submitted: boolean = false;
  returnUrl: string;
  loading = false

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  get f() {
    return this.form.controls;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.form.valid) {
      const userRegistration: UserRegistration = new UserRegistration(
        this.f['password'].value,
        this.f['confirmPassword'].value,
        'username',
        this.f['email'].value,
        this.f['firstName'].value,
        this.f['lastName'].value,
        false,
        false);
      console.log('registering')
      this.authService.register(userRegistration)
        .pipe(first())
        .subscribe({
          next: () => {
            // this.alertsService.success('Registration Successful', {keepAfterRouteChange: true});
            this.router.navigate(['../verify'], { relativeTo: this.route });
          }, error: error => {
            this.loading = false;
            // this.handleError.handleErrors(error.status, {id: 'registration-err', autoClose: true, fade: true});
          }
        })
    } else {
      console.log('error')
      return;
    }
  }
}
