import { ChangeEvent, FormEvent } from "react";
import { UseFieldReturn } from "./UseFieldTypes";

export interface FormularioProductoProps {
  nombre: UseFieldReturn;
  precio: UseFieldReturn;
  iconoOnChange: (event: ChangeEvent<HTMLInputElement>) => void;
  descripcionValue: string;
  descripcionOnChange: (event: ChangeEvent<HTMLTextAreaElement>) => void;
  iconoRequerido: boolean;
  onSubmit: (event: FormEvent<HTMLFormElement>) => void;
  envioOnChange: (event: ChangeEvent<HTMLSelectElement>) => void;
}
