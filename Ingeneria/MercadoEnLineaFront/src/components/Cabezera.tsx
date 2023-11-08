import React from "react";
import { Link, useLocation } from "react-router-dom";
import { useSesion } from "../hooks/useSesion";
import { tieneCabezera } from "../utils/tieneCabezera";
import AvatarUsuario from "./AvatarUsuario";
import BarraBusqueda from "./BarraBusqueda";

function Cabezera(): JSX.Element {
  const { sesionIniciada } = useSesion();

  const location = useLocation();

  return tieneCabezera(location.pathname) ? (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <div className="row">
          <div className="col">
            <Link to="/home" className="navbar-brand ">
              <h3>Mercado en linea</h3>
            </Link>
          </div>
          <div align="center" className="col">
            <BarraBusqueda className="form-control-lg" />
          </div>
          <div align="right" className="col">
            {sesionIniciada() ? (
              <AvatarUsuario />
            ) : (
              <Link to="/">Iniciar Sesión / Registrarse</Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  ) : (
    <div></div>
  );
}

export default Cabezera;
