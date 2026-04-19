package com.krakedev.artesanal.testJUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakdev.artesanal.Maquina;

public class TestServirCervezaAI {

    // Se define una tolerancia (delta) para las comparaciones de números double
    private static final double DELTA = 0.001;

    @Test
    public void testServirConCantidadSuficiente() {
        // Validación: Verifica el caso ideal donde se solicita una cantidad menor a la disponible.
        // Se usa el constructor de 5 parámetros para establecer una cantidad actual inicial de 500.0 ml.
        Maquina maquina = new Maquina("CR000","IPA", "Fuerte y amarga", 0.05, 1000.0, 500.0);
        
        // Acción: Se sirven 200 ml
        double valorAPagar = maquina.servirCerveza(200.0);
        
        // Verificaciones
        // El valor a pagar debe ser 200.0s ml * 0.05 = 10.0
        assertEquals(10.0, valorAPagar, DELTA, "El valor a pagar debe ser el producto de la cantidad servida por el precio por ml.");
        // La cantidad actual debe reducirse: 500.0 - 200.0 = 300.0
        assertEquals(300.0, maquina.getCantidadActual(), DELTA, "La cantidad actual debe reducirse al servir cerveza exitosamente.");
    }

    @Test
    public void testServirCantidadExactaDisponible() {
        // Validación: Verifica un caso límite donde el cliente pide exactamente toda la cerveza disponible.
        // Se usa nuevamente el constructor de 5 parámetros.
        Maquina maquina = new Maquina("CR001","Lager", "Refrescante", 0.03, 2000.0, 400.0);
        
        // Acción: Se sirven 400 ml (exactamente lo que hay)
        double valorAPagar = maquina.servirCerveza(400.0);
        
        // Verificaciones
        // El valor a pagar debe ser 400.0 ml * 0.03 = 12.0
        assertEquals(12.0, valorAPagar, DELTA, "Debe calcular el precio total cuando se vacía la cantidad actual.");
        // La cantidad actual debe quedar en 0.0
        assertEquals(0.0, maquina.getCantidadActual(), DELTA, "La cantidad actual debe ser 0 tras servir toda la cerveza disponible.");
    }

    @Test
    public void testServirConCantidadVacia() {
        // Validación: Verifica que no se sirva nada si la máquina no tiene cerveza (cantidadActual = 0).
        // Se usa el constructor de 4 parámetros, el cual por defecto inicializa cantidadActual en 0.
        Maquina maquina = new Maquina("CR002","Stout", "Negra y espesa", 0.06, 3000.0);
        
        // Acción: Se intentan servir 100 ml de una máquina vacía
        double valorAPagar = maquina.servirCerveza(100.0);
        
        // Verificaciones
        // No debe cobrar nada
        assertEquals(0.0, valorAPagar, DELTA, "El valor a pagar debe ser 0 si la máquina está vacía.");
        // La cantidad actual no debe verse modificada y debe seguir en 0
        assertEquals(0.0, maquina.getCantidadActual(), DELTA, "La cantidad actual no debe alterarse si no se logra servir cerveza.");
    }

    @Test
    public void testServirCantidadInsuficienteConAlgoDeCerveza() {
        // Validación: Verifica que la máquina rechace la acción si hay cerveza, pero no la suficiente para cubrir el pedido.
        // Se usa el constructor de 3 parámetros (capacidad máxima por defecto 10000, cantidadActual en 0).
        Maquina maquina = new Maquina("CR003","Pilsen", "Clásica rubia", 0.02);
        
        // Preparación: Se recargan 150 ml usando el método disponible en la clase
        maquina.recargarCerveza(150.0);
        
        // Acción: Se intentan servir 200 ml cuando solo hay 150 ml
        double valorAPagar = maquina.servirCerveza(200.0);
        
        // Verificaciones
        // Al no poder completar el pedido, no debe cobrar nada
        assertEquals(0.0, valorAPagar, DELTA, "El valor a pagar debe ser 0 si se solicita más de lo disponible.");
        // La cantidad actual de 150.0 ml no debió tocarse
        assertEquals(150.0, maquina.getCantidadActual(), DELTA, "La cantidad actual debe mantenerse intacta si el pedido es mayor a la disponibilidad.");
    }
    
    @Test
    public void testServirCero() {
        // Validación: Verifica el caso límite donde se intenta servir 0 ml.
        Maquina maquina = new Maquina("CR004","Stout", "Negra y espesa", 0.05, 1000.0, 500.0);
        
        double valorAPagar = maquina.servirCerveza(0.0);
        
        // No debe cobrar nada
        assertEquals(0.0, valorAPagar, DELTA, "El valor a pagar debe ser 0 al servir 0 ml.");
        // La cantidad actual debe mantenerse igual
        assertEquals(500.0, maquina.getCantidadActual(), DELTA, "La cantidad actual no debe alterarse al servir 0 ml.");
    }
}

