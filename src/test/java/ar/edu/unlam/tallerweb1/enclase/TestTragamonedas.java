package ar.edu.unlam.tallerweb1.enclase;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.enclase.Tambor;
import ar.edu.unlam.tallerweb1.modelo.enclase.Tragamonedas;

import static org.mockito.Mockito.*;

public class TestTragamonedas {

	@Test
	public void testSiLosTresTamboresSonIgualesQueEntreguePremio() {

		/* Preparacion */
		Tambor tamb1 = mock(Tambor.class);
		Tambor tamb2 = mock(Tambor.class);
		Tambor tamb3 = mock(Tambor.class);
		/* Ejecucion */	
		when(tamb1.getValor()).thenReturn(5);
		when(tamb2.getValor()).thenReturn(5);
		when(tamb3.getValor()).thenReturn(5);
		
		Tragamonedas trag = new Tragamonedas(tamb1,tamb2,tamb3);
		trag.activar();
		boolean valor = trag.entregaPremio();
		/* Validacion */
		assertTrue(valor);		
		// Necesito generar un escenario para decir que esto funciona bien
		// No puedo hacerlo porque es random
		// Hacemos la prueba de un objeto que tiene dependencia con otro objeto
		// Objeto tragamonedas tiene una dependencia de otro objeto complejo
	}
	
	@Test
	public void testSiLosTresTamboresNoSonIgualesQueNoEntreguePremio() {
	
	}
}
