class Main : Object{
    private Tablero tab;
    private JuegodePartida juega;


    public void submenu(){
        int opcion_2 = 0;
        stdout.printf("1.- Partida normal\n");
        stdout.printf("2.- Partida personalizada\n");
        stdin.scanf("%d", out opcion_2);
        switch(opcion_2){
            case 1:
            {
                //Aqui se construye el tablero 8x8
                print("El tablero se construirá de 8x8 con 8 minas");
                tab = new Tablero(8,8,8);
                juega = new JuegodePartida(tab);
                break;
            }
            case 2:
            {
                bool repetidor_error = true;
                int fila = 0, columna = 0, cant_mins = 0;
                while(repetidor_error){
                    //Aqui se construye el tablero de acuerdo a las caracteristicas del usuario
                    print("Ingresa el numero de filas :\n");
                    stdin.scanf("%d", out fila);
                    print("Ingresa el numero de columnas: \n");
                    stdin.scanf ("%d", out columna);
                    print("Ingresa la cantidad de minas que quieres: \n");
                    stdin.scanf ("%d", out cant_mins);
                    if(cant_mins < 8 || fila < 8 || columna <8){
                        print("Datos incorrectos, ninguna opcion puede ser menor que 8\n");
                    }else{
                        repetidor_error = false;
                    }
                }
                tab = new Tablero(fila, columna, cant_mins);
                juega = new JuegodePartida(tab);
                stdout.printf(tab.to_string());
                break;
            }
            default:
            {
                stdout.printf("Elige una opcion valida");
                break;
            }
        }
    }


    public static void main (string[] args){
        int opcion_1 = 0;
        //bool repetidor = false;
        //do{
        print("\033[H\033[2J");
        stdout.printf("Bienvenido al Buscaminas V2\n");
        stdout.printf("Elija lo que quiera hacer\n" + "1.- Jugar un nuevo juego\n"  + "2.- Salir\n");
        Main m = new Main();
        stdin.scanf("%d", out opcion_1);
        switch((int)opcion_1)
        {
            case 1:
            {
                //Aqui debe de ir la inicializacion del juego
                m.submenu();
                //repetidor = true;
                break;
            }
            case 2:
            {
                //repetidor = false;
                print("Hasta luego");
                break;
            }
            default:
            {
                stdout.printf("Eliga una opcion valida");
                break;
            }
        }
        //}while(repetidor);

    }
}
//}