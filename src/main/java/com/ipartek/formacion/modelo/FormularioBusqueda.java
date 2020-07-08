package com.ipartek.formacion.modelo;

public class FormularioBusqueda {

	private String nombre;
	private float pmin;
	private float pmax;
	private int idFabricante;
	
	public FormularioBusqueda() {
		super();
		this.nombre = "";
		this.pmin = 0;
		this.pmax = 0;
		this.idFabricante = 0;
	}

	public FormularioBusqueda(String nombre, String pmin, String pmax, String idFabricante) {
		this();
		this.nombre = nombre;
		//this.pmin = Float.parseFloat(pmin);
		this.setPmin(pmin);// llamo a las variables "seteadas" para asi evitar errores del tipo null o vacio.
		this.setPmax(pmax);
		this.setIdFabricante(idFabricante);
		//this.pmax = Float.parseFloat(pmax);
		//this.idFabricante = Integer.parseInt(idFabricante);
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if (nombre == null) {
			this.nombre = "";
		}else {
			this.nombre = nombre;
		}
	}

	public float getPmin() {
		return pmin;
	}

	public void setPmin(float pmin) {
		this.pmin = pmin;
	}
	
	public void setPmin(String pmin) {
		if (pmin == null) {
			this.pmin = 0;
		}else if ("".equals(pmin)) {
			this.pmin = 0;
		}else {
			this.pmin = Float.parseFloat(pmin);
		}	
	}

	public float getPmax() {
		return pmax;
	}

	public void setPmax(float pmax) {
		this.pmax = pmax;
	}
	
	public void setPmax(String pmax) {
		if (pmax == null) {
			this.pmax = 0;
		}else if ("".equals(pmax)) {
			this.pmax = 0;
		}else {
			this.pmax = Float.parseFloat(pmax);
		}
	}

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}
	
	public void setIdFabricante(String idFabricante) {
		if (idFabricante == null) {
			this.idFabricante = 0;
		}else if ("".equals(idFabricante)) {
			this.idFabricante = 0;
		}else {
			this.idFabricante = Integer.parseInt(idFabricante);
		}
	}

	@Override
	public String toString() {
		return "FormularioBusqueda [nombre=" + nombre + ", pmin=" + pmin + ", pmax=" + pmax + ", idFabricante="
				+ idFabricante + "]";
	}
	
}
