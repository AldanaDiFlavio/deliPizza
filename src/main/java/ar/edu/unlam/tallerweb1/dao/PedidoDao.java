package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

public interface PedidoDao {
	
	List<Pedido> traerTodosLosPedidos();

	void guardarPedido(Pedido pedido);

	void actualizarPedido(Pedido pedido);

}
