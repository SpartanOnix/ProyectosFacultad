// Variable para llevar el conteo de productos en el carrito.
var productosCarrito = 0;

// Variable auxiliar para el auto clikeo.
var cliker = document.getElementById("clickear");

// Varible para guardar la cantidad de productos comprados.
function parseaPrecios(cadena){
    let auxparse = cadena;
    let x = "";
    for(const n of auxparse){
        if(n == "$"){
            x = "";
        }else{
            x = x + n;
        }
    }
    return parseFloat(x);
}

// Variable para aumentar el contador de productos en el carrito.
var tarjeta = document.getElementById("tarjetas");

// Variable para guardar el precio total. 
var precioTotal = 0;

// Expresion para sacar los valores de los nombre y el precio total.
for(const producto of tarjeta.children){
    for(const info of producto.children){
        // Variable para guardar los precios de los productos.
        let precioCarrito = 0;
        // Variable para concatenar los objetos comprados.
        let names = "";
        for(const aux of info.children){
            for(const aux1 of aux.children){
                if(aux1.children[0].className == "fw-bolder"){
                    //precios = parseaPrecios(info.innerText);
                    names = aux1.children[0].innerText + ", ";
                }
                if(aux1.children[2] && aux1.children[2].className == "lead text-white-90"){
                    precioCarrito += parseaPrecios(aux1.children[2].innerText);
                }else if(aux1.children[3] && aux1.children[3].className == "lead text-white-90"){
                    precioCarrito += parseaPrecios(aux1.children[3].innerText);
                }
                if(aux1.children[0].className == "btn btn-outline-light mt-auto"){
                    if(aux1.children[0].innerText == "Agregar al carrito"){
                        aux1.children[0].addEventListener("click", function() {
                            productosCarrito += 1;
                            precioTotal = precioTotal + precioCarrito;
                            document.getElementById("cart").innerHTML = productosCarrito;
                            document.getElementById("cantidad").innerHTML = "Estos son tus productos a comprar: " + productosCarrito;
                            document.getElementById("productos").innerHTML +=  names;
                            document.getElementById("costo").innerHTML = "Total a pagar: $" + precioTotal;
                        })
                    }
                }
            }
        }
    }
}

// Expresion para abrir automaticamente la alerta de compra cancelada.
$('#ventana').on('show.bs.modal', function (e) {
    setTimeout(function(){
        cliker.click();
    }, 5000)
})