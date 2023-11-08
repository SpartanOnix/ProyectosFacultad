import React from "react";
import { Link, Redirect } from "react-router-dom";
import { useSesion } from "../hooks/useSesion";

function Inicio(): JSX.Element {
  const { sesionIniciada } = useSesion();

  return sesionIniciada() ? (
    <Redirect to="/home" />
  ) : (
    <div align="center" className="container">
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div align="center" className="card border-primary mb-3">
            <div className="card-header">
              <h1>Mercado en línea</h1>
            </div>
            <Link className="btn btn-success" to="/iniciar_sesion">
              Iniciar sesion
            </Link>
            <h1>ó</h1>
            <Link className="btn btn-info" to="/registrarse">
              Registrarse
            </Link>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default Inicio;
