import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useSesion } from "../hooks/useSesion";

function AvatarUsuario(): JSX.Element {
  const { usuario, cerrarSesion } = useSesion();

  const history = useHistory();

  const [mostrarMenu, setMostrarMenu] = useState<boolean>(false);

  const mostrarMenuOnClick = () => setMostrarMenu(!mostrarMenu);

  const perfilOnClick = () => {
    history.push("/perfil");
    setMostrarMenu(false);
  };

  const cerrarSesionOnClick = () => {
    cerrarSesion()
      .then((_) => {
        history.replace("/home");
        setMostrarMenu(false);
      })
      .catch((_) => {
        alert("Error al cerrar sesión.");
      });
  };

  const subirProductoOnClick = () => {
    history.replace("/subir");
    setMostrarMenu(false);
  };

  return (
    <div>
      <img
        onClick={mostrarMenuOnClick}
        src="https://www.dondeir.com/wp-content/uploads/2020/12/pasarela-virtual-de-perros-y-gatos-en-adopcion.jpg"
        alt="usuario"
        width="13%"
        style={{ borderRadius: "100%" }}
      />

      {mostrarMenu && (
        <div>
          {usuario!.vendedor && (
            <div>
              <button className="btn btn-primary" onClick={subirProductoOnClick}>Subir producto</button>
            </div>
          )}
          <div>
            <button className="btn btn-info" onClick={perfilOnClick}>Perfil</button>
          </div>
          <div>
            <button className="btn btn-danger" onClick={cerrarSesionOnClick}>Cerrar sesion</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default AvatarUsuario;
