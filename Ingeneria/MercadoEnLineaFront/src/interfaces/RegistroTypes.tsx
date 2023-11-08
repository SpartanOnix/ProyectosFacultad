import { FormEvent } from "react";
import { UseFieldReturn } from "./UseFieldTypes";

export interface PrimerFormularioRegistroProps {
  correo: UseFieldReturn;
  contrasena: UseFieldReturn;
  confirmarContrasena: UseFieldReturn;
  vendedor: UseFieldReturn;
  onSubmit: (event: FormEvent<HTMLFormElement>) => void;
}

export interface RegistroSecondFormProps {
  nombre: UseFieldReturn;
  direccion: UseFieldReturn;
  genero: UseFieldReturn;
  onSubmit: (event: FormEvent<HTMLFormElement>) => void;
}
