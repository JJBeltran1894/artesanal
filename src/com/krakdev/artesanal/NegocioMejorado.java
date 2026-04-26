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
	
	public boolean agregarMaquina(String nombre, String descripcion, double precioPorMl) {
		String codigo = generarCodigo();
		Maquina maquinaRecuperada=recuperarMaquina(codigo);
		if(maquinaRecuperada==null) {
			Maquina maquina = new Maquina(codigo, nombre, descripcion, precioPorMl);
			maquinas.add(maquina);
			return true;
		}else {
			return false;
		}
	}
	
	public void cargarMaquinas() {
		for(int i=0;i<maquinas.size();i++) {
			maquinas.get(i).llenarMaquina();
		}
	}
	
	public Maquina recuperarMaquina(String codigo) {
		for(int i=0;i<maquinas.size();i++) {
			if(maquinas.get(i).getCodigo().equals(codigo)) {
				return maquinas.get(i);
			}
		}
		return null;
	}

	
	

}
