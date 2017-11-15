package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

public interface ServicioPizza {
	
	List<Pizza> traerTodasLasPizzas();
	
	void guardarPizza(Pizza pizza);

	Pizza traerUnaPizzaPorSuNombre(String string);

	Pizza traerUnaPizzaPorSuId(Long id);

	void guardarLaPizzaEnCarrito(Pizza pizza);
	
	List<Integer> traerListaDeNumeros();
	
}
