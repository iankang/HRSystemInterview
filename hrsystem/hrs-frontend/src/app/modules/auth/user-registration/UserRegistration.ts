export class UserRegistration {
  password: string
  confirm_password: string;
  username: string;
  email: string;
  first_name: string;
  last_name: string;
  is_Moderator: boolean;
  is_Admin: boolean;

  constructor(password:string, confirmPassword:string, username:string, email:string, firstName:string, lastName:string, b: boolean, b2: boolean) {
    this.password = password;
    this.confirm_password = confirmPassword;
    this.username = username;
    this.email = email;
    this.first_name = firstName;
    this.last_name = lastName;
    this.is_Moderator = b;
    this.is_Admin = b2
  }
}
