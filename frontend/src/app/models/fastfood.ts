import {User} from "./user";

export interface Fastfood {
  logoUrl: string;
  fastfoodId: number;
  intitule: string;
  adresse: string;
  telephone: string;
  gerant: User;
  serveur: User;
}
