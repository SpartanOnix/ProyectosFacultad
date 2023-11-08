// var b = "hola";
// var a = "Hola mundo";
// var obj = {
//   numero: 115,
//   texto: "help",
//
//   objHijo: {
//     numero2: 935,
//     texto2: "help'n",
//   },
// };
// var persona = {
//   nombre: "Dante",
//   apellido: "Sinencio",
//   edad: 18,
//   direccion: {
//     pais: "Mexico",
//     ciudad: "CDMX",
//     cuadra: {
//       calle: "DG",
//       manzana: 16,
//       lote: 5,
//       casa: {
//         telefono: "55-5555",
//         familiares: 10,
//       },
//     },
//   },
// };
//
// var campo = "edad";
// console.log(persona.direccion.cuadra.casa.telefono);
// var casita = persona.direccion.cuadra.casa;
// console.log(casita.telefono);
// casita.mascotas = 2;
// console.log(persona.direccion.cuadra.casa.mascotas);
// console.log(persona["direccion"]["cuadra"]["casa"]["mascotas"], b);
// console.log(persona[campo]);
//
//
//
//
//
// function imprimir(fn) {
//   fn();
// }
//
// function presionoClick() {
//   console.log("Clikea el boton");
//   console.log(obj);
// }
//
// var persona = {
//   nombre: "Dante",
//   apellido: "Sinencio",
// };
//
// imprimir(function(){
//   console.log("funcion anonima");
// });
//
//
//
//
// function creaFuncion() {
//   return function (nombre) {
//     console.log("Me creo", nombre);
//     return function (edad) {
//       console.log("tengo", edad, "años");
//     }
//   }
// }
//
// var nuevaFuncion = creaFuncion();
// var segFuncion = nuevaFuncion(persona.nombre);
// segFuncion(18);

// function Jugador(nombre) {
//   this.nombre = nombre;
//   this.hp = 100;
//   this.sp = 100;

//   this.curar = function (jugadorobj) {
//     if (this.sp >= 40) {
//       this.sp -= 40;
//       jugadorobj.hp += 20;
//     }else {
//       console.info(this.nombre, "no tiene suficiente sp");
//     }

//     this.estado(jugadorobj);
//   };

//   this.tirarFlecha = function (jugadorobj) {
//     if (this.sp >= 10) {
//       this.sp -= 10;
//       if (jugadorobj.hp > 20) {
//         jugadorobj.hp -= 20;
//       }else {
//         jugadorobj.hp = 0;
//         console.error(this.nombre, "mato a", jugadorobj.nombre);
//       }
//     }else {
//       console.info(this.nombre, "no tiene suficiente sp");
//     }

//     this.estado(jugadorobj);
//   };

//   this.estado = function (jugadorobj) {
//     console.info(this);
//     console.info(jugadorobj);
//   };
// }
//
// var mago = new Jugador("Gandalf");
// var arquero = new Jugador("Legolas");
//
// console.log(arquero);
// console.log(mago);

// Cargar el modulo HTTP
var http = require('http');

// Configurar una respuesta HTTP para todas las peticiones
function onRequest(request, response) {
  console.log('Peticion Recibida.');
  response.writeHead(200, { "Content-Type": "text/html" });
  response.write("Hola Mundo");
  response.end();
}

var server = http.createServer(onRequest);

// Escuchar al puerto 8080
server.listen(8081);

// Poner un mensaje en la consola
console.log("Servidor funcionando en http://localhost:8081/");
