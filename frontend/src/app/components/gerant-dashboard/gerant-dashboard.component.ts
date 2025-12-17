// src/app/components/gerant-dashboard/gerant-dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { AuthService } from '../../services/auth.service';
import { Fastfood } from '../../models/fastfood';
import { FamilleProduit } from '../../models/famille-produit';
import { Produit } from '../../models/produit';
import {User} from "../../models/user";

@Component({
  selector: 'app-gerant-dashboard',
  templateUrl: './gerant-dashboard.component.html',
  styleUrls: ['./gerant-dashboard.component.scss']
})
export class GerantDashboardComponent implements OnInit {
  fastfood: Fastfood | null = null;
  familles: FamilleProduit[] = [];
  produits: Produit[] = [];
  familleSearch: string = '';
  produitSearch: string = '';
  user !: User ;

  constructor(private dataService: DataService, private authService: AuthService, private data :DataService)
  {
    this.user = this.data.actualUser;
    //this.familles =
  }

  ngOnInit(): void {
    this.loadFastfood();
    this.loadFamilles();
    this.loadProduits();

  }

  loadFastfood(): void {
    // if (this.user?.fastfood) {
    //   this.dataService.getFastfoodById(this.user.fastfood.fastfoodId).subscribe(fastfood => this.fastfood = fastfood);
    //
    // }
  }

  loadFamilles(): void {
    this.dataService.getAllFamilleProduits().subscribe(familles => this.familles = familles);
  }

  loadProduits(): void {
    // if (this.user?.fastfood) {
    //   this.dataService.getAllProduits().subscribe(produits => {
    //     this.produits = produits.filter(p => p.fastfoodId === this.user?.fastfood?.fastfoodId);
    //   });
    // }
  }

  searchFamilles(): void {
    // Implémenter si endpoint search existe
  }

  searchProduits(): void {
    // if (this.produitSearch) {
    //   this.dataService.searchProduits(this.produitSearch).subscribe(produits => {
    //     this.produits = produits.filter(p => p.fastfoodId === this.user?.fastfood?.fastfoodId);
    //   });
    // } else {
    //   this.loadProduits();
    // }
  }

  createFamille(): void {
    const newFamille: FamilleProduit = { id: 0, intitule: 'Nouvelle Famille' };
    this.dataService.createFamilleProduit(newFamille).subscribe(() => this.loadFamilles());
  }

  createProduit(familleId: number): void {
    // const newProduit: Produit = {
    //   id: 0,
    //   nomProd: 'Nouveau Produit',
    //   prix: 0,
    //   familleProduitId: familleId,
    //   disponible: true,
    //   fastfoodId: this.user?.fastfood?.fastfoodId
    // };
    // this.dataService.createProduit(newProduit).subscribe(() => this.loadProduits());
  }

  updateProduit(produit: Produit): void {
    this.dataService.updateProduit(produit.id, produit).subscribe(() => this.loadProduits());
  }

  deleteProduit(id: number): void {
    this.dataService.deleteProduit(id).subscribe(() => this.loadProduits());
  }
}
