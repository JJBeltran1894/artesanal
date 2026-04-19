package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakdev.artesanal.Cliente;
import com.krakdev.artesanal.Maquina;
import com.krakdev.artesanal.Negocio;

public class TestCosumoCliente {

	@Test
	public void probarConsumo() {
		
		Maquina maquinaA = new Maquina("CR001","Pilsen","Lager",0.002,8000);
		
		Negocio barDeMoe = new Negocio("Bar de Moe", maquinaA);
		
		Cliente cliente = new Cliente("Andres","134688742");
		
		barDeMoe.cargarMaquinaA();
		barDeMoe.consumirCerveza(cliente, 100);
		
		assertEquals(7700,maquinaA.getCantidadActual(), 0.0001);
		assertEquals(0.2,cliente.getTotalConsumido(), 0.0001);
		
		barDeMoe.consumirCerveza(cliente, 200);
		
		assertEquals(7500,maquinaA.getCantidadActual(), 0.0001);
		assertEquals(0.6,cliente.getTotalConsumido(),0.0001);
		
	}
}
