// src/app/components/login/login.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

import {User} from "../../models/user";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  nom: string = '';
  password: string = '';
  error: string = '';

  constructor(private authService: AuthService, private router: Router, private data : DataService) {}

  login(): void {

    this.error = '';
    this.authService.login(this.nom, this.password).subscribe({

      next: (user: User) => {
        this.data.actualUser = user;
        switch (user.role) {
          case 'ADMIN':
            console.log(user);
            this.router.navigate(['/admin']);
            break;
          case 'GERANT':
            console.log('Je suis gerant');
            this.router.navigate(['/gerant']);
            break;
          case 'SERVEUR':
            console.log('Je suis Serveur');
            this.router.navigate(['/serveur']);
            break;
          default:
            console.log('Je suis client');
            this.router.navigate(['/client']);
        }
      },
      error: (err) => {
        this.error = `Erreur ${err.status}: ${err.error?.message || err.error?.error || 'Requête invalide'}`;
        console.error('Erreur de connexion:', err); // Afficher l'erreur dans la console
      }
    });
  }

}
