import axios from "axios";
import { useEffect, useState } from "react";
import { ProductoRes } from "../interfaces/responses/Producto";

export const useProducto = (id: string) => {
  const [producto, setProducto] = useState<ProductoRes | undefined>(undefined);
  const [envio, setEnvio] = useState<boolean>(false);

  useEffect(() => {
    axios
      .get<ProductoRes>(import.meta.env.VITE_API_URI + "productos/" + id)
      .then((res) => {
        setProducto(res.data);
      })
      .catch((_) => {
        console.log("error");
      });
  }, [envio]);

  const comentar = (id: string, opinion: string, token: string): void => {
    axios
      .post(
        import.meta.env.VITE_API_URI + "opiniones/create/" + id,
        { opinion },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((_) => {
        setEnvio(!envio);
      })
      .catch((_) => {
        alert("Error de conexión");
      });
  };

  return { producto, comentar };
};
