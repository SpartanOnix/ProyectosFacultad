package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Medico extends Persona {
	private int idDoctor;
	private String especialidad;
	private Paciente pacientes [];
	
	public Medico(String nombre, int edad, char sexo, int idDoctor, String especialidad, Paciente[] pacientes) {
		super(nombre, edad, sexo);
		this.idDoctor = idDoctor;
		this.especialidad = especialidad;
		this.pacientes = pacientes;
	}
	
	public Medico(){
		super();
		input = new Scanner(System.in);
		System.out.println("Ingresa especialidad.");
		this.especialidad = input.nextLine();
		this.pacientes = null;
		input = new Scanner(System.in);
		boolean bandera = true;
		do {
			try {
				System.out.println("Ingresa idDoctor.");
				this.idDoctor = input.nextInt();
				bandera = false;
			} catch (InputMismatchException e) {
				input.next();
				bandera = true;
			}
		} while (bandera);
	}
	
	public void edita(){
		super.edita();;
		input = new Scanner(System.in);
		System.out.println("Ingresa especialidad.");
		this.especialidad = input.nextLine();
		this.pacientes = null;
		input = new Scanner(System.in);
		boolean bandera = true;
		do {
			try {
				System.out.println("Ingresa idDoctor.");
				this.idDoctor = input.nextInt();
				bandera = false;
			} catch (InputMismatchException e) {
				input.next();
				bandera = true;
			}
		} while (bandera);
		
	}
	
	public int getIdDoctor() {
		return idDoctor;
	}
	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public Paciente[] getPacientes() {
		return pacientes;
	}
	public void setPacientes(Paciente[] pacientes) {
		this.pacientes = pacientes;
	} 
	
	@Override
	public String toString() {
		return super.toString() + "," + this.idDoctor + "," + this.especialidad;
	}
	
	public static void main(String[] args) {
		Medico m = new Medico();
		System.out.println(m);
	}
}
