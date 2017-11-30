package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

public class PedidoDaoTest extends SpringTest {

	@Inject
	private PedidoDao pedidoDao;

	@Test
	@Transactional
	@Rollback(false)
	public void TestQueVerificaGuardarPedido() {

		/* Preparacion */

		Pedido pedido = new Pedido();
		pedido.setSolicitante("Jorge");
		pedido.setTelefono(1134362596);
		pedido.setDireccion("Alvear 580");
		pedido.setDemora(40);
		pedido.setPrecio(350);
		pedido.setEstado("EnEspera");

		/* Ejecucion */
		pedidoDao.guardarPedido(pedido);

		Session currentSession = sessionFactory.getCurrentSession();
		Pedido pedido1 = currentSession.get(Pedido.class, pedido.getId());

		/* Validacion */
		Assert.assertNotNull(pedido1);

	}

}
