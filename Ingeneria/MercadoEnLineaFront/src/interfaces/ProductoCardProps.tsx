import { ProductoRes } from "./responses/Producto";

export interface ProductoCardProps {
  producto: ProductoRes;
  onClick: () => void;
}
