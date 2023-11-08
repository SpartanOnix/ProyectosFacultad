/**
 * Clase para englobar los metodos que tienen en conjunto las Celdas con y sin minas
 */
//namespace Buscaminas.CeldasTablero{
public class Celdas : Object{
    //Atributos:
    // Para llevar el control de las Celdas descubiertas
    private bool descubierta = false;
    //Para llevar un control de las Celdas marcadas
    private bool marcada = false;
    //Para llevar el control de la posicion de las Celdas (Fila, Columna)
    private string nombre = "";
    //Para saber si la celda esta minada
    private bool minada = false;
    //Para llevar un conteo con las celdas sin minas
    private int conteo = 0;

    /**
     * Metodo constructor
     * @param mina True si la celda conteendra una mina y False en otro caso
     */
    public Celdas(bool mina){
        minada = mina;
    }


    /**
     * Metodo para minar una Celda
     * @param mina True si contiene una mina
     */
    public void minar(bool mina){
        this.minada = mina;
    }

    /**
     * Metodo para revelar la celda indistintamente si esta minada o no
     */
    public void revelar(){
	    descubierta = true;
    }

    /**
     * Metodo para marcar la celda indistintamente si esta minada o no
     */
    public void marcar(){
	    if(marcada){
	        marcada = false;
	    }else{
	        marcada = true;
	    }
    }

    /**
     * Metodo para asignar el nombre a una Celda
     * @param nombre_nuevo Coordenadas de la posicion de la celda dentro del tablero
     */
    public void setNombre(string nombre_nuevo){
	    nombre = nombre_nuevo;
    }

    /**
     * Metodo para obtener el nombre de una Celda
     * @return String.- Coordenadas con la posicion de la celda dentro del tablero
     */
    public string getNombre(){
	    return nombre;
    }

    /**
     * Metodo para saber si ya fue revelada una celda
     * @return True si ya fue revelada y False en otro caso
     */
    public bool revelada(){
        return descubierta;
    }

    /**
     * Metodo para saber si ya fue marcada una Celda
     * @return True si esta marcada y False en otro caso
     */
    public bool estaMarcada(){
        return marcada;
    }

    /**
     *  Metodo para saber que la Celda tiene una mina
     * @return True si tiene mina y False en otro caso
     */
    public bool estaMinada(){
        return minada;
    }

     /**
     * Metodo para aumentar el numero de minas alrededor de la celda
     */
    public void numerar(){
        conteo++;
    }

    /**
     * Metodo para obtener el numero de minas alrededor de la Celda
     * @return Numero de minas que tiene a su alrededor
     */
    public int getConteo(){
        return conteo;
    }

    /**
     * Metodo para imprimir las celdas en sus distintas formas, ya sean las minadas
     * o las que no contienen minas
     * @return String de las celdas
     */
    public string to_string(){
        if(minada){
            if(descubierta){
                return "   💣  ";
            }else
                if(marcada){
                    return "  🚩   ";
                }else{
                    return getNombre();
                }
        }else{
            if(descubierta){
                return "  "+conteo.to_string()+"  ";
            }else
                if(marcada){
                    return "  🚩   ";
                }else{
                    return getNombre();
                }
        }
    }
}
//}