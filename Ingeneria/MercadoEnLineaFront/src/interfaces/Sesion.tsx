import { DatosRegistro } from "./DatosRegistro";

export interface Sesion {
  usuario?: UsuarioSesion;
  sesionIniciada: () => boolean;
  iniciarSesion: IniciarSesionFn;
  registrarse: RegistrarseFn;
  cerrarSesion: CerrarSesionFn;
  perfil: PerfilFn;
}

export interface UsuarioSesion {
  token: string;
  nombre: string;
  vendedor: boolean;
  id: number;
}

export type IniciarSesionFn = (
  correo: string,
  contrasena: string
) => Promise<any>;

export type RegistrarseFn = (datos: DatosRegistro) => Promise<any>;

export type CerrarSesionFn = () => Promise<any>;

export type PerfilFn = () => Promise<any>;
