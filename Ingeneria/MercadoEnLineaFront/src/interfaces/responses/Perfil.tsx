import { ProductoRes } from "./Producto";

export interface Perfil {
  correo: string;
  direccion: string;
  genero: string;
  id: number;
  nombre: string;
  vendedor: boolean;
  productos: ProductoRes[];
}
