package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;

public interface ServicioPedido {
	
	List<Pedido> traerTodosLosPedidos();
	
	void guardarPedido(Pedido pedido);

	Pedido generarPedidoParaPersistirConLasPizzasDelPedido(Pedido pedido, List<Pizza> pizzasDelPedidoParaPersistir);

	List<Pedido> traerListaDePedidosEnEspera();

	void actualizarPedido(Pedido pedido);
	
}
