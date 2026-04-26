package com.krakdev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	
	private ArrayList<Maquina> maquinas;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private int ultimoCodigo=100;
	
	public NegocioMejorado() {
		this.maquinas=new ArrayList<Maquina>();
	}

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	public String generarCodigo() {
		int numero = (int) (Math.random()*100)+1;
		return "M-"+numero;
		
	}
	
	

}
