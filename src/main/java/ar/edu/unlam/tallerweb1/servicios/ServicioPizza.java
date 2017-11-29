package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

public interface ServicioPizza {

	List<Pizza> traerTodasLasPizzas();

	void guardarPizza(Pizza pizza);

	Pizza traerUnaPizzaPorSuId(Long id);

	List<Pizza> generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito);

	List<Pizza> generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito);

	Integer calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito);

	Integer calcularElprecioTotalPorTodasLasPizzasPedidas(List<Pizza> carrito);

	List<Pizza> aniadeAlCarritoOModifica(List<Pizza> carrito, Pizza pizza);

	Pizza calcularCantidadDePizzas(Pizza lapizza);

	Pizza calcularTotalDeLaPizza(Pizza pizza, Pizza laPizzaConLaCantidad);

	List<Pizza> quitaDelCarrito(List<Pizza> carrito, Pizza pizza);

}
