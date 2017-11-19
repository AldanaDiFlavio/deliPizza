package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public List<Integer> traerListaDeNumeros() {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100; i++) {
			numeros.add(i);
		}
		return numeros;
		
	}

	public List<Pizza> generarListaDePizzasDelCarrito(Map<Pizza, Integer> carrito) {
		List<Pizza> listClave = new LinkedList<Pizza>();
		Iterator it = carrito.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
			Pizza clave = e.getKey();
			Integer valor = e.getValue();
			listClave.add(clave);
		}
		return listClave;
	}

	@Override
	public List<Pizza> generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave) {
		Pizza pizza = new Pizza();
		List<Pizza> pedidoreal = new LinkedList<Pizza>(); // Persistir
		for (Pizza p : listClave) {
			for (int j = 0; j < p.getCantidad(); j++) {
				pizza = traerUnaPizzaPorSuNombre(p.getNombre());
				pedidoreal.add(pizza);
			}
		}
		return pedidoreal;
	}

	@Override
	public List<Pizza> generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave) {
		

		List<Pizza> pedidorealmostrar = new LinkedList<Pizza>();
		for (Pizza p : listClave) {
			pedidorealmostrar.add(p);
		}
		return pedidorealmostrar;
	}

	@Override
	public Integer calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(List<Pizza> listClave) {
		Integer preciototal = 0;
		for (Pizza p : listClave) {
			preciototal = preciototal + (p.getPrecio() * p.getCantidad());
		}
		return preciototal;
	}

}
