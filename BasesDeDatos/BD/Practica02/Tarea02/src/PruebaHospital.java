import exception.ExcesoMedicoException;
import model.Medico;
import service.Hospital;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PruebaHospital {
	public static void main(String[] args) {
		boolean bandera;
		int opcion = 0;
		Scanner input = new Scanner(System.in);
		Hospital hospital = new Hospital();
		do {
			bandera = true;
			System.out.println("Ingresa la opción deseada\n" + "1-Agregar model.Medico\n" + "2-Ver medicos\n"
					+ "3-Editar medico\n" + "4-Borrar medico\n" + "5-Salir\n");
			try {
				opcion = input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Ingresa un número como opción");
			}
			if (opcion > 0 && opcion < 6) {
				switch (opcion) {
				case 1:
					try {
						Medico medico = new Medico();
						hospital.agregaMedico(medico);
						System.out.println("model.Medico guardado correctamente.");
					} catch (ExcesoMedicoException e) {
						System.out.println("Ya no hay lugar disponible para un nuevo médico.");
					}
					break;
				case 2:
					System.out.println(hospital.toStringMedicos());
					break;
				case 3:
					System.out.println("Elije el número del médico que deseas editar");
					bandera = true;
					do {
						try {
							System.out.println(hospital.listadoMedicos());
							opcion = input.nextInt();
							hospital.getMedico(opcion).edita();
							bandera = false;
							System.out.println("model.Medico editado correctamente");
						} catch (ExcesoMedicoException e) {
							System.out.println(e.getMessage());
							bandera = false;
						} catch (InputMismatchException i) {
							System.out.println("Ingresa un número como opción");
							input.next();
							bandera = true;
						}
					} while (bandera);
					bandera = true;

					break;
				case 4:

					System.out.println("Elije el número del médico que deseas borrar");
					bandera = true;
					do {
						try {
							System.out.println(hospital.listadoMedicos());
							opcion = input.nextInt();
							hospital.eliminaMedico(opcion);
							bandera = false;
						} catch (ExcesoMedicoException e) {
							System.out.println(e.getMessage());
							bandera = false;
						} catch (InputMismatchException i) {
							System.out.println("Ingresa un número como opción");
							input.next();
							bandera = true;
						}
					} while (bandera);
					bandera = true;
					break;
				case 5:
					System.out.println("Guardando datos de medicos....");
					hospital.guardaMedicos();
					bandera = false;
					System.out.println("Datos guardados");
					System.out.println("Saliendo del sistema...\nHasta pronto.");
					break;
				default:
					break;
				}
			}
		} while (bandera);

	}
}
