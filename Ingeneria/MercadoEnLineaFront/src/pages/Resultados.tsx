import React, { useEffect } from "react";
import { useHistory, useLocation } from "react-router-dom";
import ProductoHome from "../components/ProductoCard/ProductoHome";
import { ProductoRes } from "../interfaces/responses/Producto";

export function Resultados(): JSX.Element {
  const location = useLocation<{ resultados: ProductoRes[] }>();
  const history = useHistory();

  useEffect(() => {
    if (!location.state) {
      alert("No hay resultados");
      history.replace("/home");
    }
  }, []);

  return (
    <div>
      <ul>
        {location.state.resultados.length === 0 ? (
          <h1 align="center">No hay resultados</h1>
        ) : (
          location.state.resultados.map((resultado) => {
            return (
              <div className="container">
                <div className="row">
                  <div className="col"></div>
                  <div className="col">
                    <ProductoHome
                      producto={resultado}
                      onClick={() =>
                        history.replace("/producto/" + resultado.id)
                      }
                    />
                  </div>
                  <div className="col"></div>
                </div>
              </div>
            );
          })
        )}
      </ul>
    </div>
  );
}
