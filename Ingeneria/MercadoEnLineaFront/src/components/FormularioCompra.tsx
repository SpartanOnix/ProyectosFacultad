import React from "react";
import { FormularioCompraProps } from "../interfaces/CompraTypes";

function FormularioCompra({
  numeroTarjeta,
  codigoSeguridad,
  mesExp,
  anhoExp,
  nombre,
  apellido,
  localidad,
  direccionFacturacion,
  direccionFacturacionSegundaLinea,
  codigoPostal,
  telefono,
  onSubmit,
}: FormularioCompraProps): JSX.Element {
  return (
    <form onSubmit={onSubmit}>
      <div className="container">
        <div className="row">
          <div className="col-sm">
            <div align="center" className="card border-primary mb-3">
              <div className="card-header">
                Informacion de tu tarjeta de credito
              </div>
              <div>
                <label>Metodo de pago: |</label>
                <select className="btn btn-outline-primary">
                  <option value="visa">Visa</option>
                  <option value="master_card">MasterCard</option>
                </select>
              </div>
              <div>
                <label>
                  Número de tarjeta |
                  <input
                    {...numeroTarjeta}
                    className="btn btn-outline-primary"
                    inputMode="numeric"
                    pattern="[0-9\s0-9\s0-9\s0-9]{19}"
                    placeholder="0000 0000 0000 0000"
                    maxLength={19}
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Codigo de seguridad |
                  <input
                    {...codigoSeguridad}
                    className="btn btn-outline-primary"
                    pattern="[0-9]{3}"
                    maxLength={3}
                    placeholder="Codigo de seguridad"
                    required
                  />
                </label>
              </div>
              <div>
                <label>Fecha de caducidad</label>
                <div>
                  <input
                    {...mesExp}
                    className="btn btn-outline-primary"
                    pattern="[0-9]{2}"
                    maxLength={2}
                    placeholder="--"
                    required
                  />
                </div>
                <div>
                  <input
                    {...anhoExp}
                    className="btn btn-outline-primary"
                    pattern="[0-9]{4}"
                    maxLength={4}
                    placeholder="----"
                    required
                  />
                </div>
              </div>
              <button align="center" className="btn btn-success" type="submit">
                Confirmar
              </button>
            </div>
          </div>
          <div className="col-sm">
            <div align="center" className="card border-primary mb-3">
              <div className="card-header">Información de facturacion</div>
              <div>
                <label>
                  Nombre |
                  <input
                    {...nombre}
                    className="btn btn-outline-primary"
                    placeholder="Nombre"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Apellido |
                  <input
                    {...apellido}
                    className="btn btn-outline-primary"
                    placeholder="Apellido"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Localidad |
                  <input
                    {...localidad}
                    className="btn btn-outline-primary"
                    placeholder="Localidad"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Dirección de facturación |
                  <input
                    {...direccionFacturacion}
                    className="btn btn-outline-primary"
                    placeholder="Dirección de facturación"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Dirección de faturación (Segunda linea) |
                  <input
                    {...direccionFacturacionSegundaLinea}
                    className="btn btn-outline-primary"
                    placeholder="Dirección de faturación (Segunda linea)"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Codigo Postal |
                  <input
                    {...codigoPostal}
                    className="btn btn-outline-primary"
                    placeholder="Codigo Postal"
                    required
                  />
                </label>
              </div>
              <div>
                <label>
                  Telefono |
                  <input
                    {...telefono}
                    className="btn btn-outline-primary"
                    placeholder="Telefono"
                    required
                  />
                </label>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  );
}

export default FormularioCompra;
