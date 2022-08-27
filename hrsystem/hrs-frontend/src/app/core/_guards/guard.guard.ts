import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/modules/auth/auth.service';


@Injectable({
  providedIn: 'root'
})
export class GuardGuard implements CanActivate, CanActivateChild {
  constructor(private authService:AuthService, private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): true| UrlTree {
    console.log('AuthGuard#canActivate called')
    const url:string = state.url;
    return this.checkLogin(url);
  }

  checkLogin(url:string):true|UrlTree{

    this.authService.userStored();
    
    if(this.authService.isLoggedIn){
      return  true;
    }
    this.authService.redirectUrl = url;
    return  this.router.parseUrl('/login')
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot):  true | UrlTree {
    return this.canActivate(childRoute, state);
  }
}
