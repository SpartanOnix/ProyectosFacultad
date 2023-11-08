import React from "react";
import { RegistroSecondFormProps } from "../interfaces/RegistroTypes";

function SegundoFormularioRegistro({
  nombre,
  direccion,
  genero,
  onSubmit,
}: RegistroSecondFormProps): JSX.Element {
  return (
    <form onSubmit={onSubmit}>
      <div>
        <input
          {...nombre}
          className="btn btn-outline-primary"
          placeholder="Nombre"
          required
        />
      </div>
      <div>
        <input
          {...direccion}
          className="btn btn-outline-primary"
          placeholder="Dirección"
          required
        />
      </div>
      <legend className="mt-4">¿Cual es su sexo?</legend>

      <div className="form-check">
        <label className="form-check-label">
          Hombre
          <input
            {...genero}
            type="radio"
            className="form-check-input"
            value="HOMBRE"
            checked={genero.value === "HOMBRE"}
          />
        </label>
      </div>
      <div className="form-check">
        <label className="form-check-label">
          Mujer
          <input
            {...genero}
            type="radio"
            className="form-check-input"
            value="MUJER"
            checked={genero.value === "MUJER"}
          />
        </label>
      </div>
      <div className="form-check">
        <label className="form-check-label">
          Otro
          <input
            {...genero}
            type="radio"
            className="form-check-input"
            value="OTRO"
            checked={genero.value === "OTRO"}
          />
        </label>
      </div>
      <div className="form-check">
        <label className="form-check-label">
          Prefiero no decir
          <input
            {...genero}
            type="radio"
            className="form-check-input"
            value="PREFIERO_NO_DECIR"
            checked={genero.value === "PREFIERO_NO_DECIR"}
          />
        </label>
      </div>
      <button className="btn btn-success" type="submit">
        Enviar
      </button>
    </form>
  );
}

export default SegundoFormularioRegistro;
