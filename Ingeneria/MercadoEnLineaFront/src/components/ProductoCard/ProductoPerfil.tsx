import React from "react";
import { ProductoCardProps } from "../../interfaces/ProductoCardProps";

function ProductoPerfil({ producto, onClick }: ProductoCardProps): JSX.Element {
  return (
    <div style={{ border: "2px solid black" }}>
      <h3 align="center">{producto.nombre}</h3>
      <img
        width="100%"
        src={import.meta.env.VITE_API_URI + "media/" + producto.icono}
        alt={producto.nombre}
      />
      <h4 align="center">
        {producto.vendido ? "Vendido" : "No ha sido vendido"}
      </h4>
      <button className="btn btn-lg btn-info" onClick={onClick}>
        Editar producto
      </button>
    </div>
  );
}

export default ProductoPerfil;
