package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maquina rubia = new Maquina("CR001","Pilsener","Cerveza fría",0.02,8000);
		rubia.imprimir();
		
		rubia.llenarMaquina();
		rubia.imprimir();
		
		Maquina negra = new Maquina("CR002","Club","Cerveza buena",0.03);
		negra.imprimir();
		negra.llenarMaquina();
		negra.imprimir();
	}

}
