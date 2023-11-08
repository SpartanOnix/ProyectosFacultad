import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { ProvideSesion } from "./hooks/useSesion";
import Cabezera from "./components/Cabezera";
import ActualizarProducto from "./pages/ActualizarProducto";
import Compra from "./pages/Compra";
import Home from "./pages/Home";
import Inicio from "./pages/Inicio";
import InicioSesion from "./pages/InicioSesion";
import Perfil from "./pages/Perfil";
import Producto from "./pages/Producto";
import Registro from "./pages/Registro";
import SubirProducto from "./pages/SubirProducto";
import CompararProducto from "./pages/CompararProducto";
import { Resultados } from "./pages/Resultados";

function App(): JSX.Element {
  return (
    <ProvideSesion>
      <Router>
        <div>
          <Cabezera />
          <div style={{ padding: "15px" }}>
            <Switch>
              <Route path="/resultados">
                <Resultados />
              </Route>
              <Route path="/comparar">
                <CompararProducto />
              </Route>
              <Route path="/actualizar">
                <ActualizarProducto />
              </Route>
              <Route path="/subir">
                <SubirProducto />
              </Route>
              <Route path="/perfil">
                <Perfil />
              </Route>
              <Route path="/compra/:id">
                <Compra />
              </Route>
              <Route path="/producto/:id">
                <Producto />
              </Route>
              <Route path="/home">
                <Home />
              </Route>
              <Route path="/registrarse">
                <Registro />
              </Route>
              <Route path="/iniciar_sesion">
                <InicioSesion />
              </Route>
              <Route path="/">
                <Inicio />
              </Route>
              <Route component={() => <h1>Error 404</h1>} />
            </Switch>
          </div>
        </div>
      </Router>
    </ProvideSesion>
  );
}

export default App;
