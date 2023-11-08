import axios from "axios";
import jwtDecode from "jwt-decode";
import React, {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useState,
} from "react";
import { Perfil } from "../interfaces/responses/Perfil";
import {
  CerrarSesionFn,
  IniciarSesionFn,
  RegistrarseFn,
  Sesion,
  UsuarioSesion,
} from "../interfaces/Sesion";

const sesionContext = createContext<Sesion>({
  usuario: undefined,
  sesionIniciada: () => false,
  iniciarSesion: async () => {},
  registrarse: async () => {},
  cerrarSesion: async () => {},
  perfil: async () => {},
});

function useProvideSesion(): Sesion {
  const [usuario, setUsuario] = useState<UsuarioSesion | undefined>(undefined);

  useEffect(() => {
    const usuarioLocal = window.localStorage.getItem("usuario");
    if (usuarioLocal) {
      const usuario: UsuarioSesion = JSON.parse(usuarioLocal);
      setUsuario(usuario);
    }
  }, []);

  const sesionIniciada = (): boolean =>
    window.localStorage.getItem("usuario") !== null;

  const iniciarSesion: IniciarSesionFn = async (correo, contrasena) => {
    try {
      const res = await axios.post(
        import.meta.env.VITE_API_URI + "usuario/login",
        {
          correo,
          contrasena,
        }
      );
      const infoUsuario = jwtDecode<any>(res.data.token);
      const usuario: UsuarioSesion = {
        nombre: infoUsuario.nombre,
        vendedor: infoUsuario.vendedor,
        token: res.data.token,
        id: infoUsuario.sub,
      };
      window.localStorage.setItem("usuario", JSON.stringify(usuario));
      setUsuario(usuario);
      return res.data;
    } catch (error) {
      console.log(error.response);
      return Promise.reject("Error al registrar");
    }
  };

  const registrarse: RegistrarseFn = async (datos) => {
    try {
      const res = await axios.post(
        import.meta.env.VITE_API_URI + "usuario/signup",
        datos
      );
      return res.data;
    } catch (error) {
      return Promise.reject("Error al registrar");
    }
  };

  const cerrarSesion: CerrarSesionFn = async () => {
    if (usuario) {
      try {
        const res = await axios.post(
          import.meta.env.VITE_API_URI + "usuario/logout",
          {},
          {
            headers: {
              Authorization: `Bearer ${usuario.token}`,
            },
          }
        );
        window.localStorage.removeItem("usuario");
        setUsuario(undefined);
        return res.data;
      } catch (error) {
        console.log(error);
        return Promise.reject("Error al registrar");
      }
    }
  };

  const perfil = async () => {
    if (usuario) {
      try {
        const res = await axios.get<Perfil>(
          import.meta.env.VITE_API_URI + "usuario/profile",
          {
            headers: {
              Authorization: `Bearer ${usuario.token}`,
            },
          }
        );
        return res.data;
      } catch (error) {
        console.log(error);
        return Promise.reject("Error al registrar");
      }
    }
  };

  return {
    usuario,
    sesionIniciada,
    iniciarSesion,
    registrarse,
    cerrarSesion,
    perfil,
  };
}

export const useSesion = () => useContext(sesionContext);

export function ProvideSesion({
  children,
}: {
  children: ReactNode;
}): JSX.Element {
  const sesion = useProvideSesion();

  return (
    <sesionContext.Provider value={sesion}>{children}</sesionContext.Provider>
  );
}
