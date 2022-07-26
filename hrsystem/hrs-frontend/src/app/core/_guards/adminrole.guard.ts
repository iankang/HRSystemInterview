import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../_services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate, CanActivateChild {

  constructor(
    public auth: AuthService,
    public router:Router
  ){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
      const expectedRole:string = route.data['expectedRole'];
      const user = this.auth.userStored();
    
      if(this.auth.isLoggedIn){
        for( let role of user.user.roles){
          if (role.name == expectedRole){
            console.log('expected role is: '+ role);
            return true;
          }
        }
      }
      this.router.navigate(['/']);
      return  false;
  }
  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivate(childRoute,state);
  }
  
}
