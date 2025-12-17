// src/app/pipes/filter.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterByFamille'
})
export class FilterByFamillePipe implements PipeTransform {
  transform(produits: any[], familleId: number): any[] {
    return produits.filter(produit => produit.familleProduitId === familleId);
  }
}

@Pipe({
  name: 'filterByEtat'
})
export class FilterByEtatPipe implements PipeTransform {
  transform(commandes: any[], etat: string): any[] {
    return commandes.filter(commande => commande.etat === etat);
  }
}
