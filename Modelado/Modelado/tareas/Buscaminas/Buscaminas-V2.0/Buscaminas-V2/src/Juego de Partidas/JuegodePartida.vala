public class JuegodePartida : Object {
    private Tablero tablero;
    public JuegodePartida (Tablero tab) {
        tablero = tab;
        inicioJuego();
    }

    private void inicioJuego(){
        int fila = 0;
        int columna = 0;
        //string comando = "";
        bool repetidor = true;
        do{
            print("\033[H\033[2J");
            muestraInstrucciones();
            print(tablero.to_string());
            print("Ingresa el comando que desees realizar :\n");
            int comando = 0;
            //comando = stdin.read_line();
            stdin.scanf("%d", out comando);
            //print(comando);
            print("Paso la lectura\n");
            print("Introduce la fila: ");
            stdin.scanf("%d", out fila);
            print("\nIntroduce la columna: ");
            stdin.scanf("%d", out columna);
            print("\n");
            //stdout.printf ("Hello, %s!\n", comando_1);
            //print(comando_1.up());
            //fila = fila -1;https://github.com/99joseluis/Buscaminas-V2.0.git
            //columna = columna-1;
            switch(comando){
                case 1:
                    print("Revelar\n");
                    if(tablero.estaMarcada(fila-1,columna-1)){
                        print("Esta celda no se puede revelar");
                    }else{
                        if(tablero.minada(fila-1,columna-1)){
                            repetidor = false;
                            tablero.revelar(fila-1, columna-1);
                            print("\033[H\033[2J");
                            print(tablero.to_string());
                            print("Perdiste, Intentalo nuevamente");
                        }else{
                            tablero.revelar(fila-1, columna-1);
                            if(tablero.juegoGanado()){
                                print("Felicidades acabas de ganar");
                                repetidor = false;
                            }

                        }
                    }
                    break;
                case 2:
                    print("Marcar");
                    tablero.marcar(fila-1,columna-1);
                    break;
                case 3:
                    print("Desmarcar");
                    tablero.marcar(fila-1, columna-1);
                    break;
                case 4:
                    print("Hasta Luego, Vuelve pronto.\n");
                    repetidor = false;
                    break;
                default :
                    print("Opcion incorrecta, mete solo el numero de la accion\n");
                break;
            }

        }while(repetidor);
    }


    private void muestraInstrucciones(){
        print("\033[H\033[2J");
        print("-------------------------------------------------------------------\n");
        print("---------------- INSTRUCCIONES ----------------------\n");
        print("-------------------------------------------------------------------\n");
        print("----------------    INSTRUCCION     ---------------------\n");
        print("----------------          1.Revelar           ---------------------\n");
        print("----------------          2.Marcar           ----------------------\n");
        print("----------------          3.Desmarcar     ---------------------\n");
        print("----------------          4.Salir               ---------------------\n");
        print("-------------------------------------------------------------------\n");
    }
}
