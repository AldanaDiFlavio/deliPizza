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

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorMenu {

	@Inject
	private ServicioPizza servicioPizza;

	@RequestMapping("/home")
	public ModelAndView irAHome(HttpServletRequest request, HttpServletResponse response) {

		Integer preciototalportodaslaspizzas = 0;

		ModelMap modelo = new ModelMap();
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
				preciototalportodaslaspizzas = preciototalportodaslaspizzas + (clave.getPrecio() * clave.getCantidad());
				listClave.add(clave);
				listValor.add(valor);
			}

			modelo.put("cantidadpizzaspedidas", listValor);
			modelo.put("pizzaspedidas", listClave);
			modelo.put("pizzaspedidasparamodificar", listClave);
		}

		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1, j = i + 10; i < 100; i++) {
			numeros.add(i);
		}

		modelo.put("numeros", numeros);

		List<Pizza> listaPizzas;
		if (listClave.isEmpty()) {
			listaPizzas = servicioPizza.traerTodasLasPizzas();
		} else { // no funca verrrrrr
			listaPizzas = servicioPizza.traerTodasLasPizzas();

			for (Pizza lp : listClave) {
				for (Pizza lc : listClave) {
					if (lp.getNombre() == lc.getNombre()) {
						lp.setAniadida(true);
					}
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

		Integer preciototal = 0;

		ModelMap modelo = new ModelMap();

		HttpSession sesion2 = request.getSession();
		HttpSession sesion = request.getSession();

		Map<Pizza, Integer> carrito;
		carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");

		List<Pizza> listClave = new LinkedList<Pizza>();
		Iterator it = carrito.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> e = (Map.Entry) it.next();
			Pizza clave = e.getKey();
			Integer valor = e.getValue();
			listClave.add(clave);
		}

		Pizza lapizzaa = new Pizza();
		List<Pizza> pedidoreal = new LinkedList<Pizza>(); // Persistir
		for (Pizza p : listClave) {
			for (int j = 0; j < p.getCantidad(); j++) {
				lapizzaa = servicioPizza.traerUnaPizzaPorSuNombre(p.getNombre());
				pedidoreal.add(lapizzaa);
			}
		}

		List<Pizza> pedidorealmostrar = new LinkedList<Pizza>(); // Para Mostrar
		for (Pizza p : listClave) {
			preciototal = preciototal + (p.getPrecio() * p.getCantidad());
			pedidorealmostrar.add(p);
		}

		LinkedList<Pedido> pedidos;
		if (sesion.getAttribute("pedidos") == null) {
			pedidos = new LinkedList<Pedido>();
		} else {
			pedidos = (LinkedList<Pedido>) sesion2.getAttribute("pedidos");
		}

		pedido.setListaPizzas(pedidorealmostrar);
		pedido.setPrecio(preciototal);
		// pedido es el que hay que persistir
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

}
