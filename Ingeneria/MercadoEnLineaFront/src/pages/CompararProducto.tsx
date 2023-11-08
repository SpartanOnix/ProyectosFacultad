import React, { useEffect } from "react";
import ProductoCompara from "../components/ProductoCard/ProductoCompara";
import { Redirect, useHistory, useLocation } from "react-router-dom";
import { ProductoRes } from "../interfaces/responses/Producto";

function CompararProducto(): JSX.Element {
  const history = useHistory();
  const location = useLocation<{
    primerProducto: ProductoRes;
    segundoProducto: ProductoRes;
  }>();

  useEffect(() => {
    if (
      location.state &&
      location.state.primerProducto.id === location.state.segundoProducto.id
    ) {
      alert("No se puede comparar el mismo producto.");
    }
  }, []);

  return !location.state ||
    location.state.primerProducto.id === location.state.segundoProducto.id ? (
    <Redirect to="/home" />
  ) : (
    <div align="center" className="container">
      <div className="row">
        <div className="col">
          <div className="card border-primary mb-3">
            <div className="card-header">Producto 1</div>
            <ProductoCompara
              producto={location.state.primerProducto}
              onClick={() =>
                history.replace("/producto/" + location.state.primerProducto.id)
              }
            />
          </div>
        </div>
        <div className="col">
          <div className="card border-primary mb-3">
            <div className="card-header">Producto 2</div>
            <ProductoCompara
              producto={location.state.segundoProducto}
              onClick={() =>
                history.replace(
                  "/producto/" + location.state.segundoProducto.id
                )
              }
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default CompararProducto;
