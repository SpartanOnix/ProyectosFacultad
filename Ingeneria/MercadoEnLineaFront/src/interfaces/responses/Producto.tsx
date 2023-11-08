import { OpinionRes } from "./Opinion";
import { SimpleUsuarioRes } from "./SimpleUsuario";

export interface ProductoRes {
  descripcion: string;
  envio: string;
  icono: string;
  id: number;
  nombre: string;
  opiniones?: OpinionRes[];
  precio: number;
  usuario?: SimpleUsuarioRes;
  vendido: boolean;
}
