import React, { FormEvent, useState } from "react";
import { Redirect, useHistory } from "react-router-dom";
import { useField } from "../hooks/useField";
import { useSesion } from "../hooks/useSesion";
import PrimerFormularioRegistro from "../components/PrimerFormularioRegistro";
import SegundoFormularioRegistro from "../components/SegundoFormularioRegistro";
import { areEmpty } from "../utils/areEmpty";

function Registro(): JSX.Element {
  const { sesionIniciada, registrarse } = useSesion();

  const [paso, setPaso] = useState<number>(1);

  const history = useHistory();

  const correo = useField({ type: "email" });
  const contrasena = useField({ type: "password" });
  const confirmarContrasena = useField({ type: "password" });
  const vendedor = useField({ type: "radio", defaultValue: "vendedor" });

  const nombre = useField({ type: "text" });
  const direccion = useField({ type: "text" });
  const genero = useField({ type: "radio", defaultValue: "PREFIERO_NO_DECIR" });

  const primerOnSubmit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (areEmpty(correo.value, contrasena.value, confirmarContrasena.value)) {
      alert("Los campos no pueden estar vacios.");
    } else if (contrasena.value !== confirmarContrasena.value) {
      alert("Las contraseñas no coinciden");
    } else {
      setPaso(paso + 1);
    }
  };

  const envioOnSubmit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (areEmpty(nombre.value, direccion.value)) {
      alert("Los campos no pueden estar vacios.");
    } else {
      registrarse({
        correo: correo.value,
        contrasena: contrasena.value,
        vendedor: vendedor.value === "vendedor",
        nombre: nombre.value,
        direccion: direccion.value,
        genero: genero.value,
      })
        .then((_) => {
          history.push("/iniciar_sesion");
          alert("El usuario ha sido registrado con exito.");
        })
        .catch((_) => {
          alert("No se pudo registrar el usuario.");
        });
    }
  };

  return sesionIniciada() ? (
    <Redirect to="/home" />
  ) : (
    <div align="center" className="container">
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div className="card border-primary mb-3">
            <div className="card-header">
              <h1>Registrarse</h1>
            </div>
            {paso === 1 ? (
              <PrimerFormularioRegistro
                correo={correo}
                contrasena={contrasena}
                confirmarContrasena={confirmarContrasena}
                vendedor={vendedor}
                onSubmit={primerOnSubmit}
              />
            ) : (
              <SegundoFormularioRegistro
                nombre={nombre}
                direccion={direccion}
                genero={genero}
                onSubmit={envioOnSubmit}
              />
            )}
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default Registro;
