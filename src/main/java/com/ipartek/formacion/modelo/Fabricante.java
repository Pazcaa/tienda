package com.ipartek.formacion.modelo;

public class Fabricante {

	private int id;
	private String nombre;
	
	public Fabricante() {
		super();
		this.id = 0;
		this.nombre = "";
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
}
