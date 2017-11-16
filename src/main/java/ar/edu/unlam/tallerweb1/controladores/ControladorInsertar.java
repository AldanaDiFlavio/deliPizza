package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorInsertar {

	@Inject
	private ServicioPizza servicioPizza;

	@Inject
	private ServicioPedido servicioPedido;

	@Inject
	private ServicioMoto servicioMoto;
	
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
		calabresa.setNombre("Calabresa");
		calabresa.setImagen("calabresa.jpg");
		calabresa.setPrecio(100);
		calabresa.setTcoccion(20);
		servicioPizza.guardarPizza(calabresa);

		Pizza dechoclo = new Pizza();
		dechoclo.setNombre("Choclo");
		dechoclo.setImagen("dechoclo.jpg");
		dechoclo.setPrecio(170);
		dechoclo.setTcoccion(25);
		servicioPizza.guardarPizza(dechoclo);

		Pizza mozzarella = new Pizza();
		mozzarella.setNombre("Mozzarella");
		mozzarella.setImagen("mozzarella.jpg");
		mozzarella.setPrecio(150);
		mozzarella.setTcoccion(20);
		servicioPizza.guardarPizza(mozzarella);

		Pizza napolitana = new Pizza();
		napolitana.setNombre("Napolitana");
		napolitana.setImagen("napolitana.jpg");
		napolitana.setPrecio(150);
		napolitana.setTcoccion(20);
		servicioPizza.guardarPizza(napolitana);
		
		Pizza jamonymorrones = new Pizza();
		jamonymorrones.setNombre("Jamon y Morron");
		jamonymorrones.setImagen("jamonymorrones.jpg");
		jamonymorrones.setPrecio(150);
		jamonymorrones.setTcoccion(20);
		servicioPizza.guardarPizza(jamonymorrones);

		return new ModelAndView("redirect:/insertar-motos");
	}

	@RequestMapping("/insertar-pedidos")
	public ModelAndView insertarPedidos() {

		Pizza napolitana = servicioPizza.traerUnaPizzaPorSuNombre("Napolitana");
		Pizza calabresa = servicioPizza.traerUnaPizzaPorSuNombre("Calabresa");
		Pizza dechoclo = servicioPizza.traerUnaPizzaPorSuNombre("Choclo");
		Pizza mozzarella = servicioPizza.traerUnaPizzaPorSuNombre("Mozzarella");

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

	@RequestMapping("/insertar-motos")
	public ModelAndView insertarMotos() {
		
		Moto motouno = new Moto();		// No tiene pedidos
		motouno.setPatente("A123BCD");
		servicioMoto.guardarMoto(motouno);

		Moto motodos = new Moto();		// Tiene 1 Pedido
		motodos.setPatente("B124ACD");
		servicioMoto.guardarMoto(motodos);

		Moto mototres = new Moto();		// Tiene 3 Pedidos
		mototres.setPatente("A353CBD");
		servicioMoto.guardarMoto(mototres);

		Moto motocuatro = new Moto();	// Tiene 2 pedidos
		motocuatro.setPatente("D250ACB");
		servicioMoto.guardarMoto(motocuatro);
		
		Pizza napolitana = servicioPizza.traerUnaPizzaPorSuNombre("Pizza Napolitana");
		Pizza calabresa = servicioPizza.traerUnaPizzaPorSuNombre("Pizza Calabresa");
		Pizza dechoclo = servicioPizza.traerUnaPizzaPorSuNombre("Pizza Choclo");
		Pizza mozzarella = servicioPizza.traerUnaPizzaPorSuNombre("Pizza Mozzarella");
		
		// Pedidos moto 2
		
		List<Pizza> pizzaspedidomotodos = new LinkedList<Pizza>();
		pizzaspedidomotodos.add(napolitana);
		pizzaspedidomotodos.add(napolitana);
		Pedido primerpedidomotodos = new Pedido();
		primerpedidomotodos.setSolicitante("Juan");
		primerpedidomotodos.setTelefono(1162312497);
		primerpedidomotodos.setDireccion("Florencio Varela 699");
		primerpedidomotodos.setListaPizzas(pizzaspedidomotodos);
		primerpedidomotodos.setMoto(motodos);
		servicioPedido.guardarPedido(primerpedidomotodos);

		// Pedidos moto 3	- Para agilizar los 3 piden las mismas pizzas
		
		List<Pizza> pizzaspedido = new LinkedList<Pizza>();
		pizzaspedido.add(napolitana);
		pizzaspedido.add(mozzarella);
		Pedido primerpedidomototres = new Pedido();
		primerpedidomototres.setSolicitante("Jose");
		primerpedidomototres.setTelefono(1162343596);
		primerpedidomototres.setDireccion("Almafuerte 129");
		primerpedidomototres.setListaPizzas(pizzaspedido);
		primerpedidomototres.setMoto(mototres);
		servicioPedido.guardarPedido(primerpedidomototres);

		Pedido segundopedidomototres = new Pedido();
		segundopedidomototres.setSolicitante("Jorge");
		segundopedidomototres.setTelefono(1134362596);
		segundopedidomototres.setDireccion("Alvear 580");
		segundopedidomototres.setListaPizzas(pizzaspedido);
		segundopedidomototres.setMoto(mototres);
		servicioPedido.guardarPedido(segundopedidomototres);

		Pedido tercerpedidomototres = new Pedido();
		tercerpedidomototres.setSolicitante("Susana");
		tercerpedidomototres.setTelefono(1162359634);
		tercerpedidomototres.setDireccion("Alvarez Jonte 256");
		tercerpedidomototres.setListaPizzas(pizzaspedido);
		tercerpedidomototres.setMoto(mototres);
		servicioPedido.guardarPedido(tercerpedidomototres);

		
	// Pedidos moto 4	- Para agilizar los 2 piden las mismas pizzas
		
		List<Pizza> pizzaspedidos = new LinkedList<Pizza>();
		pizzaspedidos.add(dechoclo);
		pizzaspedidos.add(calabresa);
		Pedido primerpedidomotocuatro = new Pedido();
		primerpedidomotocuatro.setSolicitante("Maria");
		primerpedidomotocuatro.setTelefono(1165923436);
		primerpedidomotocuatro.setDireccion("Asamblea 1453");
		primerpedidomotocuatro.setListaPizzas(pizzaspedidos);
		primerpedidomotocuatro.setMoto(motocuatro);
		servicioPedido.guardarPedido(primerpedidomotocuatro);

		Pedido segundopedidomotocuatro = new Pedido();
		segundopedidomotocuatro.setSolicitante("Marcos");
		segundopedidomotocuatro.setTelefono(1134356296);
		segundopedidomotocuatro.setDireccion("Alcorta Amancio 1801");
		segundopedidomotocuatro.setListaPizzas(pizzaspedidos);
		segundopedidomotocuatro.setMoto(motocuatro);
		servicioPedido.guardarPedido(segundopedidomotocuatro);
	

		return new ModelAndView("redirect:/home");
	}
	
}
