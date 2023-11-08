import axios from "axios";
import React, { ChangeEvent, FormEvent, useState } from "react";
import { Redirect, useHistory, useLocation } from "react-router-dom";
import FormularioProducto from "../components/FormularioProducto";
import { useField } from "../hooks/useField";
import { useSesion } from "../hooks/useSesion";
import { ProductoRes } from "../interfaces/responses/Producto";
import { areEmpty } from "../utils/areEmpty";

function ActualizarProducto(): JSX.Element {
  const { usuario, sesionIniciada } = useSesion();

  const history = useHistory();

  const location = useLocation<{ producto: ProductoRes }>();

  if (location.state === undefined) {
    history.replace("/home");
  }

  const nombre = useField({
    type: "text",
    defaultValue: location.state?.producto.nombre ?? "",
  });
  const precio = useField({
    type: "number",
    defaultValue: location.state?.producto.precio.toString() ?? "",
  });

  const [descripcion, setDescripcion] = useState<string>(
    location.state?.producto.descripcion ?? ""
  );
  const [archivo, setArchivo] = useState<File>();
  const [envio, setEnvio] = useState<string>(
    location.state?.producto.envio ?? ""
  );

  const descripcionOnChange = (event: ChangeEvent<HTMLTextAreaElement>): void =>
    setDescripcion(event.target.value);

  const iconoOnChange = (event: ChangeEvent<HTMLInputElement>): void => {
    const archivos = event.target.files;
    if (archivos) {
      setArchivo(archivos[0]);
    }
  };

  const envioOnChange = (event: ChangeEvent<HTMLSelectElement>): void => {
    setEnvio(event.target.value);
  };

  const guardarProducto = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (areEmpty(nombre.value, descripcion, precio.value)) {
      alert("Los campos tienen que ser llenados.");
    } else {
      const formulario = new FormData();
      formulario.append("nombre", nombre.value);
      formulario.append("descripcion", descripcion);
      formulario.append("precio", precio.value);
      formulario.append("envio", envio);

      if (archivo) {
        formulario.append("icono", archivo, archivo.name);
      }

      if (location.state.producto) {
        axios
          .patch(
            import.meta.env.VITE_API_URI +
              "productos/" +
              location.state.producto.id +
              "/actualizar",
            formulario,
            {
              headers: {
                Authorization: `Bearer ${usuario!.token}`,
              },
            }
          )
          .then((_) => {
            alert("El producto se subio correctamente");
            history.replace("/perfil");
          })
          .catch((_) => {
            alert("No se pudo subir el producto.");
          });
      }
    }
  };

  return !sesionIniciada() || !usuario || !usuario.vendedor ? (
    <Redirect to="/home" />
  ) : (
    <div align="center" className="container">
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div className="card border-primary mb-3">
            <div className="card-header">
              Actualice la informacion del producto
            </div>
            <FormularioProducto
              nombre={nombre}
              descripcionValue={descripcion}
              descripcionOnChange={descripcionOnChange}
              precio={precio}
              iconoOnChange={iconoOnChange}
              iconoRequerido={false}
              onSubmit={guardarProducto}
              envioOnChange={envioOnChange}
            />
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default ActualizarProducto;
