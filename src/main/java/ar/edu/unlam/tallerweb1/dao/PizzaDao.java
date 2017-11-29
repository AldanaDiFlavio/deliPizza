package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

public interface PizzaDao {

	List<Pizza> traerTodasLasPizzas();

	void guardarPizza(Pizza pizza);

	Pizza traerUnaPizzaPorSuNombre(String string);

	Pizza traerUnaPizzaPorSuId(Long id);

}
