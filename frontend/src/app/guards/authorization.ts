// src/app/guards/authorization.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';
import {Role} from "../enums/role";


@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRole = route.data['role'] as Role;
    if (this.authService.isAuthenticated() && this.authService.hasRole(requiredRole)) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
