import axios from "axios";
import React, { ChangeEvent, FormEvent, useState } from "react";
import { Redirect, useHistory } from "react-router-dom";
import FormularioProducto from "../components/FormularioProducto";
import { useField } from "../hooks/useField";
import { useSesion } from "../hooks/useSesion";
import { areEmpty } from "../utils/areEmpty";

function SubirProducto(): JSX.Element {
  const { usuario, sesionIniciada } = useSesion();

  const history = useHistory();

  const nombre = useField({ type: "text" });
  const precio = useField({ type: "number" });

  const [descripcion, setDescripcion] = useState<string>("");
  const [archivo, setArchivo] = useState<File>();
  const [envio, setEnvio] = useState<string>("DHL");

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
      formulario.append("icono", archivo!, archivo!.name);

      axios
        .post(import.meta.env.VITE_API_URI + "productos/subir", formulario, {
          headers: {
            Authorization: `Bearer ${usuario!.token}`,
          },
        })
        .then((_) => {
          alert("El producto se subio correctamente");
          history.replace("/perfil");
        })
        .catch((_) => {
          alert("No se pudo subir el producto.");
        });
    }
  };

  return !sesionIniciada() || !usuario || !usuario.vendedor ? (
    <Redirect to="/home" />
  ) : (
    <div align="center" className="container">
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div className="card border-info mb-3">
            <div className="card-header">
              Ingrese la informacion de su producto
            </div>
            <FormularioProducto
              nombre={nombre}
              descripcionValue={descripcion}
              descripcionOnChange={descripcionOnChange}
              precio={precio}
              iconoOnChange={iconoOnChange}
              iconoRequerido={true}
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

export default SubirProducto;
