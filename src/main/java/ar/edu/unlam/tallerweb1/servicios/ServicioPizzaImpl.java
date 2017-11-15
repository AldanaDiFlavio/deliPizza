package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PizzaDao;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

@Service("servicioPizza")
@Transactional
public class ServicioPizzaImpl implements ServicioPizza {

	@Inject
	private PizzaDao servicioPizzaDao;

	@Override
	public List<Pizza> traerTodasLasPizzas() {
		return servicioPizzaDao.traerTodasLasPizzas();
	}

	@Override
	public void guardarPizza(Pizza pizza) {
		 servicioPizzaDao.guardarPizza(pizza);
	}

	@Override
	public Pizza traerUnaPizzaPorSuNombre(String string) {
		return servicioPizzaDao.traerUnaPizzaPorSuNombre(string);
	}

	@Override
	public Pizza traerUnaPizzaPorSuId(Long id) {
		return servicioPizzaDao.traerUnaPizzaPorSuId(id);
	}

	@Override
	public void guardarLaPizzaEnCarrito(Pizza pizza) {
		servicioPizzaDao.guardarLaPizzaEnCarrito(pizza);
	}

	@Override
	public List<Integer> traerListaDeNumeros() {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100; i++) {
			numeros.add(i);
		}
		return numeros;
		
	}

}
