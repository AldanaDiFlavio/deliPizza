package ar.edu.unlam.tallerweb1.enclase;

import org.junit.Assert;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.enclase.Nota;

public class NotaTest extends SpringTest {

	@Test
	public void testAlCrearNotaDeberiaTenerValorMayorAUnoYMenorIgualADiez() throws Exception {
		/* Preparacion */
		Nota nota;

		/* Ejecucion */
		nota = new Nota(3.0);

		/* Validacion */
		Double valorNota = nota.getNota();
		Assert.assertTrue(1 <= valorNota && 10 >= valorNota);

	}

	@Test(expected = Exception.class) // Cuando creo una nota con null tiene que
										// dar excepcion
	public void testAlCrearNotaConValorNuloDeberiaDarError() throws Exception {
		/* Preparacion */
		Nota nota;

		/* Ejecucion */
		Double nota1 = null;
		nota = new Nota(nota1);

		// Assert.assertNull(valorNota);
		// TDD Desarrollo giado por pruebas
	}

	@Test(expected = Exception.class) // Cuando creo una nota con null tiene que
										// dar excepcion
	public void testAlCrearNotaConValorNegativoDeberiaDarError() throws Exception {

		/* Preparacion */
		Nota nota;

		/* Ejecucion */
		Double nota1 = -5.0;
		nota = new Nota(nota1);
	}

}