// src/app/components/order-page/order-page.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../../services/data.service';
import { Fastfood } from '../../models/fastfood';
import { Produit } from '../../models/produit';
import { Commande } from '../../models/commande';

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.scss']
})
export class OrderPageComponent implements OnInit {
  fastfood: Fastfood | null = null;
  produits: Produit[] = [];
  panier: Produit[] = [];
  clientInfo = { nom: '', email: '', telephone: '' };

  constructor(private route: ActivatedRoute, private dataService: DataService) {}

  ngOnInit(): void {
    const fastfoodId = +this.route.snapshot.paramMap.get('fastfoodId')!;
    this.dataService.getFastfoodById(fastfoodId).subscribe(fastfood => this.fastfood = fastfood);
    this.dataService.getAllProduits().subscribe(produits => {
      this.produits = produits.filter(p => p.fastfoodId === fastfoodId);
    });
  }

  addToPanier(produit: Produit): void {
    this.panier.push(produit);
  }

  removeFromPanier(index: number): void {
    this.panier.splice(index, 1);
  }

  calculateTotal(): number {
    return this.panier.reduce((total, p) => total + p.prix, 0);
  }

  submitOrder(): void {
    const commande: Commande = {
      id: 0,
      clientId: 0, // Simuler client anonyme
      produitIds: this.panier.map(p => p.id),
      prixGlobal: this.calculateTotal(),
      etat: 'CREEE',
      dateCreation: new Date().toISOString()
    };
    this.dataService.createCommande(commande).subscribe(() => {
      this.panier = [];
      this.clientInfo = { nom: '', email: '', telephone: '' };
      alert('Commande passée avec succès !');
    });
  }
}
