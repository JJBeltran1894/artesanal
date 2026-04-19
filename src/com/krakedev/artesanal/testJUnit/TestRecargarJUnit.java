package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakdev.artesanal.Maquina;

public class TestRecargarJUnit {
	@Test
	public void testRecargaExitosa() {
		boolean resultado;
		Maquina rubia = new Maquina("CR001","Pilsener", "Cerveza", 0.02, 8000);
		resultado=rubia.recargarCerveza(3000);
		assertTrue(resultado);
		assertEquals(3000, rubia.getCantidadActual(),0.0001);
	}
	@Test
	public void testRecargaFallida() {
		boolean resultado;
		Maquina negra = new Maquina("CR002","Club", "Cerveza fria", 0.02, 8000);
		resultado=negra.recargarCerveza(7000);
		resultado=negra.recargarCerveza(1000);
		assertTrue(resultado);
		assertEquals(0, negra.getCantidadActual(),0.0001);
	}
}
