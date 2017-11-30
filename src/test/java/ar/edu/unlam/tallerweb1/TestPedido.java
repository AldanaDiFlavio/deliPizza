package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PedidoDao;
import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoto;

public class TestPedido extends SpringTest {

	@Inject
	PedidoDao pedidoDao;

	@Inject
	ServicioMoto servicioMoto;

	@Test
	@Transactional
	@Rollback(true)
	public void TestQueVerificaQueUnPedidoEstaEnEsperaYNoHayaMotosLibres() {

		Session session = getSession();

		/* Preparacion */
		Moto motouno = new Moto();
		motouno.setId((long) 1);
		motouno.setPatente("A123BCD");
		motouno.setConductor("Jose Luis");
		motouno.setEstado("Ocupada");
		session.save(motouno);

		Moto motodos = new Moto();
		motodos.setId((long) 2);
		motodos.setPatente("B124ACD");
		motodos.setConductor("Mario Lopez");
		motodos.setEstado("Ocupada");
		session.save(motodos);

		Moto mototres = new Moto();
		mototres.setId((long) 3);
		mototres.setPatente("A353CBD");
		mototres.setConductor("Carlos Gimenez");
		mototres.setEstado("Ocupada");
		session.save(mototres);

		// Pedido 

		Pedido pedido = new Pedido();

		pedido.setSolicitante("Jorge");
		pedido.setTelefono(1134362596);
		pedido.setDireccion("Alvear 580");
		pedido.setDemora(40);
		pedido.setPrecio(350);
		pedido.setEstado("EnEspera");
		session.save(pedido);

		/* Ejecucion */

		Moto moto = servicioMoto.consultarSiHayMotosLibres();

		if (moto.getEstado().equals("Libre")) {

			servicioMoto.asignarMotoAPedido(moto, pedido);

		}

		/* Validacion */

		assertThat(pedido.getEstado()).isEqualTo("EnEspera");

	}
}
