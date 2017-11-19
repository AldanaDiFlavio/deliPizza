package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

public interface ServicioPizza {
	
	List<Pizza> traerTodasLasPizzas();
	
	void guardarPizza(Pizza pizza);

	Pizza traerUnaPizzaPorSuNombre(String string);

	Pizza traerUnaPizzaPorSuId(Long id);
	
	List<Integer> traerListaDeNumeros();

	List<Pizza> generarListaDePizzasDelCarrito(Map<Pizza, Integer> carrito);

	List<Pizza> generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave);

	List<Pizza> generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave);

	Integer calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave);
	
}
