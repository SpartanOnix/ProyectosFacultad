import React, { FormEvent } from "react";
import { Redirect, useHistory } from "react-router-dom";
import { useField } from "../hooks/useField";
import { useSesion } from "../hooks/useSesion";
import { areEmpty } from "../utils/areEmpty";

function InicioSesion(): JSX.Element {
  const { sesionIniciada, iniciarSesion } = useSesion();

  const history = useHistory();

  const correo = useField({ type: "email" });
  const contrasena = useField({ type: "password" });

  const iniciarSesionOnSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (areEmpty(correo.value, contrasena.value)) {
      alert("Tiene que llenar los campos.");
    } else {
      iniciarSesion(correo.value, contrasena.value)
        .then((_) => {
          history.push("/home");
        })
        .catch((_) => {
          alert("No se pudo iniciar sesión.");
        });
    }
  };

  return sesionIniciada() ? (
    <Redirect to="/home" />
  ) : (
    <div className="container">
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div align="center" className="card border-primary mb-3">
            <div className="card-header">
              <h1>IniciarSesión</h1>
            </div>
            <form onSubmit={iniciarSesionOnSubmit}>
              <div>
                <input
                  {...correo}
                  className="btn btn-outline-primary"
                  placeholder="Correo"
                  required
                />
              </div>
              <div>
                <input
                  {...contrasena}
                  className="btn btn-outline-primary"
                  placeholder="Contraseña"
                  required
                />
              </div>
              <button className="btn btn-success" type="submit">
                Iniciar sesión
              </button>
            </form>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default InicioSesion;
