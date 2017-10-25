package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PedidoDao;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

@Service("servicioPedido")
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

	@Inject
	private PedidoDao servicioPedidoDao;

	@Override
	public void guardarPedido(Pedido pedido) {
		servicioPedidoDao.guardarPedido(pedido);
		
	}

}
