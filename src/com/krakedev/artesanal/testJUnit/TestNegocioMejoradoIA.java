package com.krakedev.artesanal.testJUnit;

import org.junit.jupiter.api.Test;

import com.krakdev.artesanal.Cliente;
import com.krakdev.artesanal.Maquina;
import com.krakdev.artesanal.NegocioMejorado;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestNegocioMejoradoIA {

    private static final double DELTA = 0.001;

    @Test
    public void testGettersYSetters() {
        // Validación: Verifica que el constructor, getter y setter de la lista de máquinas funcionen.
        NegocioMejorado negocio = new NegocioMejorado();
        ArrayList<Maquina> nuevaLista = new ArrayList<>();
        
        negocio.setMaquinas(nuevaLista);
        assertEquals(nuevaLista, negocio.getMaquinas(), "El setter debe actualizar la lista de máquinas.");
    }

    @Test
    public void testAgregarMaquinaExito() {
        // Validación: Verifica que se agregue una máquina nueva correctamente.
        NegocioMejorado negocio = new NegocioMejorado();
        
        boolean resultado = negocio.agregarMaquina("IPA", "Cerveza Amarga", 0.05);
        
        assertTrue(resultado, "Debe retornar true al agregar una máquina nueva.");
        assertEquals(1, negocio.getMaquinas().size(), "La lista de máquinas debe tener 1 elemento.");
    }

    @Test
    public void testAgregarMaquinaColisionAleatoria() {
        // Validación: Forzamos el límite del Math.random() para asegurar que entre al 'else' y retorne false.
        // Dado que generarCodigo() da valores de M-1 a M-100, si agregamos 150, obligatoriamente habrá un repetido.
        NegocioMejorado negocio = new NegocioMejorado();
        boolean colisionDetectada = false;
        
        for (int i = 0; i < 150; i++) {
            boolean agregada = negocio.agregarMaquina("Stout " + i, "Prueba", 0.04);
            if (!agregada) {
                colisionDetectada = true; // Entró al 'else' porque el código ya existía
                break;
            }
        }
        
        assertTrue(colisionDetectada, "El método debe retornar false si el código generado al azar ya existe.");
    }

    @Test
    public void testCargarMaquinas() {
        // Validación: Verifica que el ciclo for recorra las máquinas y aplique la recarga de 10000 - 200 = 9800
        NegocioMejorado negocio = new NegocioMejorado();
        negocio.agregarMaquina("Lager", "Refrescante", 0.03);
        
        // Ejecutamos la carga general
        negocio.cargarMaquinas();
        
        // Recuperamos la máquina de la lista (como no sabemos su código aleatorio, la sacamos por el índice 0)
        Maquina maquinaRecuperada = negocio.getMaquinas().get(0);
        
        assertEquals(9800.0, maquinaRecuperada.getCantidadActual(), DELTA, "La máquina debe cargarse descontando 200ml de su capacidad máxima (10000).");
    }

    @Test
    public void testRecuperarMaquinaNoExistente() {
        // Validación: Verifica que recuperarMaquina retorne null si no la encuentra.
        NegocioMejorado negocio = new NegocioMejorado();
        Maquina maquina = negocio.recuperarMaquina("M-999");
        
        assertNull(maquina, "Debe retornar null si la máquina no existe.");
    }

    @Test
    public void testRegistrarYBuscarCliente() {
        // Validación: Verifica el registro de clientes y los métodos de búsqueda (caminos de éxito y de fallo).
        NegocioMejorado negocio = new NegocioMejorado();
        
        // Acción
        negocio.registrarCliente("Juan Perez", "1717171717");
        
        // Búsquedas exitosas
        Cliente clientePorCedula = negocio.buscarClientePorCedula("1717171717");
        Cliente clientePorCodigo = negocio.buscarClientePorCodigo(100); // El primer cliente asume código 100
        
        assertNotNull(clientePorCedula, "Debe encontrar al cliente por su cédula.");
        assertEquals("Juan Perez", clientePorCedula.getNombre(), "El nombre del cliente debe coincidir.");
        assertNotNull(clientePorCodigo, "Debe encontrar al cliente por su código inicial (100).");
        
        // Búsquedas fallidas
        assertNull(negocio.buscarClientePorCedula("000"), "Debe retornar null si la cédula no existe.");
        assertNull(negocio.buscarClientePorCodigo(999), "Debe retornar null si el código no existe.");
    }

    @Test
    public void testFlujoCompletoConsumoYVentas() {
        // Validación: Verifica la integración de consumir cerveza, registrar consumo y consultar las ventas totales.
        NegocioMejorado negocio = new NegocioMejorado();
        
        // 1. Preparamos una máquina con cerveza
        negocio.agregarMaquina("Pilsen", "Dorada", 0.05); // Precio: 0.05 por ml
        negocio.cargarMaquinas();
        String codigoMaquinaGenerado = negocio.getMaquinas().get(0).getCodigo();
        
        // 2. Preparamos un cliente
        negocio.registrarCliente("Maria", "1818181818"); // Código asigando: 100
        
        // 3. El cliente consume 200 ml de la máquina. Valor esperado: 200 * 0.05 = 10.0
        negocio.consumirCerveza(100, codigoMaquinaGenerado, 200.0);
        
        // 4. Verificaciones
        Cliente cliente = negocio.buscarClientePorCodigo(100);
        assertEquals(10.0, cliente.getTotalConsumido(), DELTA, "El total consumido del cliente debe actualizarse.");
        
        double totalVendido = negocio.consultarValorVendido();
        assertEquals(10.0, totalVendido, DELTA, "El valor total vendido por el negocio debe sumar los consumos.");
    }
    
}