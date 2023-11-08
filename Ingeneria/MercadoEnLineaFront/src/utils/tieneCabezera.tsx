export function tieneCabezera(ruta: string): boolean {
  return ruta !== "/iniciar_sesion" && ruta !== "/registrarse" && ruta !== "/";
}
