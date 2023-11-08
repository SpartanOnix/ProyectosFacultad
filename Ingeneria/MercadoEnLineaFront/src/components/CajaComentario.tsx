import React, { ChangeEvent, FormEvent, useState } from "react";
import { useSesion } from "../hooks/useSesion";

type ComentarFnType = (id: string, opinion: string, token: string) => void;

function CajaComentario({
  id,
  comentarFn,
}: {
  id: string;
  comentarFn: ComentarFnType;
}): JSX.Element {
  const { usuario } = useSesion();

  const [opinion, setOpinion] = useState<string>("");

  const opinionOnChange = (event: ChangeEvent<HTMLTextAreaElement>): void => {
    setOpinion(event.target.value);
  };

  const opinionOnSubmit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    comentarFn(id, opinion, usuario!.token);
    setOpinion("");
  };

  return (
    <div>
      <form onSubmit={opinionOnSubmit}>
        <textarea
          className="form-control form-control-sm"
          value={opinion}
          onChange={opinionOnChange}
          placeholder="Danos tu opinion sobre el producto"
          maxLength={140}
        />
        <button className="btn btn-lg btn-info" align="center" type="submit">Comentar</button>
      </form>
    </div>
  );
}

export default CajaComentario;
