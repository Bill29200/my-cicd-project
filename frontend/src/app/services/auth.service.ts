// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { User } from '../models/user';
import {Role} from "../enums/role";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  //private apiUrl = 'http://localhost:8080/api/auth/login';
  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUser: User | null = null;
  private user: Observable<User> | undefined;

  constructor(private http: HttpClient) {
  }


  login(nom: string, password: string): Observable<User> {
    // Créer les paramètres de requête

    // Envoyer une requête GET avec les paramètres
    return this.http.post<User>(this.apiUrl, { nom, password });
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
  }

  getCurrentUser(): User | null {
    return this.currentUser;
  }

  isAuthenticated(): boolean {
    return !!this.getCurrentUser();
  }

  hasRole(role: Role): boolean {
    const user = this.getCurrentUser();
    return user ? user.role === role : false;
  }
}
