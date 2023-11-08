import axios from "axios";
import React, { FormEvent, useEffect, useState } from "react";
import { Redirect, useHistory, useLocation, useParams } from "react-router-dom";
import FormularioCompra from "../components/FormularioCompra";
import { useField } from "../hooks/useField";
import { useSesion } from "../hooks/useSesion";
import { areEmpty } from "../utils/areEmpty";

function Compra(): JSX.Element {
  const { usuario, sesionIniciada } = useSesion();
  const { id } = useParams<{ id: string }>();

  const [cargando, setCargando] = useState<boolean>(false);

  const history = useHistory();
  const location = useLocation<{ vendido: boolean; precio: number }>();

  const numeroTarjeta = useField({ type: "tel" });
  const codigoSeguridad = useField({ type: "tel" });
  const mesExp = useField({ type: "tel" });
  const anhoExp = useField({ type: "tel" });
  const nombre = useField({ type: "text" });
  const apellido = useField({ type: "text" });
  const localidad = useField({ type: "text" });
  const direccionFacturacion = useField({ type: "text" });
  const direccionFacturacionSegundaLinea = useField({ type: "text" });
  const codigoPostal = useField({ type: "text" });
  const telefono = useField({ type: "text" });

  useEffect(() => {
    if (!usuario && !sesionIniciada()) {
      alert("No ha iniciado sesión.");
    }
  }, []);

  const confirmarOnSubmit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (
      areEmpty(
        numeroTarjeta.value,
        codigoSeguridad.value,
        mesExp.value,
        anhoExp.value,
        nombre.value,
        apellido.value,
        localidad.value,
        direccionFacturacion.value,
        direccionFacturacionSegundaLinea.value,
        codigoPostal.value,
        telefono.value
      )
    ) {
      alert("Los campos no tienen que estar vacios");
    } else {
      setCargando(true);
      axios
        .post(
          import.meta.env.VITE_API_URI + "productos/" + id + "/comprar",
          {},
          {
            headers: {
              Authorization: `Bearer ${usuario!.token}`,
            },
          }
        )
        .then((_) => {
          alert("Compra realizada con exito");
          setCargando(false);
          history.replace("/producto/" + id);
        })
        .catch((error) => {
          console.log(error.response.data.message);
          alert("Error al realizar la compra");
          setCargando(false);
          // history.replace("/producto/" + id);
        });
    }
  };

  return !sesionIniciada() || !usuario || usuario.vendedor ? (
    <Redirect to={"/producto/" + id} />
  ) : (
    <div>
      <h1>Total a pagar ${location.state.precio}</h1>
      {cargando ? (
        <h1>Cargando</h1>
      ) : (
        <FormularioCompra
          numeroTarjeta={numeroTarjeta}
          codigoSeguridad={codigoSeguridad}
          mesExp={mesExp}
          anhoExp={anhoExp}
          nombre={nombre}
          apellido={apellido}
          localidad={localidad}
          direccionFacturacion={direccionFacturacion}
          direccionFacturacionSegundaLinea={direccionFacturacionSegundaLinea}
          codigoPostal={codigoPostal}
          telefono={telefono}
          onSubmit={confirmarOnSubmit}
        />
      )}
    </div>
  );
}

export default Compra;
