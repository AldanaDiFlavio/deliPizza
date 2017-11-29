package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;

public interface ServicioIngrediente {

	void guardarIngrediente(Ingrediente ingrediente);

	Ingrediente buscarIngrediente(String ingrediente);

	List<Ingrediente> todosLosIngredientes();

}
