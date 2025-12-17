export interface Produit {
  id: number;
  nomProd: string;
  prix: number;
  familleProduitId: number;
  disponible: boolean;
  photo?: string;
  fastfoodId?: number;
}
