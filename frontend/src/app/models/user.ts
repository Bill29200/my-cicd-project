import {Fastfood} from "./fastfood";

export interface User {
  userId: number;
  nom: string;
  role: string;
  password: string;
  fastfoodId?: number;
}
