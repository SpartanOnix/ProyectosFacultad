import { FormEvent } from "react";
import { UseFieldReturn } from "./UseFieldTypes";

export interface FormularioCompraProps {
  numeroTarjeta: UseFieldReturn;
  codigoSeguridad: UseFieldReturn;
  mesExp: UseFieldReturn;
  anhoExp: UseFieldReturn;
  nombre: UseFieldReturn;
  apellido: UseFieldReturn;
  localidad: UseFieldReturn;
  direccionFacturacion: UseFieldReturn;
  direccionFacturacionSegundaLinea: UseFieldReturn;
  codigoPostal: UseFieldReturn;
  telefono: UseFieldReturn;
  onSubmit: (event: FormEvent<HTMLFormElement>) => void;
}
