package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorInsertar {

	@Inject
	private ServicioPizza servicioPizza;

	@Inject
	private ServicioPedido servicioPedido;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {

		List<Pizza> listaPizzas = servicioPizza.traerTodasLasPizzas();

		if (listaPizzas.isEmpty()) {
			return new ModelAndView("redirect:/insertar-pizzas");
		} else {
			return new ModelAndView("redirect:/home");
		}
	}

	@RequestMapping("/insertar-pizzas")
	public ModelAndView insertarPizzas() {

		Pizza calabresa = new Pizza();
		calabresa.setNombre("calabresa");
		calabresa.setImagen("calabresa.jpg");
		calabresa.setPrecio(100);
		calabresa.setTcoccion(20);
		servicioPizza.guardarPizza(calabresa);

		Pizza dechoclo = new Pizza();
		dechoclo.setNombre("dechoclo");
		dechoclo.setImagen("dechoclo.jpg");
		dechoclo.setPrecio(170);
		dechoclo.setTcoccion(25);
		servicioPizza.guardarPizza(dechoclo);

		Pizza jamonymorrones = new Pizza();
		jamonymorrones.setNombre("jamonymorrones");
		jamonymorrones.setImagen("jamonymorrones.jpg");
		jamonymorrones.setPrecio(150);
		jamonymorrones.setTcoccion(20);
		servicioPizza.guardarPizza(jamonymorrones);

		Pizza mozzarella = new Pizza();
		mozzarella.setNombre("mozzarella");
		mozzarella.setImagen("mozzarella.jpg");
		mozzarella.setPrecio(150);
		mozzarella.setTcoccion(20);
		servicioPizza.guardarPizza(mozzarella);

		Pizza napolitana = new Pizza();
		napolitana.setNombre("napolitana");
		napolitana.setImagen("napolitana.jpg");
		napolitana.setPrecio(150);
		napolitana.setTcoccion(20);
		servicioPizza.guardarPizza(napolitana);

		return new ModelAndView("redirect:/home");
	}

	@RequestMapping("/insertar-pedidos")
	public ModelAndView insertarPedidos() {

		Pizza napolitana = servicioPizza.traerUnaPizzaPorSuNombre("napolitana");
		Pizza calabresa = servicioPizza.traerUnaPizzaPorSuNombre("calabresa");
		Pizza dechoclo = servicioPizza.traerUnaPizzaPorSuNombre("dechoclo");
		Pizza mozzarella = servicioPizza.traerUnaPizzaPorSuNombre("mozzarella");

		List<Pizza> pizzasprimerpedido = new LinkedList<Pizza>();
		pizzasprimerpedido.add(napolitana);
		pizzasprimerpedido.add(napolitana);
		pizzasprimerpedido.add(calabresa);
		Pedido primerpedido = new Pedido();
		primerpedido.setSolicitante("Juan");
		primerpedido.setTelefono(1162343596);
		primerpedido.setDireccion("Florencio Varela 699");
		primerpedido.setListaPizzas(pizzasprimerpedido);
		servicioPedido.guardarPedido(primerpedido);

		List<Pizza> pizzassegundopedido = new LinkedList<Pizza>();
		pizzassegundopedido.add(dechoclo);
		pizzassegundopedido.add(mozzarella);
		Pedido segundopedido = new Pedido();
		segundopedido.setSolicitante("Mariano");
		segundopedido.setTelefono(1159464863);
		segundopedido.setDireccion("Falsa 123");
		segundopedido.setListaPizzas(pizzassegundopedido);
		servicioPedido.guardarPedido(segundopedido);

		List<Pizza> pizzastercerpedido = new LinkedList<Pizza>();
		pizzastercerpedido.add(mozzarella);
		pizzastercerpedido.add(mozzarella);
		Pedido tercerpedido = new Pedido();
		tercerpedido.setSolicitante("Ramon");
		tercerpedido.setTelefono(117343526);
		tercerpedido.setDireccion("Siempre viva");
		tercerpedido.setListaPizzas(pizzastercerpedido);
		servicioPedido.guardarPedido(tercerpedido);

		return new ModelAndView("redirect:/home");
	}

}
