package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
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
	public Pizza traerUnaPizzaPorSuId(Long id) {
		return servicioPizzaDao.traerUnaPizzaPorSuId(id);
	}

	@Override
	public List<Pizza> generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito) {
		Pizza pizza = new Pizza();
		List<Pizza> pedidoreal = new LinkedList<Pizza>(); // Persistir
		for (Pizza p : carrito) {
			for (int j = 0; j < p.getCantidad(); j++) {
				pizza = traerUnaPizzaPorSuId(p.getId());
				pedidoreal.add(pizza);
			}
		}
		return pedidoreal;
	}

	@Override
	public List<Pizza> generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito) {

		List<Pizza> pedidoAMostrar = new LinkedList<Pizza>();
		for (Pizza p : carrito) {
			pedidoAMostrar.add(p);
		}
		return pedidoAMostrar;
	}

	@Override
	public Integer calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(List<Pizza> carrito) {
		Integer preciototal = 0;
		for (Pizza p : carrito) {
			preciototal = preciototal + (p.getPrecio() * p.getCantidad());
		}
		return preciototal;
	}

	@Override
	public Integer calcularElprecioTotalPorTodasLasPizzasPedidas(List<Pizza> carrito) {
		Integer preciototalportodaslaspizzas = 0;
		for (Pizza p : carrito) {
			preciototalportodaslaspizzas = preciototalportodaslaspizzas + (p.getPrecio() * p.getCantidad());
		}
		return preciototalportodaslaspizzas;

	}

	@Override
	public List<Pizza> aniadeAlCarritoOModifica(List<Pizza> carrito, Pizza pizza) {
		Pizza remueve = null;
		for (Pizza p : carrito) {

			if (p.getId().equals(pizza.getId())) {
				remueve = p;
			}
		}
		if (remueve != null) {
			carrito.remove(remueve);
		}
		carrito.add(pizza);
		return carrito;
	}

	@Override
	public Pizza calcularCantidadDePizzas(Pizza lapizza) {
		if (lapizza.getCantidad() == null) {
			lapizza.setCantidad(1);
		} else {
			lapizza.setCantidad(lapizza.getCantidad());
		}
		return lapizza;
	}

	@Override
	public Pizza calcularTotalDeLaPizza(Pizza pizza, Pizza laPizzaConLaCantidad) {
		pizza.setCantidad(laPizzaConLaCantidad.getCantidad());
		Integer preciototalporpizza = pizza.getPrecio();
		preciototalporpizza = pizza.getPrecio() * laPizzaConLaCantidad.getCantidad();
		pizza.setPreciototal(preciototalporpizza);
		return pizza;
	}

	@Override
	public List<Pizza> quitaDelCarrito(List<Pizza> carrito, Pizza pizza) {
		Pizza remueve = null;
		for (Pizza p : carrito) {

			if (p.getId().equals(pizza.getId())) {
				remueve = p;
			}
		}
		if (remueve != null) {
			carrito.remove(remueve);
		}
		return carrito;
	}
}
