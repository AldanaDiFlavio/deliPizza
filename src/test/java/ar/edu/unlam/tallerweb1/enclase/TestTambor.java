package ar.edu.unlam.tallerweb1.enclase;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Tambor;
// Libreria de assersiones - que junit no tiene, ya esta incluida en el proyecto base
import static org.assertj.core.api.Assertions.*;


public class TestTambor extends SpringTest{
	
	@Test
	public void testAlGirarElTamborDeUnNumeroEntre1y7() {
		
		/*Preparacion*/ 
		Tambor tamb = new Tambor();
		/*Ejecucion*/ 
		tamb.girar();
		/*Validacion*/ 
	    assertThat(tamb.getValor()).isBetween(1, 8);		
	}
}
