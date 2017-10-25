package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("pedidoDao")
public class PedidoDaoImpl implements PedidoDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarPedido(Pedido pedido) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(pedido);		
	}


}
