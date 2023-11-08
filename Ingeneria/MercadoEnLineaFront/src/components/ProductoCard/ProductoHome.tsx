import React from "react";
import { ProductoCardProps } from "../../interfaces/ProductoCardProps";

function ProductoHome({ producto, onClick }: ProductoCardProps): JSX.Element {
  return (
    <div className="card m-3" style={{ width: "18rem" }} onClick={onClick}>
      <img
        className="card-img-top"
        src={import.meta.env.VITE_API_URI + "media/" + producto.icono}
        alt={producto.nombre}
      />
      <div className="card-body">
        <h2 className="card-title">{producto.nombre}</h2>
        <h3 className="card-text">${producto.precio}</h3>
      </div>
    </div>
  );
}

export default ProductoHome;
