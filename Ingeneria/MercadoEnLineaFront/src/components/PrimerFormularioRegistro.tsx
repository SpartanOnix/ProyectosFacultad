import React from "react";
import { PrimerFormularioRegistroProps } from "../interfaces/RegistroTypes";

function PrimerFormularioRegistro({
  correo,
  contrasena,
  confirmarContrasena,
  onSubmit,
  vendedor,
}: PrimerFormularioRegistroProps): JSX.Element {
  return (
    <form onSubmit={onSubmit}>
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
      <div>
        <input
          {...confirmarContrasena}
          className="btn btn-outline-primary"
          placeholder="Confirmar contraseña"
          required
        />
      </div>
      <legend className="mt-4">¿Que tipo de cuenta quiere?</legend>
      <div className="form-check">
        <label className="form-check-label">
          Vendedor
          <input
            {...vendedor}
            type="radio"
            className="form-check-input"
            value="vendedor"
            checked={vendedor.value === "vendedor"}
          />
        </label>
      </div>
      <div className="form-check">
        <label className="form-check-label">
          Comprador
          <input
            {...vendedor}
            type="radio"
            className="form-check-input"
            value="comprador"
            checked={vendedor.value === "comprador"}
          />
        </label>
      </div>
      <button className="btn btn-info" type="submit">Siguiente</button>
    </form>
  );
}

export default PrimerFormularioRegistro;
