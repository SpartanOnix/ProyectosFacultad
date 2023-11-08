import { SimpleUsuarioRes } from "./SimpleUsuario";

export interface OpinionRes {
  id: number;
  opinion: string;
  usuario: SimpleUsuarioRes;
}
