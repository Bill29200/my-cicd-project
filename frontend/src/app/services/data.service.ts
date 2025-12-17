import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import { User } from '../models/user';
import { Fastfood } from '../models/fastfood';
import { Produit } from '../models/produit';
import { FamilleProduit } from '../models/famille-produit';
import { Commande } from '../models/commande';
import {Role} from "../enums/role";
import {FastfoodDTO} from "../models/FastfoodDTO";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'http://localhost:8080/api';
  actualUser!: User;
  // roleActual!: Role;
  // actualFastfood!: Fastfood;
  // actualFamilles: FamilleProduit[] = [];
  // actualProduits: Produit[] = [];
  //
  // currentGerant! : User;
  // currentServeur!: User;
  constructor(private http: HttpClient) {}

  // ---------------------------------------------------------------------------------------------  Users
  login(nom: string, password: string): Observable<User> {
    const params = new HttpParams()
      .set('nom', nom)
      .set('password', password);
    return this.http.get<User>(`${this.apiUrl}/login`, { params }).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la connexion')))
    );
  }
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la récupération des utilisateurs')))
    );
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/users/${id}`).pipe(
      catchError(err => throwError(() => new Error('Utilisateur non trouvé')))
    );
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/users`, user).pipe(
      catchError(err => {
        if (err.status === 400 && err.error?.message.includes('nom')) {
          return throwError(() => new Error(`Le nom "${user.nom}" est déjà pris`));
        }
        return throwError(() => new Error('Erreur lors de la création de l\'utilisateur'));
      })
    );
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/users/${id}`, user).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la mise à jour de l\'utilisateur')))
    );
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/users/${id}`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la suppression de l\'utilisateur')))
    );
  }

  // ------------------------------------------------------------------------------------------ Fastfoods
  getAllFastfoods(): Observable<Fastfood[]> {
    return this.http.get<Fastfood[]>(`${this.apiUrl}/fastfoods`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la récupération des fastfoods')))
    );
  }

  getFastfoodById(id: number): Observable<Fastfood> {
    return this.http.get<Fastfood>(`${this.apiUrl}/fastfoods/${id}`).pipe(
      catchError(err => throwError(() => new Error('Fastfood non trouvé')))
    );
  }

  createFastfood(dto: FastfoodDTO): Observable<Fastfood> {
    return this.http.post<Fastfood>(`${this.apiUrl}/fastfoods`, dto).pipe(
      catchError(err => {
        if (err.status === 400) {
          return throwError(() => new Error('Données invalides pour la création du fastfood'));
        }
        return throwError(() => new Error('Erreur lors de la création du fastfood'));
      })
    );
  }

  updateFastfood(id: number, fastfood: Fastfood): Observable<Fastfood> {
    return this.http.put<Fastfood>(`${this.apiUrl}/fastfoods/${id}`, fastfood).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la mise à jour du fastfood')))
    );
  }

  deleteFastfood(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/fastfoods/${id}`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la suppression du fastfood')))
    );
  }

  searchFastfoods(keyword: string): Observable<Fastfood[]> {
    const params = new HttpParams().set('keyword', keyword);
    return this.http.get<Fastfood[]>(`${this.apiUrl}/fastfoods/search`, { params }).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la recherche des fastfoods')))
    );
  }

  // Produits
  getAllProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/produits`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la récupération des produits')))
    );
  }

  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/produits/${id}`).pipe(
      catchError(err => throwError(() => new Error('Produit non trouvé')))
    );
  }

  createProduit(produit: Produit): Observable<Produit> {
    return this.http.post<Produit>(`${this.apiUrl}/produits`, produit).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la création du produit')))
    );
  }

  updateProduit(id: number, produit: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/produits/${id}`, produit).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la mise à jour du produit')))
    );
  }

  deleteProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/produits/${id}`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la suppression du produit')))
    );
  }

  searchProduits(nomProd: string): Observable<Produit[]> {
    const params = new HttpParams().set('nomProd', nomProd);
    return this.http.get<Produit[]>(`${this.apiUrl}/produits/search`, { params }).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la recherche des produits')))
    );
  }

  filterProduitsByFamille(familleId: number): Observable<Produit[]> {
    const params = new HttpParams().set('familleId', familleId.toString());
    return this.http.get<Produit[]>(`${this.apiUrl}/produits/filter`, { params }).pipe(
      catchError(err => throwError(() => new Error('Erreur lors du filtrage des produits')))
    );
  }

  // FamilleProduits
  getAllFamilleProduits(): Observable<FamilleProduit[]> {
    return this.http.get<FamilleProduit[]>(`${this.apiUrl}/familles`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la récupération des familles')))
    );
  }

  getFamilleProduitById(id: number): Observable<FamilleProduit> {
    return this.http.get<FamilleProduit>(`${this.apiUrl}/familles/${id}`).pipe(
      catchError(err => throwError(() => new Error('Famille non trouvée')))
    );
  }

  createFamilleProduit(famille: FamilleProduit): Observable<FamilleProduit> {
    return this.http.post<FamilleProduit>(`${this.apiUrl}/familles`, famille).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la création de la famille')))
    );
  }

  updateFamilleProduit(id: number, famille: FamilleProduit): Observable<FamilleProduit> {
    return this.http.put<FamilleProduit>(`${this.apiUrl}/familles/${id}`, famille).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la mise à jour de la famille')))
    );
  }

  deleteFamilleProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/familles/${id}`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la suppression de la famille')))
    );
  }

  // Commandes
  getAllCommandes(): Observable<Commande[]> {
    return this.http.get<Commande[]>(`${this.apiUrl}/commandes`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la récupération des commandes')))
    );
  }

  getCommandeById(id: number): Observable<Commande> {
    return this.http.get<Commande>(`${this.apiUrl}/commandes/${id}`).pipe(
      catchError(err => throwError(() => new Error('Commande non trouvée')))
    );
  }

  createCommande(commande: Commande): Observable<Commande> {
    return this.http.post<Commande>(`${this.apiUrl}/commandes`, commande).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la création de la commande')))
    );
  }

  updateCommande(id: number, commande: Commande): Observable<Commande> {
    return this.http.put<Commande>(`${this.apiUrl}/commandes/${id}`, commande).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la mise à jour de la commande')))
    );
  }

  deleteCommande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/commandes/${id}`).pipe(
      catchError(err => throwError(() => new Error('Erreur lors de la suppression de la commande')))
    );
  }

  filterCommandesByEtat(etat: string): Observable<Commande[]> {
    const params = new HttpParams().set('etat', etat);
    return this.http.get<Commande[]>(`${this.apiUrl}/commandes/filter`, { params }).pipe(
      catchError(err => throwError(() => new Error('Erreur lors du filtrage des commandes')))
    );
  }
}
