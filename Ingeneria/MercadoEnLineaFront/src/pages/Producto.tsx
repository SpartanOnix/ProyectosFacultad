import React from "react";
import { useHistory, useParams } from "react-router-dom";
import CajaComentario from "../components/CajaComentario";
import { useProducto } from "../hooks/useProducto";
import { useSesion } from "../hooks/useSesion";

function Producto(): JSX.Element {
  const { usuario } = useSesion();
  const { id } = useParams<{ id: string }>();
  const { producto, comentar } = useProducto(id);

  const history = useHistory();

  return !producto ? (
    <h1>Cargando</h1>
  ) : (
    <div className="container">
      <div className="row">
        <div className="col-sm">
          <div className="card border-primary mb-3">
            <div className="card-header">
              <h1 align="center">{producto.nombre}</h1>
            </div>
            <img
              width="100%"
              src={import.meta.env.VITE_API_URI + "media/" + producto.icono}
              alt={producto.nombre}
            />
          </div>
        </div>
        <div className="col-sm">
          <div className="card border-info mb-3">
            <div className="card-header" align="center">
              Descripcción
            </div>
            <div className="card-body">
              <p className="card-text" align="center">
                {producto.descripcion}
              </p>
            </div>
          </div>
          <div className="list-group">
            <a href="#" className="list-group-item active" align="center">
              Opiniones
            </a>
            <a href="#" className="list-group-item" align="center">
              {producto.opiniones!.map((opinion) => (
                <h6
                  key={
                    opinion.id.toString() + opinion.usuario + producto.nombre
                  }
                >
                  {opinion.opinion}
                </h6>
              ))}
            </a>
          </div>
        </div>
        <div className="col-sm">
          <div className="card border-primary mb-3">
            <div className="card-header" align="center">
              Precio estimado:
              <h3>${producto.precio}</h3>
            </div>
            <div className="d-grid gap-2">
              <button
                className="btn btn-lg btn-dark"
                onClick={() =>
                  history.replace("/home", { primerProducto: producto })
                }
              >
                Comparar producto
              </button>
            </div>
            {!producto.vendido && !usuario!.vendedor && (
              <div className="d-grid gap-2">
                <button
                  className="btn btn-lg btn-success"
                  onClick={() =>
                    history.replace("/compra/" + id, {
                      vendido: producto.vendido,
                      precio: producto.precio,
                    })
                  }
                >
                  Comprar Producto
                </button>
              </div>
            )}
            <div className="form-group">
            {usuario && !usuario.vendedor && (
              <CajaComentario id={id} comentarFn={comentar} />
            )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Producto;
