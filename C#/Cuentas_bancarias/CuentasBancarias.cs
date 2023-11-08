using System;

namespace Programa
{
    class CuentaBancaria
    {
        public string titular;
        public string numeroCuenta;
        public string nip;
        public double saldo;

        public CuentaBancaria(string titular, string nip, double saldo)
        {
            this.titular = titular;
            this.nip = nip;
            this.saldo = saldo;
            this.numeroCuenta = CrearNumeroCuenta();
        }

        public CuentaBancaria(string titular, string nip)
        {
            this.titular = titular;
            this.nip = nip;
            this.saldo = 0;
            this.numeroCuenta = CrearNumeroCuenta();
        }

        public string CrearNumeroCuenta()
        {
            string num = "";
            Random random = new Random();
            for (int i = 0; i < 16; i++)
            {
                num += random.Next(0, 9);
            }
            return num;
        } 

        public void Ingresar(double saldo)
        {
            this.saldo += saldo;
        }

        public void Retirar(double saldo)
        {
            if(saldo <= this.saldo)
            {
                this.saldo -= saldo;
            }
            else
            {
                Console.WriteLine("No tienes suficiente saldo");
            }
        }

        public override string ToString()
        {
            return "Titular: " + titular + ", Numero de cuenta: " + numeroCuenta + ", saldo: " + saldo;
        }

        public static void Main(string[] args)
        {
            int opcion;
            bool bandera = true;
            bool bandera2 = true;
            CuentaBancaria cuenta = null;
            string operaciones = "";
             
            
            while(bandera == true)
            {
                Console.WriteLine("\n***** Bienvenido al banco *****");
                Console.WriteLine("\n¿Qué desea hacer?");
                Console.WriteLine("1. Crear cuenta");
                Console.WriteLine("2. Ingresar al sistema");
                Console.WriteLine("3. Salir");
                Console.WriteLine("\nSeleccione una opción: ");
                opcion = Convert.ToInt32(Console.ReadLine());

                switch(opcion)
                {
                    case 1:
                        Console.WriteLine("\n***** Sistema de creación de cuentas *****");
                        Console.WriteLine("\nIngrese el nombre del titular: ");
                        string titular = Console.ReadLine();
                        Console.WriteLine("\nIngrese el nip que quiera utilizar: ");
                        string nip = Console.ReadLine();
                        Console.WriteLine("\nIngrese el saldo inicial de la cuenta: ");
                        double saldo = Convert.ToDouble(Console.ReadLine());
                        cuenta = new CuentaBancaria(titular, nip, saldo);
                        Console.WriteLine("\nCuenta creada con éxito \nSu numero de cuenta es: " + cuenta.numeroCuenta + " (Guardelo para futuras operaciones)");
                        operaciones = "Creacion de cuenta";
                        break;
                    case 2:
                        if (cuenta == null)
                        {
                            Console.WriteLine("\nNo hay cuentas creadas");
                            break;
                        }
                        else
                        {
                            Console.WriteLine("\n***** Sistema de operaciones *****");
                            Console.WriteLine("\nIngrese el numero de cuenta: ");
                            string numCuenta = Console.ReadLine();
                            Console.WriteLine("\nIngrese el nip: ");
                            string nip2 = Console.ReadLine();
                            Console.WriteLine("\nBienvenido " + cuenta.titular);
                            while(bandera2 == true)
                            {
                                if (numCuenta == cuenta.numeroCuenta)
                                {
                                    if (nip2 == cuenta.nip)
                                    {
                                        Console.WriteLine("\n¿Qué desea hacer?");
                                        Console.WriteLine("1. Ver saldo");
                                        Console.WriteLine("2. Ingresar dinero");
                                        Console.WriteLine("3. Retirar dinero");
                                        Console.WriteLine("4. Consultar movimientos");
                                        Console.WriteLine("5. Salir");
                                        Console.WriteLine("\nSeleccione una opción: ");
                                        opcion = Convert.ToInt32(Console.ReadLine());
                                        switch(opcion)
                                        {
                                            case 1:
                                                Console.WriteLine("\nSu saldo es: " + cuenta.saldo);
                                                operaciones += "\nConsulta de saldo";
                                                break;
                                            case 2:
                                                Console.WriteLine("\nIngrese la cantidad a ingresar: ");
                                                double cantidad = Convert.ToDouble(Console.ReadLine());
                                                cuenta.Ingresar(cantidad);
                                                Console.WriteLine("\nDinero ingresado con éxito");
                                                operaciones += "\nIngeso de saldo";
                                                break;
                                            case 3:
                                                Console.WriteLine("\nIngrese la cantidad a retirar: ");
                                                double cantidad2 = Convert.ToDouble(Console.ReadLine());
                                                cuenta.Retirar(cantidad2);
                                                Console.WriteLine("\nDinero retirado con éxito");
                                                operaciones += "\nRetiro de saldo";
                                                break;
                                            case 4:
                                                Console.WriteLine("\nMovimiento realizados: ");
                                                Console.WriteLine(operaciones);
                                                break;
                                            case 5:
                                                Console.WriteLine("\nGracias por usar nuestros servicios");
                                                bandera2 = false;
                                                break;
                                            default:
                                                Console.WriteLine("\nPor favor seleccione una opcion válida, solo numero 1, 2, 3, 4 o 5");
                                                break;
                                        }
                                    }
                                    else
                                    {
                                        Console.WriteLine("\nNip incorrecto");
                                        break;
                                    }
                                }
                                else
                                {
                                    Console.WriteLine("\nNumero de cuenta incorrecto");
                                    break;
                                }
                            }
                        }
                        break;
                    case 3:
                        Console.WriteLine("\nGracias por usar el banco");
                        bandera = false;
                        break;
                    default:
                        Console.WriteLine("\nPor favor seleccione una opcion válida, solo numero 1, 2 o 3");
                        break;
                }
            }
        }
    }
}
