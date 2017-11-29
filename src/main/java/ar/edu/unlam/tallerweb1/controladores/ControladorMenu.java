package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@SuppressWarnings("unchecked")
	@RequestMapping("/home")
	public ModelAndView irAHome(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();

		List<Pizza> listaPizzas = servicioPizza.traerTodasLasPizzas();

		Pizza lapizza = new Pizza();

		if (request.getSession().getAttribute("carrito") != null) {

			List<Pizza> carrito = (List<Pizza>) request.getSession().getAttribute("carrito");

			modelo.put("pizzaspedidas", carrito);

			Integer preciototalportodaslaspizzas = servicioPizza.calcularElprecioTotalPorTodasLasPizzasPedidas(carrito);

			modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);
		}

		modelo.put("listaPizzas", listaPizzas);

		modelo.put("lapizza", lapizza);

		if (request.getSession().getAttribute("pedidos") != null) {

			List<Pedido> pedidos;
			pedidos = (List<Pedido>) request.getSession().getAttribute("pedidos");

			modelo.put("pedidorealizado", pedidos);
		}
		return new ModelAndView("home", modelo);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/aniadirCarrito", method = RequestMethod.POST)
	public ModelAndView aniadirCarrito(@ModelAttribute("lapizza") Pizza laPizzaConLaCantidad,
			HttpServletRequest request) {

		Pizza pizza = servicioPizza.traerUnaPizzaPorSuId(laPizzaConLaCantidad.getId());

		laPizzaConLaCantidad = servicioPizza.calcularCantidadDePizzas(laPizzaConLaCantidad);

		pizza = servicioPizza.calcularTotalDeLaPizza(pizza, laPizzaConLaCantidad);

		List<Pizza> carrito;

		if (request.getSession().getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) request.getSession().getAttribute("carrito");
		}

		carrito = servicioPizza.aniadeAlCarritoOModifica(carrito, pizza);

		request.getSession().setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/quitarDelCarrito", method = RequestMethod.GET)
	public ModelAndView quitarDelCarrito(@RequestParam("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {

		Pizza pizza = servicioPizza.traerUnaPizzaPorSuId(id);

		List<Pizza> carrito;

		if (request.getSession().getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) request.getSession().getAttribute("carrito");
		}

		carrito = servicioPizza.quitaDelCarrito(carrito, pizza);

		request.getSession().setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/cancelarCarrito", method = RequestMethod.GET)
	public ModelAndView cancelarCarrito(HttpServletRequest request) {

		List<Pizza> carrito;

		if (request.getSession().getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) request.getSession().getAttribute("carrito");
		}

		carrito.clear();

		request.getSession().setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/mostrar-pedido", method = RequestMethod.POST)
	public ModelAndView mostrarPedido(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();

		Pedido pedido = new Pedido();

		List<Pizza> carrito;
		carrito = (List<Pizza>) request.getSession().getAttribute("carrito");

		List<Pizza> pedidoAMostrar = servicioPedido.generarPedidoParaMostrar(carrito);

		Integer preciototalportodaslaspizzas = servicioPedido.calcularPrecioTotalPorTodasLasPizzasDelPedido(carrito);

		modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);

		modelo.put("pedido", pedido);

		modelo.put("pedidos", pedidoAMostrar);

		return new ModelAndView("pedidos", modelo);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/validar-pedido", method = RequestMethod.POST)
	public ModelAndView validarPedido(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request) {

		List<Pizza> carrito;

		carrito = (List<Pizza>) request.getSession().getAttribute("carrito");

		List<Pizza> pizzasDelPedidoParaPersistir = servicioPizza
				.generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(carrito);

		List<Pizza> pizzasDelPedidoParaMostrar = servicioPizza
				.generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(carrito);

		Integer preciodelpedido = servicioPizza.calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(carrito);

		LinkedList<Pedido> pedidos;

		if (request.getSession().getAttribute("pedidos") == null) {
			pedidos = new LinkedList<Pedido>();
		} else {
			pedidos = (LinkedList<Pedido>) request.getSession().getAttribute("pedidos");
		}

		pedido.setListaPizzas(pizzasDelPedidoParaMostrar);

		pedido.setPrecio(preciodelpedido);

		Pedido pedidoParaPersistir = servicioPedido.generarPedidoParaPersistirConLasPizzasDelPedido(pedido,
				pizzasDelPedidoParaPersistir);

		Moto moto = servicioMoto.consultarSiHayMotosLibres();

		if (moto.getEstado().equals("Libre")) {

			servicioMoto.asignarMotoAPedido(moto, pedidoParaPersistir);

		}

		servicioPedido.guardarPedido(pedidoParaPersistir);

		pedido.setEstado(pedidoParaPersistir.getEstado());

		pedidos.add(pedido);

		request.getSession().setAttribute("pedidos", pedidos);

		carrito.clear();

		request.getSession().setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");

	}

	@RequestMapping("/administrar")
	public ModelAndView panelAdministrador(HttpServletRequest request) {

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
	public ModelAndView liberarMoto(@RequestParam("patente") String patente, HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") != null) {

			Moto moto = servicioMoto.traerUnaMotoPorSuPatente(patente);

			servicioMoto.liberarMotoDePedido(moto);

			Pedido pedido = servicioPedido.traerElPedidoEnDeliveryAsignadoAUnaMoto(moto);

			servicioPedido.marcarUnPedidoComoEntregado(pedido);

			servicioPedido.actualizarPedido(pedido);

			servicioMoto.actualizarMoto(moto);

			List<Pedido> ListaDePedidosEnEspera = servicioPedido.traerListaDePedidosEnEspera();

			if (!ListaDePedidosEnEspera.isEmpty()) {
				servicioPedido.siHayPedidosEnEsperaAsignarLaMotoQuelibere(moto, ListaDePedidosEnEspera);
			}

			return new ModelAndView("redirect:/administrar");
		}
		return new ModelAndView("redirect:/login");
	}
}
