package model;

import model.Persona;

public class Paciente extends Persona {

	private int nss;
	private String diagnostico;
	private String fechaIngreso;
	private boolean status;
	private int idDoctor;

	public Paciente(String nombre, int edad, char sexo, int nss, String diagnostico, String fechaIngreso,
			boolean status, int idDoctor) {
		super(nombre, edad, sexo);
		this.nss = nss;
		this.diagnostico = diagnostico;
		this.fechaIngreso = fechaIngreso;
		this.status = status;
		this.idDoctor = idDoctor;
	}

	public int getNss() {
		return nss;
	}

	public void setNss(int nss) {
		this.nss = nss;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	@Override
	public String toString() {
		return super.toString() + "," + nss + "," + diagnostico + "," + fechaIngreso + "," + status + "," + idDoctor;
	}

}
