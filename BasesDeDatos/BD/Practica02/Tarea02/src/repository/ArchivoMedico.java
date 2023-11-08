package repository;

import exception.ArchivoLecturaNoCreadoException;
import model.Medico;
import repository.ManipulaArchivo;

/**
 * repository.ArchivoMedico.java Clase encargada de leer/escribir objetos de la clase
 * model.Medico
 * 
 * @author Eduardo Castro
 * @version Noviembre 2016
 * @see ManipulaArchivo
 */
public class ArchivoMedico extends ManipulaArchivo {
	public ArchivoMedico() {
		// coustrimos un file llamado medico, para poder editar
		super("medicos.csv");
	}

	/**
	 * Prepara un arreglo de model.Medico para que pueda ser guardado
	 * 
	 * @param medico
	 *            los medicos que se desean guardar
	 */
	public void escribeMedico(Medico medico[]) {
		String lineaMedico = "";
		for (Medico m : medico) {
			lineaMedico += m + "\n";
		}
		super.escribeArchivo(lineaMedico);
	}
 
	/**
	 * Convierte un arreglo de String a un arreglo de medicos
	 * @return arreglo de model.Medico con los datos del archivo
	 * @throws ArchivoLecturaNoCreadoException
	 */
	public Medico[] leeMedicos() throws ArchivoLecturaNoCreadoException {
		String lineas[] = super.leeArchivo();
		Medico medicos[] = new Medico[lineas.length];
		for (int i = 0; i < medicos.length; i++) {
			if (!lineas[i].equals("null")) {
				medicos[i] = this.parseaMedico(lineas[i]);
			}
		}
		return medicos;
	}

	/**
	 * Recibe una cadena y parsea los datos para crear el objeto model.Medico
	 * @param cadenaMedico la cadena a parsear 
	 * @return el objeto con los datos de la cadena
	 */
	private Medico parseaMedico(String cadenaMedico) {
		String linea[] = cadenaMedico.trim().split(",");
		String nombre = linea[0];
		int edad = Integer.parseInt(linea[1]);
		char sexo = linea[2].charAt(0);
		int idMedico = Integer.parseInt(linea[3]);
		String especialidad = linea[4];
		return new Medico(nombre, edad, sexo, idMedico,especialidad,null);
	}

}
