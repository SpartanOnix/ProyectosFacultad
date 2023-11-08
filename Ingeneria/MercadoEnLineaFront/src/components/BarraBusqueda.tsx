import axios from "axios";
import React, { FormEvent, useState } from "react";
import { useHistory } from "react-router";
import { useField } from "../hooks/useField";
import { ProductoRes } from "../interfaces/responses/Producto";
import { areEmpty } from "../utils/areEmpty";

function BarraBusqueda(props: any): JSX.Element {
  const busqueda = useField({ type: "text" });
  const history = useHistory();

  const busquedaOnSubmit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (!(busqueda.value === "" || areEmpty(busqueda.value))) {
      axios
        .get<ProductoRes[]>(
          import.meta.env.VITE_API_URI + "productos/buscar?q=" + busqueda.value
        )
        .then((res) => {
          history.replace("/resultados", { resultados: res.data });
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div>
      <form onSubmit={busquedaOnSubmit} className="form-inline my-2 my-lg-0">
        <input {...busqueda} placeholder="Buscar..." {...props} />
      </form>
    </div>
  );
}

export default BarraBusqueda;
