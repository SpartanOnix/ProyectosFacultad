
public class Tablero : Object {
    //Atributos
    private Celdas[,] tablero;
    private int filas;
    private int columnas;
    private int minas;

    /**
     * Metodo constructor del tablero
     */
    public Tablero (int fila, int columna, int minas){
        this.filas = fila;
        this.columnas = columna;
        this.minas = minas;
        this.tablero = new Celdas[fila,columna];
        for(int i = 0; i< fila; i++)
            for(int j = 0; j< columna; j++)
                tablero[i,j] = new Celdas(false);
        minarCeldas(minas);
	    numerarCeldasSM();
	    asignarCoordenadas();
    }

    /**
     * Metodo para saber si la celda esta minada (para el main)
     * @return true si tiene mina y false en otro caso
     */
    public bool minada(int fila, int columna){
        return tablero[fila,columna].estaMinada();
    }

    /**
    * Metodo para saber si ya ganaron el juego
    * @return True si ya marcaron todas las minas y revelaron todas las celdas restantes
    * y false si faltan minas por marcar
    */
    public bool juegoGanado(){
        int cont_SM = 0;
        for(int i = 0; i< filas; i++){
           for(int j= 0; j<columnas; j++){
               if(tablero[i,j].estaMinada()){
                   cont_SM++;
               }
           }
        }
        return (cont_SM == ((filas*columnas)-minas));
    }

    /**
    * Metodo para revelar una Celda
    * @param fila -Fila de celda a revelar
    * @param columna -Columna de celda a revelar
    */
    public void revelar(int fila, int columna){
        if(tablero[fila,columna].estaMinada()){
            revelarMinas();
        }else{
            revelarSMinasAlrededor(fila, columna);
        }
    }

    /**
     * Metodo para marcar una Celda
     */
    public void marcar(int fila, int columna){
        if(tablero[fila,columna].revelada()){
            print("No se puede marcar la Celda \nLa celda ya esta revelada");
        }else{
            tablero[fila,columna].marcar();
        }
    }

    /**
     * Metodo para saber si una celda esta marcada
     */
    public bool estaMarcada(int fila, int columna){
        return tablero[fila,columna].estaMarcada();
    }

    /**
     * Metodo para imprimir el tablero con sus coordenadas
     * @return String El tablero de celdas
     */
    public string to_string(){
        string format = "";
       	for(int k = 0; k<columnas; k++)
			format += "--------";
		format += "+\n";
      	for(int i = 0; i<filas; i++){
        	format += "|";
        	for(int j = 0; j<columnas; j++){
          		if(tablero[i,j] == null){
            		format += " |";
          		}else{
            		format += tablero[i,j].to_string() + "|";
          		}
        	}
        	format += "\n";
	        for(int k = 0; k<columnas; k++)
	          format += "--------";
        	format += "+\n";
      	}
      	return format;
    }

    /**
     * Metodo para revelar todas las minas cuando el usuario revele una
     */
    internal void revelarMinas(){
        for (int i = 0; i<filas; i++){
            for(int j= 0; j<columnas; j++){
                if(tablero[i,j].estaMinada())
                    tablero[i,j].revelar();
            }
        }
    }

    /**
    * Metodo para revelar las Celdas que no tienen minas alrededor
    * @param fila -Fila de la celda principal
    * @param columna -Columna de la celda principal
    */
    internal void revelarSMinasAlrededor(int fila, int columna){
        int x = fila;
        int y = columna;
        tablero[fila,columna].revelar();

        //Caso 1.- Celdas superiores
        if(x-1 >= 0){
            if(y-1 >= 0){
                //Izquierda
	            if(tablero[x-1,y-1].estaMinada() == false){
		            if(!(tablero[x-1,y-1].revelada())){
			            if(tablero[x-1,y-1].getConteo() == 0){
				            revelarSMinasAlrededor(x-1,y-1);
			            }else{
				            tablero[x-1,y-1].revelar();
			            }
		            }
	            }
            }
            //Central
            if(tablero[x-1,y].estaMinada() == false){
	            if(!(tablero[x-1,y].revelada())){
		            if(tablero[x-1,y].getConteo() == 0){
			            revelarSMinasAlrededor(x-1, y);
		            }else{
			            tablero[x-1,y].revelar();
		            }
	            }
            }
            //Derecha
            if(y+1 < columnas){
	            if(tablero[x-1,y+1].estaMinada() == false){
		            if(!(tablero[x-1,y+1].revelada())){
			            if(tablero[x-1,y+1].getConteo() == 0){
				            revelarSMinasAlrededor(x-1, y+1);
			            }else{
				            tablero[x-1,y+1].revelar();
			            }
		            }
	            }
            }
        }

        //Caso 2.- Celda anterior
        if(y-1 >= 0){
            if(tablero[x,y-1].estaMinada() == false){
	            if(!(tablero[x,y-1].revelada())){
		            if(tablero[x,y-1].getConteo() == 0){
			            revelarSMinasAlrededor(x,y-1);
		            }else{
			            tablero[x,y-1].revelar();
		            }
	            }
            }
        }
        //Caso 3.- Celda siguiente
        if(y+1 < columnas){
            if(tablero[x,y+1].estaMinada() == false){
	            if(!(tablero[x,y+1].revelada())){
		            if(tablero[x,y+1].getConteo() == 0){
			            revelarSMinasAlrededor(x, y+1);
		            }else{
			            tablero[x,y+1].revelar();
		            }
	            }
            }
        }
        //Caso 4.- Celdas Inferiores
        if(x+1 < filas){
            if(y-1 >= 0){
                //Izquierda
	            if(tablero[x+1,y-1].estaMinada() == false){
		            if(!(tablero[x+1,y-1].revelada())){
			            if(tablero[x+1,y-1].getConteo() == 0){
				            revelarSMinasAlrededor(x+1,y-1);
			            }else{
				            tablero[x+1,y-1].revelar();
			            }
		            }
	            }
            }
            //Superior
            if(tablero[x+1,y].estaMinada() == false){
	            if(!(tablero[x+1,y].revelada())){
		            if(tablero[x+1,y].getConteo() == 0){
			            revelarSMinasAlrededor(x+1, y);
		            }else{
			            tablero[x+1,y].revelar();
		            }
	            }
            }
            //Derecha
            if(y+1 < columnas){
	            if(tablero[x+1,y+1].estaMinada() == false){
		            if(!(tablero[x+1,y+1].revelada())){
			            if(tablero[x+1,y+1].getConteo() == 0){
				            revelarSMinasAlrededor(x+1, y+1);
			            }else{
				            tablero[x+1,y+1].revelar();
			            }
		            }
	            }
            }
        }
    }

    /**
     * Este metodo se encarga de crear las celdas con minas y el resto sin minas
     * @param minas --Cantidad de minas que tendra el tablero
     */
    internal void minarCeldas(int minas){
        int cantidadmins = 0;
        int random_filas = Random.int_range(0,filas);
        int random_columnas = Random.int_range(0,columnas);
        tablero[random_filas, random_columnas].minar(true);
        cantidadmins++;
    }

    /**
     * Metodo para numerar las celdas que no tienen minas, es decir,
     * hacer un conteo de las minas que hay alrededor de cada celda
     */
    internal void numerarCeldasSM(){
        for(int i= 0; i<filas; i++){
		    for(int j=0; j<columnas; j++){
			    if(tablero[i,j].estaMinada() == false){
				    if(i-1 >= 0){
					    if(tablero[i-1,j].estaMinada() == false){
						    tablero[i-1,j].numerar();
					    }

					    if(j-1 >= 0){
						    if(tablero[i-1,j-1].estaMinada() == false){
							    tablero[i-1,j-1].numerar();
						    }
					    }

					    if(j+1 < columnas){
						    if(tablero[i-1,j+1].estaMinada() == false){
						        tablero[i-1,j+1].numerar();
						    }
					    }
				    }

				    if(j-1 >= 0){
					    if(tablero[i,j-1].estaMinada() == false){
						    tablero[i,j-1].numerar();
					    }
				    }

				    if(j+1 < columnas){
					    if(tablero[i,j+1].estaMinada() == false){
						    tablero[i,j+1].numerar();
					    }
				    }

				    if(i+1 < filas){
					    if(tablero[i+1,j].estaMinada() == false){
						    tablero[i+1,j].numerar();
					    }

					    if(j-1 >= 0){
						    if(tablero[i+1,j-1].estaMinada() == false){
							    tablero[i+1,j-1].numerar();
						    }
					    }

					    if(j+1 < columnas){
						    if(tablero[i+1,j+1].estaMinada() == false){
							    tablero[i+1,j+1].numerar();
						    }
					    }
				    }
			    }
		    }
	    }
    }

    /**
     * Metodo para asignarle coordenadas a las Celdas
     */
    internal void asignarCoordenadas(){
        for(int i=0; i<filas; i++){
		    for(int j=0; j< columnas; j++){
			    if((i+1) < 10 && (j+1) < 10){
				    string nombre = "( "+(i+1).to_string()+", "+(j+1).to_string()+")";
				    tablero[i,j].setNombre(nombre);
			    }
			    if((i+1)< 10 && (j+1)>= 10){
				    string nombre = "( "+(i+1).to_string()+","+(j+1).to_string()+")";
				    tablero[i,j].setNombre(nombre);
			    }
			    if((i+1)>= 10 && (j+1)<10){
				    string nombre = "("+(i+1).to_string()+", "+(j+1).to_string()+")";
				    tablero[i,j].setNombre(nombre);
			    }
			    if((i+1)>= 10 && (j+1)>= 10){
				    string nombre = "("+(i+1).to_string()+","+(j+1).to_string()+")";
				    tablero[i,j].setNombre(nombre);
			    }

		    }
	    }
    }


}

//}