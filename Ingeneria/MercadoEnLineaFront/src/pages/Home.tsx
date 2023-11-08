import React from "react";
import { useHistory, useLocation } from "react-router-dom";
import ProductoHome from "../components/ProductoCard/ProductoHome";
import { useProductos } from "../hooks/useProductos";
import { ProductoRes } from "../interfaces/responses/Producto";

function Home(): JSX.Element {
  const productos = useProductos();

  const history = useHistory();

  const location = useLocation<{ primerProducto: ProductoRes }>();

  return (
    <div className="container">
      <div className="row">
        {productos.map((producto) => (
          <div key={producto.id + producto.nombre} className="col">
            <ProductoHome
              producto={producto}
              onClick={() => {
                if (location.state) {
                  history.replace("/comparar", {
                    primerProducto: location.state.primerProducto,
                    segundoProducto: producto,
                  });
                } else {
                  history.replace("/producto/" + producto.id);
                }
              }}
            />
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;
