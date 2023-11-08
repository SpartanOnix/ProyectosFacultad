import axios from "axios";
import { useEffect, useState } from "react";
import { ProductoRes } from "../interfaces/responses/Producto";

export const useProductos = () => {
  const [productos, setProductos] = useState<ProductoRes[]>([]);

  useEffect(() => {
    axios
      .get<ProductoRes[]>(import.meta.env.VITE_API_URI + "productos")
      .then((res) => {
        setProductos(res.data);
      })
      .catch((error) => {
        console.log(error.response.data.message);
      });
  }, []);

  return productos;
};
