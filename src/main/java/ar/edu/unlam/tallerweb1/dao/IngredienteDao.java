package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;

public interface IngredienteDao {

	void guardarIngrediente(Ingrediente ingrediente);

	Ingrediente buscarIngrediente(String ingrediente);

	List<Ingrediente> todosLosIngredientes();

}
