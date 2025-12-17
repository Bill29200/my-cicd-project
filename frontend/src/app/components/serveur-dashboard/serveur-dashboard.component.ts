// src/app/components/serveur-dashboard/serveur-dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { AuthService } from '../../services/auth.service';
import { Commande } from '../../models/commande';
import { Etat } from '../../enums/etat';

@Component({
  selector: 'app-serveur-dashboard',
  templateUrl: './serveur-dashboard.component.html',
  styleUrls: ['./serveur-dashboard.component.scss']
})
export class ServeurDashboardComponent implements OnInit {
  Etat = Etat; // Exposer l'enum pour le template
  commandes: Commande[] = [];
  fastfood = this.authService.getCurrentUser()?.fastfoodId;
  user = this.authService.getCurrentUser();
  search: { [key in Etat]: string } = {
    [Etat.CREEE]: '',
    [Etat.EN_ATTENTE]: '',
    [Etat.PRETE]: '',
    [Etat.LIVREE]: ''
  };

  constructor(private dataService: DataService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadCommandes();
  }

  loadCommandes(): void {
    // this.dataService.getAllCommandes().subscribe(commandes => {
    //   if (this.fastfood) {
    //     this.commandes = commandes.filter(c =>
    //       c.produitIds.some(id => this.getProduitFastfoodId(id) === this.fastfood!.fastfoodId)
    //     );
    //   } else {
    //     this.commandes = [];
    //   }
    // });
  }

  searchCommandes(etat: Etat): void {
    if (this.search[etat]) {
      this.dataService.filterCommandesByEtat(etat).subscribe(commandes => {
        if (this.fastfood) {
          this.commandes = commandes.filter(c =>
            // c.produitIds.some(id => this.getProduitFastfoodId(id) === this.fastfood!.fastfoodId) &&
            c.prixGlobal.toString().includes(this.search[etat])
          );
        } else {
          this.commandes = [];
        }
      });
    } else {
      this.loadCommandes();
    }
  }

  nextEtat(commande: Commande): void {
    let newEtat: string;
    switch (commande.etat) {
      case Etat.CREEE: newEtat = Etat.EN_ATTENTE; break;
      case Etat.EN_ATTENTE: newEtat = Etat.PRETE; break;
      case Etat.PRETE: newEtat = Etat.LIVREE; break;
      default: return;
    }
    this.updateCommandeEtat(commande, newEtat);
  }

  prevEtat(commande: Commande): void {
    let newEtat: string;
    switch (commande.etat) {
      case Etat.EN_ATTENTE: newEtat = Etat.CREEE; break;
      case Etat.PRETE: newEtat = Etat.EN_ATTENTE; break;
      case Etat.LIVREE: newEtat = Etat.PRETE; break;
      default: return;
    }
    this.updateCommandeEtat(commande, newEtat);
  }

  updateCommandeEtat(commande: Commande, etat: string): void {
    const updatedCommande = { ...commande, etat };
    this.dataService.updateCommande(commande.id, updatedCommande).subscribe(() => this.loadCommandes());
  }

  // getProduitFastfoodId(produitId: number): number {
  //   // return this.fastfood?.fastfoodId || 0;
  // }
}
