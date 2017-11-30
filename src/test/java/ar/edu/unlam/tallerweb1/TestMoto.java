package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;

public class TestMoto extends SpringTest{
	
	@Inject
	private ServicioPedido servicioPedidoFake;

		@Test
		public void testQuePruebaQueSiSeGeneraElPedidoParaPersistirSeaEnEspera() {

			/* Preparacion */	
			
			servicioPedidoFake = mock(ServicioPedido.class);

			Pizza pizzamuzzarella = new Pizza();
			pizzamuzzarella.setNombre("Muzzarella");
			pizzamuzzarella.setImagen("muzzarella.jpg");
			pizzamuzzarella.setPrecio(150);
			pizzamuzzarella.setTcoccion(20);
			pizzamuzzarella.setTamanio("Normal");		
			
			List<Pizza> pizzasdelpedido = new LinkedList<Pizza>();
			pizzasdelpedido.add(pizzamuzzarella);
			pizzasdelpedido.add(pizzamuzzarella);
			
			Pedido pedidouno = new Pedido();
			pedidouno.setSolicitante("Jorge");
			pedidouno.setTelefono(1134362596);
			pedidouno.setDireccion("Alvear 580");
			pedidouno.setDemora(40);
			pedidouno.setPrecio(350);
			
			/* Ejecucion */
			
			Pedido pedidoValidar = servicioPedidoFake.generarPedidoParaPersistirConLasPizzasDelPedido(pedidouno,pizzasdelpedido);
			
			/* Validacion */			
			assertThat(pedidoValidar.getEstado()).isEqualTo("EnEspera");
		}
	
}
