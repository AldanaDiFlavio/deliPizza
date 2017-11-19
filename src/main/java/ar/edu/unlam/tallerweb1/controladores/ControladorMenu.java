package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorMenu {

	@Inject
	private ServicioPizza servicioPizza;

	@Inject
	private ServicioPedido servicioPedido;

	@Inject
	private ServicioMoto servicioMoto;

	@RequestMapping("/home")
	public ModelAndView irAHome(HttpServletRequest request, HttpServletResponse response) {

		ModelMap modelo = new ModelMap();
		//suma el precio de todas las pizza en el pedido, inicializado en 0
		Integer preciototalportodaslaspizzas = 0;
		//clase Pizza
		Pizza lapizza = new Pizza();
		
		List<Pizza> listClave = new LinkedList<Pizza>();
		List<Integer> listValor = new LinkedList<Integer>();
		if (request.getSession().getAttribute("carrito") != null) {
			Map<Pizza, Integer> carrito = (Map<Pizza, Integer>) request.getSession().getAttribute("carrito");

			Iterator it = carrito.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
				Pizza clave = e.getKey();
				Integer valor = e.getValue();
				preciototalportodaslaspizzas = preciototalportodaslaspizzas
						+ (clave.getPrecio() * clave.getCantidad());
				listClave.add(clave);
				listValor.add(valor);
			}

			modelo.put("cantidadpizzaspedidas", listValor);
			modelo.put("pizzaspedidas", listClave);
			modelo.put("pizzaspedidasparamodificar", listClave);
		}
		

		//lista de pizzas
		List<Pizza> listaPizzas= servicioPizza.traerTodasLasPizzas();
		
		for (Pizza lp : listClave) {
			for (Pizza lc : listClave) {
				if (lp.getNombre() == lc.getNombre()) {
					lp.setAniadida(true);
				}
			}
		}

		modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);
		modelo.put("lapizza", lapizza);
		modelo.put("listaPizzas", listaPizzas);

		if (request.getSession().getAttribute("pedidos") != null) {

			HttpSession sesion2 = request.getSession();

			List<Pedido> pedidos;
			pedidos = (List<Pedido>) sesion2.getAttribute("pedidos");

			modelo.put("pedidorealizado", pedidos);
		}
		return new ModelAndView("home", modelo);
	}

	@RequestMapping(path = "/aniadirCarrito", method = RequestMethod.POST)
	public ModelAndView aniadirCarrito(@ModelAttribute("lapizza") Pizza lapizza, HttpServletRequest request,
			HttpServletResponse response) {

		String nombre = lapizza.getNombre();

		if (lapizza.getCantidad() == null) {
			lapizza.setCantidad(1);
		} else {
			lapizza.setCantidad(lapizza.getCantidad());
		}

		ModelMap modelo = new ModelMap();

		Pizza pizza = servicioPizza.traerUnaPizzaPorSuNombre(nombre);
		pizza.setCantidad(lapizza.getCantidad());
		Integer preciototalporpizza = pizza.getPrecio();
		preciototalporpizza = pizza.getPrecio() * lapizza.getCantidad();
		pizza.setPreciototal(preciototalporpizza);

		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;

		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedHashMap<Pizza, Integer>();
		} else {
			carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
		}

		List<Pizza> listClave = new LinkedList<Pizza>();
		Iterator it = carrito.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
			Pizza clave = e.getKey();
			listClave.add(clave);

		}
		Pizza clavee = null;
		for (Pizza p : listClave) {

			if (p.getNombre().equals(pizza.getNombre())) {
				clavee = p;
			}
		}
		if (clavee != null) {
			carrito.remove(clavee);
		}

		pizza.setAniadida(true);

		int cantidad = 1;

		carrito.put(pizza, cantidad);

		sesion.setAttribute("carrito", carrito);

		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1, j = i + 10; i < 100; i++) {
			numeros.add(i);
		}

		modelo.put("numeros", numeros);

		modelo.put("precio", preciototalporpizza);

		modelo.put("nombresdepizzas", listClave);

		return new ModelAndView("redirect:/home");

	}

	@RequestMapping(path = "/quitarDelCarrito", method = RequestMethod.GET)
	public ModelAndView quitarDelCarrito(@RequestParam("nombre") String nombre, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();

		Pizza pizza = servicioPizza.traerUnaPizzaPorSuNombre(nombre);

		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;

		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedHashMap<Pizza, Integer>();
		} else {
			carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
		}

		List<Pizza> listClave = new LinkedList<Pizza>();
		Iterator it = carrito.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
			Pizza clave = e.getKey();
			listClave.add(clave);

		}
		Pizza clavee = null;
		for (Pizza p : listClave) {

			if (p.getNombre().equals(pizza.getNombre())) {
				clavee = p;
			}
		}
		if (clavee != null) {
			carrito.remove(clavee);
		}

		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(path = "/cancelarCarrito", method = RequestMethod.GET)
	public ModelAndView cancelarCarrito(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();

		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;

		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedHashMap<Pizza, Integer>();
		} else {
			carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
		}

		carrito.clear();

		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(path = "/mostrar-pedido", method = RequestMethod.POST)
	public ModelAndView mostrarPedido(HttpServletRequest request, HttpServletResponse response) {

		Integer preciototalportodaslaspizzas = 0;

		ModelMap modelo = new ModelMap();

		Pedido pedido = new Pedido();

		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;
		carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");

		List<Pizza> listClave = new LinkedList<Pizza>();
		List<Integer> listValor = new LinkedList<Integer>();
		Iterator it = carrito.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
			Pizza clave = e.getKey();
			Integer valor = e.getValue();
			listClave.add(clave);
			listValor.add(valor);
		}

		List<Pizza> pedidorealmostrar = new LinkedList<Pizza>(); // Para Mostrar
		for (Pizza p : listClave) {
			preciototalportodaslaspizzas = preciototalportodaslaspizzas + (p.getPrecio() * p.getCantidad());
			pedidorealmostrar.add(p);
		}

		modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);
		modelo.put("pedido", pedido);
		modelo.put("pedidos", pedidorealmostrar);
		return new ModelAndView("pedidos", modelo);

	}

	@RequestMapping(path = "/validar-pedido", method = RequestMethod.POST)
	public ModelAndView validarPedido(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request,
			HttpServletResponse response) {

		ModelMap modelo = new ModelMap();

		HttpSession sesion2 = request.getSession();

		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;
		carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");

		List<Pizza> listClave = servicioPizza.generarListaDePizzasDelCarrito(carrito);

		List<Pizza> pizzasDelPedidoParaPersistir = servicioPizza
				.generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(listClave); // Genera
																									// la
																									// lista
																									// de
																									// Pizzas
																									// para
																									// persistir
																									// a
																									// partir
																									// de
																									// la
																									// lista
																									// de
																									// pizzas
																									// que
																									// se
																									// pusieron
																									// en
																									// el
																									// Carrito

		List<Pizza> pizzasDelPedidoParaMostrar = servicioPizza
				.generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(listClave);

		Integer preciodelpedido = servicioPizza.calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(listClave);

		LinkedList<Pedido> pedidos; // Genero la session de pedidos para poder
									// mostrarla al cliente
		if (sesion.getAttribute("pedidos") == null) {
			pedidos = new LinkedList<Pedido>();
		} else {
			pedidos = (LinkedList<Pedido>) sesion2.getAttribute("pedidos");
		}

		// pedido --> Es el pedido del cliente que viene del form de pedidos.jsp

		pedido.setListaPizzas(pizzasDelPedidoParaMostrar);

		pedido.setPrecio(preciodelpedido);

		// pedidoParaPersistir --> Es el pedido que voy a persistir // Genera el
		// pedido para persistir // pedidoParaPersistir.setEstado(false); //
		// EnEspera
		Pedido pedidoParaPersistir = servicioPedido.generarPedidoParaPersistirConLasPizzasDelPedido(pedido,
				pizzasDelPedidoParaPersistir);

		Moto moto = servicioMoto.consultarSiHayMotosLibres();
		if (moto.getEstado().equals("Libre")) { // Si hay almenos una moto Libre
			pedidoParaPersistir.setMoto(moto);
			pedidoParaPersistir.setEstado("EnDelivery"); // Esta En Delivery
			moto.setEstado("Ocupada"); // Paso la moto a Ocupada
			servicioMoto.actualizarMoto(moto);
		}

		servicioPedido.guardarPedido(pedidoParaPersistir);

		pedidos.add(pedido);

		sesion.setAttribute("pedidos", pedidos);

		carrito.clear();
		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");

	}

	@RequestMapping("/pedidos")
	public ModelAndView irAPedido(HttpServletRequest request, HttpServletResponse response) {

		ModelMap modelo = new ModelMap();

		Pizza lapizza = new Pizza();

		return new ModelAndView("pedidos", modelo);
	}

	@RequestMapping("/administrar")
	public ModelAndView panelAdministrador(HttpServletRequest request, HttpServletResponse response) {

		if (request.getSession().getAttribute("usuario") != null) {

			ModelMap modelo = new ModelMap();

			List<Moto> motos = servicioMoto.traerTodasLasMotos();
			List<Moto> motosocupadas = servicioMoto.traerListaDeMotosOcupadas();
			List<Moto> motoslibres = servicioMoto.traerListaDeMotosLibres();
			List<Pedido> ListaDePedidosEnEspera = servicioPedido.traerListaDePedidosEnEspera();

			modelo.put("motos", motos);
			modelo.put("motoslibres", motoslibres);
			modelo.put("motosocupadas", motosocupadas);
			modelo.put("listadepedio", motosocupadas);
			modelo.put("pedidosenespera", ListaDePedidosEnEspera);
			return new ModelAndView("administrar", modelo);
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(path = "/liberar-moto")
	public ModelAndView desadherirseBandas(@RequestParam("patente") String patente, HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") != null) {

			Moto moto = servicioMoto.traerUnaMotoPorSuPatente(patente);

			servicioMoto.liberarMotoDePedido(moto);

			List<Pedido> listaPedido = moto.getlistaPedido();
			listaPedido.clear();
			moto.setlistaPedido(listaPedido);

			servicioMoto.actualizarMoto(moto);

			List<Pedido> ListaDePedidosEnEspera = servicioPedido.traerListaDePedidosEnEspera();

			if (!ListaDePedidosEnEspera.isEmpty()) {
				Pedido pedido = ListaDePedidosEnEspera.get(0);
				pedido.setMoto(moto);

				pedido.setEstado("EnDelivery");
				servicioPedido.actualizarPedido(pedido);
				moto.setEstado("Ocupada");
				List<Pedido> listaPedidos = moto.getlistaPedido();
				listaPedidos.add(pedido);
				moto.setlistaPedido(listaPedidos);
				servicioMoto.actualizarMoto(moto);
			}

			return new ModelAndView("redirect:/administrar");
		}
		return new ModelAndView("redirect:/login");
	}
}
