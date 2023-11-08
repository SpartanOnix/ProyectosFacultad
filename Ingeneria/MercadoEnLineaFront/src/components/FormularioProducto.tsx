import React from "react";
import { Link } from "react-router-dom";
import { FormularioProductoProps } from "../interfaces/FormularioProductoProps";

function FormularioProducto({
  nombre,
  precio,
  descripcionValue,
  descripcionOnChange,
  iconoOnChange,
  iconoRequerido,
  onSubmit,
  envioOnChange,
}: FormularioProductoProps): JSX.Element {
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
        <textarea
          className="form-control"
          value={descripcionValue}
          onChange={descripcionOnChange}
          placeholder="Descripción"
          maxLength={350}
          required
        />
      </div>
      <div>
        <input
          {...precio}
          className="btn btn-outline-primary"
          placeholder="Precio"
          required
        />
      </div>
      <div>
        <label>Metodo de envio: |</label>
        <select className="btn btn-outline-primary" onChange={envioOnChange}>
          <option value="DHL">DHL</option>
          <option value="Fedex">Fedex</option>
          <option value="Correos de México">Correos de México</option>
        </select>
      </div>
      <div>
        <label>Suba una imagen del producto:</label>
      </div>
      <div className="form-group">
        <input
          className="form-control"
          type="file"
          onChange={iconoOnChange}
          required={iconoRequerido}
        />
      </div>
      <Link className="btn btn-danger" to="/home">
        Cancelar
      </Link>
      <button className="btn btn-success" type="submit">
        Guardar Producto
      </button>
    </form>
  );
}

export default FormularioProducto;
