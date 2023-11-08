import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import ProductoPerfil from "../components/ProductoCard/ProductoPerfil";
import { useSesion } from "../hooks/useSesion";
import { Perfil as PerfilRes } from "../interfaces/responses/Perfil";

function Perfil(): JSX.Element {
  const { usuario, perfil } = useSesion();

  const history = useHistory();

  const [info, setInfo] = useState<PerfilRes | undefined>(undefined);

  useEffect(() => {
    perfil()
      .then((res) => {
        setInfo(res);
      })
      .catch((error) => {
        alert("Hubo un error.");
        history.replace("/home");
      });
    return () => axios.CancelToken.source().cancel();
  }, [usuario]);

  return !info ? (
    <h1>Cargando</h1>
  ) : (
    <div className="list-group">
      <h1 className="list-group-item">{info.nombre}</h1>
      <h3 className="list-group-item">
        Usted es un: {info.vendedor ? "Vendedor" : "Comprador"}
      </h3>
      <div className="list-group-item">
        <span>Su correo es: {info.correo}</span>
      </div>
      <div className="list-group-item">
        <span>Su dirección es: {info.direccion}</span>
      </div>
      <div className="list-group-item">
        <div className="container">
          <div className="row">
            {info.vendedor &&
              info.productos!.map((producto) => {
                return (
                  <div
                    key={producto.id + producto.nombre + info.id}
                    className="col"
                  >
                    <ProductoPerfil
                      producto={producto}
                      onClick={() =>
                        history.replace("/actualizar/", {
                          producto,
                        })
                      }
                    />
                  </div>
                );
              })}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Perfil;
