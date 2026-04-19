package com.krakdev.artesanal.test;

import com.krakdev.artesanal.Maquina;

public class TestAtributos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maquina rubia = new Maquina("CR001","Pilsener","Cerveza Rubia",0.02,10000,10000);
		rubia.imprimir();
		
		rubia.setNombreCerveza("Golden Ale");
		rubia.setDescripcion("Cerveza con aroma mas intenso");
		
		rubia.imprimir();
		
	}

}
