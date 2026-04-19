package com.krakdev.artesanal.test;

import com.krakdev.artesanal.Maquina;

public class TestServir {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//boolean resultado;
		double venta;
		Maquina rubia = new Maquina("CR001","Pilsener", "Cerveza fría", 0.02, 8000);
		System.out.println("--------- ESTADO INICIAL ---------");
		rubia.imprimir();
		System.out.println("--------- LLENANDO MAQUINA ---------");
		rubia.llenarMaquina();
		
		rubia.imprimir();
		System.out.println("--------- SERVIR 1000ML ---------");
		venta = rubia.servirCerveza(1000);
		System.out.println("Costo cerveza servida al cliente: $"+venta);
		rubia.imprimir();
		System.out.println("--------- SERVIR 2000ML ---------");
		venta = rubia.servirCerveza(2000);
		System.out.println("Costo cerveza servida al cliente: $"+venta);
		rubia.imprimir();
		System.out.println("--------- SERVIR 6000ML ---------");
		venta = rubia.servirCerveza(6000);
		System.out.println("Costo cerveza servida al cliente: $"+venta);
		rubia.imprimir();
	}

}
