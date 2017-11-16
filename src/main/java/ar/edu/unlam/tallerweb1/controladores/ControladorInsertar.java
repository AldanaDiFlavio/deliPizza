package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioIngrediente;
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
	
	@Inject
	private ServicioIngrediente servicioIngrediente;
	
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

		Boolean grande=true;
		Boolean chica=false;
		
		Ingrediente muzzarella = new Ingrediente();
		muzzarella.setNombre("Muzzarella");
		muzzarella.setPrecio((float) 10);
		servicioIngrediente.guardarIngrediente(muzzarella);
		
		Ingrediente tomate = new Ingrediente();
		tomate.setNombre("Tomate");
		tomate.setPrecio((float) 10);
		servicioIngrediente.guardarIngrediente(tomate);
		
		
		Ingrediente jamon = new Ingrediente();
		jamon.setNombre("Jamon");
		jamon.setPrecio((float) 12);
		servicioIngrediente.guardarIngrediente(jamon);
		
		Ingrediente	morron = new Ingrediente();
		morron.setNombre("Morron");
		morron.setPrecio((float) 10);
		servicioIngrediente.guardarIngrediente(morron);
		
		Ingrediente	cebolla = new Ingrediente();
		cebolla.setNombre("Cebolla");
		cebolla.setPrecio((float) 10);
		servicioIngrediente.guardarIngrediente(cebolla);
		
		Ingrediente choclo = new Ingrediente();
		choclo.setNombre("Choclo");
		choclo.setPrecio((float) 8);
		servicioIngrediente.guardarIngrediente(choclo);
		
		Ingrediente huevo = new Ingrediente();
		huevo.setNombre("Huevo");
		huevo.setPrecio((float) 5);
		servicioIngrediente.guardarIngrediente(huevo);
		
		Ingrediente roquefort = new Ingrediente();
		roquefort.setNombre("Roquefort");
		roquefort.setPrecio((float) 20);
		servicioIngrediente.guardarIngrediente(roquefort);
		
		Ingrediente provolone = new Ingrediente();
		provolone.setNombre("Provolone");
		provolone.setPrecio((float) 20);
		servicioIngrediente.guardarIngrediente(provolone);
		
		Ingrediente anchoas = new Ingrediente();
		anchoas.setNombre("Anchoas");
		anchoas.setPrecio((float) 20);
		servicioIngrediente.guardarIngrediente(anchoas);
		
		Ingrediente longaniza = new Ingrediente();
		longaniza.setNombre("Longaniza");
		longaniza.setPrecio((float) 15);
		servicioIngrediente.guardarIngrediente(longaniza);
			
		Ingrediente palmito = new Ingrediente();
		palmito.setNombre("Palmito");
		palmito.setPrecio((float) 18);
		servicioIngrediente.guardarIngrediente(palmito);
		
		Ingrediente anana = new Ingrediente();
		anana.setNombre("Anana");
		anana.setPrecio((float) 20);
		servicioIngrediente.guardarIngrediente(anana);
		
		Ingrediente rucula = new Ingrediente();
		rucula.setNombre("Rucula");
		rucula.setPrecio((float) 15);
		servicioIngrediente.guardarIngrediente(rucula);
		
		Ingrediente albahaca = new Ingrediente();
		albahaca.setNombre("Albahaca");
		albahaca.setPrecio((float) 15);
		servicioIngrediente.guardarIngrediente(albahaca);
		
						

		Pizza pizzamuzzarella = new Pizza();
		pizzamuzzarella.setNombre("Muzzarella");
		pizzamuzzarella.setImagen("muzzarella.jpg");
		pizzamuzzarella.setPrecio(150);
		pizzamuzzarella.setTcoccion(20);
		pizzamuzzarella.setTamanio(grande);
		pizzamuzzarella.setAclaracion(null);
				
		Ingrediente im = servicioIngrediente.buscarIngrediente("muzzarella");
		List<Ingrediente> ingredientemuzzarella = new LinkedList<Ingrediente>();
		ingredientemuzzarella.add(im);
		
		pizzamuzzarella.setListaIngrediente(ingredientemuzzarella);	
		servicioPizza.guardarPizza(pizzamuzzarella);
		
		
		Pizza pizzacalabresa = new Pizza();
		pizzacalabresa.setNombre("Calabresa");
		pizzacalabresa.setImagen("calabresa.jpg");
		pizzacalabresa.setPrecio(150);
		pizzacalabresa.setTcoccion(20);
		pizzacalabresa.setTamanio(grande);
		pizzacalabresa.setAclaracion(null);
				
		Ingrediente ic = servicioIngrediente.buscarIngrediente("longaniza");
		List<Ingrediente> ingredientecalabresa = new LinkedList<Ingrediente>();
		ingredientecalabresa.add(ic);
		
		pizzacalabresa.setListaIngrediente(ingredientecalabresa);	
		servicioPizza.guardarPizza(pizzacalabresa);
		
		
		Pizza pizzacalabresa2 = new Pizza();
		pizzacalabresa2.setNombre("Calabresa Especial");
		pizzacalabresa2.setImagen("calabresae.jpg");
		pizzacalabresa2.setPrecio(200);
		pizzacalabresa2.setTcoccion(25);
		pizzacalabresa2.setTamanio(grande);
		pizzacalabresa2.setAclaracion(null);
		
	
		Ingrediente ic21 = servicioIngrediente.buscarIngrediente("longaniza");
		Ingrediente ic22 = servicioIngrediente.buscarIngrediente("tomate");
		Ingrediente ic23 = servicioIngrediente.buscarIngrediente("anchoas");
		List<Ingrediente> ingredientecalabresa2 = new LinkedList<Ingrediente>();
		ingredientecalabresa2.add(ic21);
		ingredientecalabresa2.add(ic22);
		ingredientecalabresa2.add(ic23);
		
		
		pizzacalabresa2.setListaIngrediente(ingredientecalabresa2);	
		servicioPizza.guardarPizza(pizzacalabresa2);
		
		
		Pizza pizzajamonymorron = new Pizza();
		pizzajamonymorron.setNombre("Jamon y Morron");
		pizzajamonymorron.setImagen("jamonymorron.jpg");
		pizzajamonymorron.setPrecio(150);
		pizzajamonymorron.setTcoccion(20);
		pizzajamonymorron.setTamanio(chica);
		pizzajamonymorron.setAclaracion(null);
		
		
		Ingrediente ijm1 = servicioIngrediente.buscarIngrediente("jamon");
		Ingrediente ijm2 = servicioIngrediente.buscarIngrediente("morron");
	
		List<Ingrediente> ingredientejamonymorron = new LinkedList<Ingrediente>();
		ingredientejamonymorron.add(ijm1);
		ingredientejamonymorron.add(ijm2);
				
		pizzajamonymorron.setListaIngrediente(ingredientejamonymorron);	
		servicioPizza.guardarPizza(pizzajamonymorron);
		
		
		
		Pizza pizzafugazzetta = new Pizza();
		pizzafugazzetta.setNombre("Fugazzetta");
		pizzafugazzetta.setImagen("fugazzetta.jpg");
		pizzafugazzetta.setPrecio(200);
		pizzafugazzetta.setTcoccion(25);
		pizzafugazzetta.setTamanio(grande);
		pizzafugazzetta.setAclaracion(null);
		
			
		Ingrediente ifu = servicioIngrediente.buscarIngrediente("cebolla");
		List<Ingrediente> ingredientefugazzetta = new LinkedList<Ingrediente>();
		ingredientefugazzetta.add(ifu);
		
	
		pizzafugazzetta.setListaIngrediente(ingredientefugazzetta);	
		servicioPizza.guardarPizza(pizzafugazzetta);
		
		Pizza pizzanapolitana = new Pizza();
		pizzanapolitana.setNombre("Napolitana");
		pizzanapolitana.setImagen("napolitana.jpg");
		pizzanapolitana.setPrecio(200);
		pizzanapolitana.setTcoccion(25);
		pizzanapolitana.setTamanio(grande);
		pizzanapolitana.setAclaracion(null);
		
		Ingrediente in = servicioIngrediente.buscarIngrediente("tomate");
		List<Ingrediente> ingredientenapolitana = new LinkedList<Ingrediente>();
		ingredientenapolitana.add(in);
		
		pizzanapolitana.setListaIngrediente(ingredientenapolitana);	
		servicioPizza.guardarPizza(pizzanapolitana);
		
		
		Pizza pizzacapresse = new Pizza();
		pizzacapresse.setNombre("Capresse");
		pizzacapresse.setImagen("capresse.jpg");
		pizzacapresse.setPrecio(250);
		pizzacapresse.setTcoccion(25);
		pizzacapresse.setTamanio(grande);
		pizzacapresse.setAclaracion(null);
		
		Ingrediente ica1 = servicioIngrediente.buscarIngrediente("tomate");
		Ingrediente ica2 = servicioIngrediente.buscarIngrediente("albahaca");
		List<Ingrediente> ingredientecapresse = new LinkedList<Ingrediente>();
		ingredientecapresse.add(ica1);		
		ingredientecapresse.add(ica2);		

				
		pizzacapresse.setListaIngrediente(ingredientecapresse);	
		servicioPizza.guardarPizza(pizzacapresse);
		
		
		Pizza pizzaroquefort = new Pizza();
		pizzaroquefort.setNombre("Roquefort");
		pizzaroquefort.setImagen("roquefort.jpg");
		pizzaroquefort.setPrecio(200);
		pizzaroquefort.setTcoccion(25);
		pizzaroquefort.setTamanio(grande);
		pizzaroquefort.setAclaracion(null);
		

		Ingrediente ir = servicioIngrediente.buscarIngrediente("roquefort");
		List<Ingrediente> ingredienteroquefort =new LinkedList<Ingrediente>();
		ingredienteroquefort.add(ir);	

		
		pizzaroquefort.setListaIngrediente(ingredienteroquefort);	
		servicioPizza.guardarPizza(pizzaroquefort);
		
		
		Pizza pizzachoclo = new Pizza();
		pizzachoclo.setNombre("Choclo");
		pizzachoclo.setImagen("choclo.jpg");
		pizzachoclo.setPrecio(200);
		pizzachoclo.setTcoccion(25);
		pizzachoclo.setTamanio(grande);
		pizzachoclo.setAclaracion(null);
		
		Ingrediente ich = servicioIngrediente.buscarIngrediente("choclo");
		List<Ingrediente> ingredientechoclo =new LinkedList<Ingrediente>();
		ingredientechoclo.add(ich);	

				
		pizzachoclo.setListaIngrediente(ingredientechoclo);	
		servicioPizza.guardarPizza(pizzachoclo);
		
		
		Pizza pizzapalmito = new Pizza();
		pizzapalmito.setNombre("Palmito");
		pizzapalmito.setImagen("palmito.jpg");
		pizzapalmito.setPrecio(250);
		pizzapalmito.setTcoccion(20);
		pizzapalmito.setTamanio(grande);
		pizzapalmito.setAclaracion(null);
		
		Ingrediente ip = servicioIngrediente.buscarIngrediente("palmito");
		List<Ingrediente> ingredientepalmito =new LinkedList<Ingrediente>();
		ingredientepalmito.add(ip);	

		
		pizzapalmito.setListaIngrediente(ingredientepalmito);	
		servicioPizza.guardarPizza(pizzapalmito);
		
		Pizza pizzaanana = new Pizza();
		pizzaanana.setNombre("Anana");
		pizzaanana.setImagen("anana.jpg");
		pizzaanana.setPrecio(250);
		pizzaanana.setTcoccion(20);
		pizzaanana.setTamanio(grande);
		pizzaanana.setAclaracion(null);
		
		Ingrediente ia = servicioIngrediente.buscarIngrediente("anana");
		List<Ingrediente> ingredienteanana =new LinkedList<Ingrediente>();
		ingredienteanana.add(ia);	
		
		pizzaanana.setListaIngrediente(ingredienteanana);	
		servicioPizza.guardarPizza(pizzaanana);
		

		Pizza pizzaprovolone = new Pizza();
		pizzaprovolone.setNombre("Provolone");
		pizzaprovolone.setImagen("provolone.jpg");
		pizzaprovolone.setPrecio(230);
		pizzaprovolone.setTcoccion(20);
		pizzaprovolone.setTamanio(grande);
		pizzaprovolone.setAclaracion(null);
		
		Ingrediente ipr = servicioIngrediente.buscarIngrediente("provolone");
		List<Ingrediente> ingredienteprovolone =new LinkedList<Ingrediente>();
		ingredienteprovolone.add(ipr);	
		
		
		pizzaprovolone.setListaIngrediente(ingredienteprovolone);	
		servicioPizza.guardarPizza(pizzaprovolone);
		
		
		Pizza pizzarucula = new Pizza();
		pizzarucula.setNombre("Rucula");
		pizzarucula.setImagen("rucula.jpg");
		pizzarucula.setPrecio(230);
		pizzarucula.setTcoccion(20);
		pizzarucula.setTamanio(grande);
		pizzarucula.setAclaracion(null);
		

		Ingrediente iru = servicioIngrediente.buscarIngrediente("rucula");
		List<Ingrediente> ingredienterucula =new LinkedList<Ingrediente>();
		ingredienterucula.add(iru);	
		
		
		pizzarucula.setListaIngrediente(ingredienterucula);	
		servicioPizza.guardarPizza(pizzarucula);
		
//		Pizza pizzaespecial = new Pizza();
//		pizzaespecial.setNombre("Especial");
//		pizzaespecial.setImagen("pizzaespecial.jpg");
//		pizzaespecial.setPrecio(300);
//		pizzaespecial.setTcoccion(20);
//		pizzaespecial.setTamanio(grande);
//		pizzaespecial.setAclaracion(null);
//		
//				
//		List<Ingrediente> todosLosIngredientes = servicioIngrediente.todosLosIngredientes();
//		
//		pizzaespecial.setListaIngrediente(todosLosIngredientes);
//		servicioPizza.guardarPizza(pizzaespecial);
//		
		return new ModelAndView("redirect:/insertar-motos");
	}

	@RequestMapping("/insertar-pedidos")
	public ModelAndView insertarPedidos() {

		Pizza napolitana = servicioPizza.traerUnaPizzaPorSuNombre("Napolitana");
		Pizza calabresa = servicioPizza.traerUnaPizzaPorSuNombre("Calabresa");
		Pizza dechoclo = servicioPizza.traerUnaPizzaPorSuNombre("Choclo");
		Pizza muzzarella = servicioPizza.traerUnaPizzaPorSuNombre("Muzzarella");

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
		pizzassegundopedido.add(muzzarella);
		Pedido segundopedido = new Pedido();
		segundopedido.setSolicitante("Mariano");
		segundopedido.setTelefono(1159464863);
		segundopedido.setDireccion("Falsa 123");
		segundopedido.setListaPizzas(pizzassegundopedido);
		servicioPedido.guardarPedido(segundopedido);

		List<Pizza> pizzastercerpedido = new LinkedList<Pizza>();
		pizzastercerpedido.add(muzzarella);
		pizzastercerpedido.add(muzzarella);
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
