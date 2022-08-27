export class LoginRequest {
  identifier:string;
  password:string;

  constructor(email:string, password:string) {
    this.identifier = email;
    this.password = password;
  }
}
