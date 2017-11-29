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
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioIngrediente;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorInsertar {

	@Inject
	private ServicioPizza servicioPizza;

	@Inject
	private ServicioPedido servicioPedido;

	@Inject
	private ServicioMoto servicioMoto;

	@Inject
	private ServicioUsuario servicioUsuario;

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

		Ingrediente morron = new Ingrediente();
		morron.setNombre("Morron");
		morron.setPrecio((float) 10);
		servicioIngrediente.guardarIngrediente(morron);

		Ingrediente cebolla = new Ingrediente();
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
		pizzamuzzarella.setTamanio("Normal");

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
		pizzacalabresa.setTamanio("Normal");

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
		pizzacalabresa2.setTamanio("Normal");

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
		pizzajamonymorron.setTamanio("Normal");

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
		pizzafugazzetta.setTamanio("Normal");

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
		pizzanapolitana.setTamanio("Normal");

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
		pizzacapresse.setTamanio("Normal");

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
		pizzaroquefort.setTamanio("Normal");

		Ingrediente ir = servicioIngrediente.buscarIngrediente("roquefort");
		List<Ingrediente> ingredienteroquefort = new LinkedList<Ingrediente>();
		ingredienteroquefort.add(ir);

		pizzaroquefort.setListaIngrediente(ingredienteroquefort);
		servicioPizza.guardarPizza(pizzaroquefort);

		Pizza pizzachoclo = new Pizza();
		pizzachoclo.setNombre("Choclo");
		pizzachoclo.setImagen("choclo.jpg");
		pizzachoclo.setPrecio(200);
		pizzachoclo.setTcoccion(25);
		pizzachoclo.setTamanio("Normal");

		Ingrediente ich = servicioIngrediente.buscarIngrediente("choclo");
		List<Ingrediente> ingredientechoclo = new LinkedList<Ingrediente>();
		ingredientechoclo.add(ich);

		pizzachoclo.setListaIngrediente(ingredientechoclo);
		servicioPizza.guardarPizza(pizzachoclo);

		Pizza pizzapalmito = new Pizza();
		pizzapalmito.setNombre("Palmito");
		pizzapalmito.setImagen("palmito.jpg");
		pizzapalmito.setPrecio(250);
		pizzapalmito.setTcoccion(20);
		pizzapalmito.setTamanio("Normal");

		Ingrediente ip = servicioIngrediente.buscarIngrediente("palmito");
		List<Ingrediente> ingredientepalmito = new LinkedList<Ingrediente>();
		ingredientepalmito.add(ip);

		pizzapalmito.setListaIngrediente(ingredientepalmito);
		servicioPizza.guardarPizza(pizzapalmito);

		Pizza pizzaanana = new Pizza();
		pizzaanana.setNombre("Anana");
		pizzaanana.setImagen("anana.jpg");
		pizzaanana.setPrecio(250);
		pizzaanana.setTcoccion(20);
		pizzaanana.setTamanio("Normal");

		Ingrediente ia = servicioIngrediente.buscarIngrediente("anana");
		List<Ingrediente> ingredienteanana = new LinkedList<Ingrediente>();
		ingredienteanana.add(ia);

		pizzaanana.setListaIngrediente(ingredienteanana);
		servicioPizza.guardarPizza(pizzaanana);

		Pizza pizzaprovolone = new Pizza();
		pizzaprovolone.setNombre("Provolone");
		pizzaprovolone.setImagen("provolone.jpg");
		pizzaprovolone.setPrecio(230);
		pizzaprovolone.setTcoccion(20);
		pizzaprovolone.setTamanio("Normal");

		Ingrediente ipr = servicioIngrediente.buscarIngrediente("provolone");
		List<Ingrediente> ingredienteprovolone = new LinkedList<Ingrediente>();
		ingredienteprovolone.add(ipr);

		pizzaprovolone.setListaIngrediente(ingredienteprovolone);
		servicioPizza.guardarPizza(pizzaprovolone);

		Pizza pizzarucula = new Pizza();
		pizzarucula.setNombre("Rucula");
		pizzarucula.setImagen("rucula.jpg");
		pizzarucula.setPrecio(230);
		pizzarucula.setTcoccion(20);
		pizzarucula.setTamanio("Normal");

		Ingrediente iru = servicioIngrediente.buscarIngrediente("rucula");
		List<Ingrediente> ingredienterucula = new LinkedList<Ingrediente>();
		ingredienterucula.add(iru);

		pizzarucula.setListaIngrediente(ingredienterucula);
		servicioPizza.guardarPizza(pizzarucula);

		return new ModelAndView("redirect:/insertar-motos");
	}

	@RequestMapping("/insertar-motos")
	public ModelAndView insertarMotos() {

		Moto motouno = new Moto();
		motouno.setPatente("A123BCD");
		motouno.setConductor("Jose Luis");
		motouno.setEstado("Ocupada");
		servicioMoto.guardarMoto(motouno);

		Moto motodos = new Moto();
		motodos.setPatente("B124ACD");
		motodos.setConductor("Mario Lopez");
		motodos.setEstado("Ocupada");
		servicioMoto.guardarMoto(motodos);

		Moto mototres = new Moto();
		mototres.setPatente("A353CBD");
		mototres.setConductor("Carlos Gimenez");
		mototres.setEstado("Ocupada");
		servicioMoto.guardarMoto(mototres);

		Moto motocuatro = new Moto();
		motocuatro.setPatente("D250ACB");
		motocuatro.setConductor("Raul Villanueva");
		motocuatro.setEstado("Ocupada");
		servicioMoto.guardarMoto(motocuatro);

		Moto motocinco = new Moto();
		motocinco.setPatente("A553BCD");
		motocinco.setConductor("Felipe Tamales");
		motocinco.setEstado("Ocupada");
		servicioMoto.guardarMoto(motocinco);

		Moto motoseis = new Moto();
		motoseis.setPatente("B1222GH");
		motoseis.setConductor("Lorenzo Lamas");
		motoseis.setEstado("Ocupada");
		servicioMoto.guardarMoto(motoseis);

		Moto motosiete = new Moto();
		motosiete.setPatente("9517CBD");
		motosiete.setConductor("Enrique Antonio");
		motosiete.setEstado("Ocupada");
		servicioMoto.guardarMoto(motosiete);

		Moto motoocho = new Moto();
		motoocho.setPatente("AIJACBZ");
		motoocho.setConductor("Joaquin Ortega");
		motoocho.setEstado("Libre");
		servicioMoto.guardarMoto(motoocho);

		return new ModelAndView("redirect:/insertar-pedidos-asignarmotos");
	}

	@RequestMapping("/insertar-pedidos-asignarmotos")
	public ModelAndView insertarPedidosAsignarMotos() {
		long mo = 1;
		long na = 6;
		long ca = 2;
		long ch = 9;
		long cap = 7;
		long pro = 12;
		long ro = 8;
		long an = 11;
		Pizza napolitana = servicioPizza.traerUnaPizzaPorSuId(na);
		Pizza calabresa = servicioPizza.traerUnaPizzaPorSuId(ca);
		Pizza dechoclo = servicioPizza.traerUnaPizzaPorSuId(ch);
		Pizza mozzarella = servicioPizza.traerUnaPizzaPorSuId(mo);
		Pizza capresse = servicioPizza.traerUnaPizzaPorSuId(cap);
		Pizza provolone = servicioPizza.traerUnaPizzaPorSuId(pro);
		Pizza roquefort = servicioPizza.traerUnaPizzaPorSuId(ro);
		Pizza anana = servicioPizza.traerUnaPizzaPorSuId(an);

		// Pedido moto 1

		List<Pizza> pizzaspedidomotouno = new LinkedList<Pizza>();
		pizzaspedidomotouno.add(calabresa);
		pizzaspedidomotouno.add(napolitana);
		Pedido pedidomotouno = new Pedido();
		pedidomotouno.setSolicitante("Jorge");
		pedidomotouno.setTelefono(1134362596);
		pedidomotouno.setDireccion("Alvear 580");
		pedidomotouno.setDemora(40);
		pedidomotouno.setListaPizzas(pizzaspedidomotouno);
		pedidomotouno.setPrecio(350);
		pedidomotouno.setEstado("EnDelivery");
		Moto motouno = servicioMoto.traerUnaMotoPorSuPatente("A123BCD");
		pedidomotouno.setMoto(motouno);
		servicioPedido.guardarPedido(pedidomotouno);
		List<Pedido> lpedidomotouno = new LinkedList<Pedido>();
		lpedidomotouno.add(pedidomotouno);
		motouno.setlistaPedido(lpedidomotouno);
		servicioMoto.actualizarMoto(motouno);

		// Pedido moto 2

		List<Pizza> pizzaspedidomotodos = new LinkedList<Pizza>();
		pizzaspedidomotodos.add(roquefort);
		pizzaspedidomotodos.add(napolitana);
		Pedido pedidomotodos = new Pedido();
		pedidomotodos.setSolicitante("Juan");
		pedidomotodos.setTelefono(1155312497);
		pedidomotodos.setDireccion("Albarellos 320");
		pedidomotodos.setDemora(45);
		pedidomotodos.setListaPizzas(pizzaspedidomotodos);
		pedidomotodos.setPrecio(400);
		pedidomotodos.setEstado("EnDelivery");
		Moto motodos = servicioMoto.traerUnaMotoPorSuPatente("B124ACD");
		pedidomotodos.setMoto(motodos);
		servicioPedido.guardarPedido(pedidomotodos);
		List<Pedido> lpedidomotodos = new LinkedList<Pedido>();
		lpedidomotodos.add(pedidomotodos);
		motodos.setlistaPedido(lpedidomotodos);
		servicioMoto.actualizarMoto(motodos);

		// Pedido moto 3

		List<Pizza> pizzaspedidomototres = new LinkedList<Pizza>();
		pizzaspedidomototres.add(napolitana);
		pizzaspedidomototres.add(napolitana);
		pizzaspedidomototres.add(mozzarella);
		pizzaspedidomototres.add(anana);
		Pedido pedidomototres = new Pedido();
		pedidomototres.setSolicitante("Jose");
		pedidomototres.setTelefono(1145343548);
		pedidomototres.setDireccion("Almafuerte 129");
		pedidomototres.setDemora(40);
		pedidomototres.setListaPizzas(pizzaspedidomototres);
		pedidomototres.setPrecio(800);
		pedidomototres.setEstado("EnDelivery");
		Moto mototres = servicioMoto.traerUnaMotoPorSuPatente("A353CBD");
		pedidomototres.setMoto(mototres);
		servicioPedido.guardarPedido(pedidomototres);
		List<Pedido> lpedidomototres = new LinkedList<Pedido>();
		lpedidomototres.add(pedidomototres);
		mototres.setlistaPedido(lpedidomototres);
		servicioMoto.actualizarMoto(mototres);

		// Pedido moto 4

		List<Pizza> pizzaspedidomotocuatro = new LinkedList<Pizza>();
		pizzaspedidomotocuatro.add(napolitana);
		pizzaspedidomotocuatro.add(anana);
		pizzaspedidomotocuatro.add(mozzarella);
		Pedido pedidomotocuatro = new Pedido();
		pedidomotocuatro.setSolicitante("Maria");
		pedidomotocuatro.setTelefono(1165923436);
		pedidomotocuatro.setDireccion("Asamblea 1453");
		pedidomotocuatro.setDemora(45);
		pedidomotocuatro.setListaPizzas(pizzaspedidomotocuatro);
		pedidomotocuatro.setPrecio(600);
		pedidomotocuatro.setEstado("EnDelivery");
		Moto motocuatro = servicioMoto.traerUnaMotoPorSuPatente("D250ACB");
		pedidomotocuatro.setMoto(motocuatro);
		servicioPedido.guardarPedido(pedidomotocuatro);
		List<Pedido> lpedidomotocuatro = new LinkedList<Pedido>();
		lpedidomotocuatro.add(pedidomotocuatro);
		motocuatro.setlistaPedido(lpedidomotocuatro);
		servicioMoto.actualizarMoto(motocuatro);

		// Pedido moto 5

		List<Pizza> pizzaspedidomotocinco = new LinkedList<Pizza>();
		pizzaspedidomotocinco.add(dechoclo);
		pizzaspedidomotocinco.add(calabresa);
		Pedido pedidomotocinco = new Pedido();
		pedidomotocinco.setSolicitante("Susana");
		pedidomotocinco.setTelefono(1162359634);
		pedidomotocinco.setDireccion("Alvarez Jonte 256");
		pedidomotocinco.setDemora(55);
		pedidomotocinco.setListaPizzas(pizzaspedidomotocinco);
		pedidomotocinco.setPrecio(350);
		pedidomotocinco.setEstado("EnDelivery");
		Moto motocinco = servicioMoto.traerUnaMotoPorSuPatente("A553BCD");
		pedidomotocinco.setMoto(motocinco);
		servicioPedido.guardarPedido(pedidomotocinco);
		List<Pedido> lpedidomotocinco = new LinkedList<Pedido>();
		lpedidomotocinco.add(pedidomotocinco);
		motocinco.setlistaPedido(lpedidomotocinco);
		servicioMoto.actualizarMoto(motocinco);

		// Pedido moto 6

		List<Pizza> pizzaspedidomotoseis = new LinkedList<Pizza>();
		pizzaspedidomotoseis.add(provolone);
		pizzaspedidomotoseis.add(napolitana);
		pizzaspedidomotoseis.add(capresse);
		pizzaspedidomotoseis.add(anana);
		Pedido pedidomotoseis = new Pedido();
		pedidomotoseis.setSolicitante("Marcos");
		pedidomotoseis.setTelefono(1134356970);
		pedidomotoseis.setDireccion("Alcorta Amancio 1801");
		pedidomotoseis.setDemora(42);
		pedidomotoseis.setListaPizzas(pizzaspedidomotoseis);
		pedidomotoseis.setPrecio(930);
		pedidomotoseis.setEstado("EnDelivery");
		Moto motoseis = servicioMoto.traerUnaMotoPorSuPatente("B1222GH");
		pedidomotoseis.setMoto(motoseis);
		servicioPedido.guardarPedido(pedidomotoseis);
		List<Pedido> lpedidomotoseis = new LinkedList<Pedido>();
		lpedidomotoseis.add(pedidomotoseis);
		motoseis.setlistaPedido(lpedidomotoseis);
		servicioMoto.actualizarMoto(motoseis);

		// Pedido moto 7

		List<Pizza> pizzaspedidomotosiete = new LinkedList<Pizza>();
		pizzaspedidomotosiete.add(dechoclo);
		pizzaspedidomotosiete.add(dechoclo);
		Pedido pedidomotosiete = new Pedido();
		pedidomotosiete.setSolicitante("Romina");
		pedidomotosiete.setTelefono(1195738291);
		pedidomotosiete.setDireccion("AV cristiania 3050");
		pedidomotosiete.setDemora(37);
		pedidomotosiete.setListaPizzas(pizzaspedidomotosiete);
		pedidomotosiete.setPrecio(400);
		pedidomotosiete.setEstado("EnDelivery");
		Moto motosiete = servicioMoto.traerUnaMotoPorSuPatente("9517CBD");
		pedidomotosiete.setMoto(motosiete);
		servicioPedido.guardarPedido(pedidomotosiete);
		List<Pedido> lpedidomotosiete = new LinkedList<Pedido>();
		lpedidomotosiete.add(pedidomotosiete);
		motosiete.setlistaPedido(lpedidomotosiete);
		servicioMoto.actualizarMoto(motosiete);

		return new ModelAndView("redirect:/insertar-administradores");
	}

	@RequestMapping("/insertar-administradores")
	public ModelAndView insertarAdministradores() {

		Usuario uAMatias = new Usuario();
		uAMatias.setUser("Admin Matias");
		uAMatias.setEmail("matias@gmail.com");
		uAMatias.setPassword("matias");
		uAMatias.setRol("Administrador");
		servicioUsuario.guardarUsuario(uAMatias);

		Usuario uAFlor = new Usuario();
		uAFlor.setUser("Admin Flor");
		uAFlor.setEmail("flor@gmail.com");
		uAFlor.setPassword("flor");
		uAFlor.setRol("Administrador");
		servicioUsuario.guardarUsuario(uAFlor);

		Usuario uAClara = new Usuario();
		uAClara.setUser("Admin Clara");
		uAClara.setEmail("clara@gmail.com");
		uAClara.setPassword("clara");
		uAClara.setRol("Administrador");
		servicioUsuario.guardarUsuario(uAClara);

		Usuario uAldana = new Usuario();
		uAldana.setUser("Admin Aldana");
		uAldana.setEmail("aldana@gmail.com");
		uAldana.setPassword("aldana");
		uAldana.setRol("Administrador");
		servicioUsuario.guardarUsuario(uAldana);

		return new ModelAndView("redirect:/home");
	}
}
