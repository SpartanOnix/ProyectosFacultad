package service;

import exception.ArchivoLecturaNoCreadoException;
import exception.ExcesoMedicoException;
import model.Medico;
import model.Paciente;
import repository.ArchivoMedico;

/**
 * service.Hospital.java
 * Clase encargada  de modelar el funcionamiento de registro de pacientes y medicos de un hospital
 */

public class Hospital {
	private Medico medicos[];
	private Paciente pacientes[];

	/**
	 * Creacinco5 paciente por cada medico
	 * @param cantidadMedicos
	 */
	public Hospital(int cantidadMedicos) {
		medicos = new Medico[cantidadMedicos];
		setPacientes(new Paciente[cantidadMedicos * 5]);
	}

	/**
	 * Leeemos la información que hay guardada en los archivos y la cargamos
	 */
	public Hospital() {
		medicos = new Medico[5];
		setPacientes(new Paciente[5 * 5]);
		ArchivoMedico archivoMedico = new ArchivoMedico();
		try{
			System.out.println("Leyendo datos....");
			medicos = archivoMedico.leeMedicos();
			System.out.println("Datos leidos....");
		}catch(ArchivoLecturaNoCreadoException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * agrega un medico al arreglo, verifica que hay espacio disponible para que este sea agregado
	 * @param medico
	 * @throws ExcesoMedicoException
	 */
	public void agregaMedico(Medico medico) throws ExcesoMedicoException {
		if (!lugarMedicoDisponible()) {
			throw new ExcesoMedicoException("Ya no hay espacios disponible para agregar un médico");
		} else {
			medicos[obtenerCantidadMedico()] = medico;
		}

	}
	
	/**
	 * Escribe los medicos en un archivo 
	 */
	public void guardaMedicos(){
		ArchivoMedico archivoM = new ArchivoMedico();
		archivoM.escribeMedico(medicos);
	}
	

	/**
	 * ]Calcula los lugares ocupados por medicos
	 * @return
	 */
	private int obtenerCantidadMedico() {
		int cantidad = 0;
		for (Medico m : medicos) {
			if (m != null) {
				cantidad++;
			}
		}
		return cantidad;
	}

	/**
	 * Verifica que exista lugar para un medico mas
	 * @return
	 */
	private boolean lugarMedicoDisponible() {
		return obtenerCantidadMedico() < medicos.length;
	}

	/**
	 * Regresa información de los medicos guardados
	 * @return String con la información de los medicos
	 */
	public String toStringMedicos() {
		String todosMedicos = "";
		for(int i = 0; i < obtenerCantidadMedico(); i++){
			todosMedicos += medicos[i] + "\n"; 
		}
		return todosMedicos;
	}	
	
	/**
	 * Muestra los medicos que existen en el hospital
	 * @return
	 * @throws ExcesoMedicoException 
	 */
	public String listadoMedicos() throws ExcesoMedicoException{
		String medicosString = "";
		int i = 0;
		for(Medico medico: this.medicos){
			if(medico != null){
				medicosString += i + " - Nombre: " + medico.getNombre() + " Especialidad:" + medico.getEspecialidad() + "\n";
				i++;
			}
		}
		if(medicosString.equals("")){
			throw new ExcesoMedicoException("No existen médicos registrados");
		}
		return medicosString;
	}

	public Medico[] getMedicos() {
		return medicos;
	}

	public Medico getMedico(int i) throws ExcesoMedicoException{
		if(i < 0 || i >= this.obtenerCantidadMedico()){
			throw new ExcesoMedicoException("model.Medico seleccionado incorrectamente");
		}
		return this.medicos[i];
	}
	
	/**
	 * Elimina a un medico del arreglo de medicos
	 * @param i indice del medico a eliminar 
	 * @throws ExcesoMedicoException
	 */
	public void eliminaMedico(int i) throws ExcesoMedicoException{
		if(i < 0 || i >= this.obtenerCantidadMedico()){
			throw new ExcesoMedicoException("model.Medico seleccionado incorrectamente");
		}
		this.medicos[i] = null;
		this.recorreMedicos(i);
	}
	/**
	 * Se encarga de recorrer los medicos en el arreglo para que no haya nulos en medio
	 */
	private void recorreMedicos(int i){
		
		int indiceUltimoM = this.obtenerCantidadMedico();
		if(i != indiceUltimoM){
			this.medicos[i] = medicos[indiceUltimoM];
			medicos[indiceUltimoM] = null;
		}
	}
	
	public void setMedicos(Medico[] medicos) {
		this.medicos = medicos;
	}

	public Paciente[] getPacientes() {
		return pacientes;
	}

	public void setPacientes(Paciente pacientes[]) {
		this.pacientes = pacientes;
	}
}
