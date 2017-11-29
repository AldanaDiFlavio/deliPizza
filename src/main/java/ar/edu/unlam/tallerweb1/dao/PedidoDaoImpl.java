package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> traerTodosLosPedidos() {

		return (sessionFactory.getCurrentSession().createCriteria(Pedido.class).list());
	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(pedido);
	}

	@Override
	public Pedido traerElPedidoEnDeliveryAsignadoAUnaMoto(Moto moto) {
		return (Pedido) (sessionFactory.getCurrentSession().createCriteria(Pedido.class)
				.add(Restrictions.eq("estado", "EnDelivery")).add(Restrictions.eq("moto", moto)).uniqueResult());
	}

}
