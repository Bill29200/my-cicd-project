export interface Commande {
  id: number;
  clientId: number;
  produitIds: number[];
  prixGlobal: number;
  etat: string;
  dateCreation: string;
}
