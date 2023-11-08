import React from "react";
import { ProductoCardProps } from "../../interfaces/ProductoCardProps";

function ProductoCompara({
  producto,
  onClick,
}: ProductoCardProps): JSX.Element {
  return (
    <div>
      <img
        width="100%"
        src={import.meta.env.VITE_API_URI + "media/" + producto.icono}
        alt={producto.nombre}
      />
      <h3>{producto.nombre}</h3>
      <h4>${producto.precio}</h4>
      <h4>Descripción del producto:</h4>
      <h5>{producto.descripcion}</h5>
      <button className="btn btn-success" onClick={onClick}>
        Comprar producto
      </button>
    </div>
  );
}

export default ProductoCompara;
