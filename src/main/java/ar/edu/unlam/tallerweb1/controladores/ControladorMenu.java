package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

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
	public ModelAndView irAHome(HttpServletRequest request, HttpServletResponse response) {
		// Genero el mapa
		ModelMap modelo = new ModelMap();
		
//		Genero la lista de todas las pizzas
		List<Pizza> listaPizzas= servicioPizza.traerTodasLasPizzas();

		//Genero la pizza para el modelAttribute de aniadirAlCarrito
		Pizza lapizza = new Pizza();
		
		//Pregunto si la session no es null
		if (request.getSession().getAttribute("carrito") != null) {
			//Guardo el objeto de la session en la variable carrito
			List<Pizza> carrito = (List<Pizza>) request.getSession().getAttribute("carrito");		
			
			modelo.put("pizzaspedidas", carrito);
			
			Integer preciototalportodaslaspizzas = servicioPizza.calcularElprecioTotalPorTodasLasPizzasPedidas(carrito);
			
			modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);
			}
		
		modelo.put("listaPizzas", listaPizzas);
		
		modelo.put("lapizza", lapizza);

		if (request.getSession().getAttribute("pedidos") != null) {

			HttpSession sesion2 = request.getSession();

			List<Pedido> pedidos;
			pedidos = (List<Pedido>) sesion2.getAttribute("pedidos");

			modelo.put("pedidorealizado", pedidos);
		}
		return new ModelAndView("home", modelo);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/aniadirCarrito", method = RequestMethod.POST)
	public ModelAndView aniadirCarrito(@ModelAttribute("lapizza") Pizza laPizzaConLaCantidad, HttpServletRequest request,
			HttpServletResponse response) {	
		
		HttpSession sesion = request.getSession();
		
		Pizza pizza = servicioPizza.traerUnaPizzaPorSuId(laPizzaConLaCantidad.getId());

		laPizzaConLaCantidad = servicioPizza.calcularCantidadDePizzas(laPizzaConLaCantidad); // Quizas mal
		
		// Trae el precio total por la cantidad de pizzas, es lo que se mostrara al cliente
		pizza = servicioPizza.calcularTotalDeLaPizza(pizza,laPizzaConLaCantidad);
	
		List<Pizza> carrito;
	
		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) sesion.getAttribute("carrito");
		}	
		
		carrito = servicioPizza.aniadeAlCarritoOModifica(carrito,pizza);
		
		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/quitarDelCarrito", method = RequestMethod.GET)
	public ModelAndView quitarDelCarrito(@RequestParam("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {

		Pizza pizza = servicioPizza.traerUnaPizzaPorSuId(id);

		HttpSession sesion = request.getSession();

		List<Pizza> carrito;

		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) sesion.getAttribute("carrito");
		}

		carrito = servicioPizza.quitaDelCarrito(carrito,pizza);

		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/cancelarCarrito", method = RequestMethod.GET)
	public ModelAndView cancelarCarrito(HttpServletRequest request, HttpServletResponse response) {

		HttpSession sesion = request.getSession();

		List<Pizza> carrito;

		if (sesion.getAttribute("carrito") == null) {
			carrito = new LinkedList<Pizza>();
		} else {
			carrito = (List<Pizza>) sesion.getAttribute("carrito");
		}

		carrito.clear();

		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/mostrar-pedido", method = RequestMethod.POST)
	public ModelAndView mostrarPedido(HttpServletRequest request, HttpServletResponse response) {

		ModelMap modelo = new ModelMap();

		Pedido pedido = new Pedido();

		HttpSession sesion = request.getSession();

		List<Pizza> carrito;
		carrito = (List<Pizza>) sesion.getAttribute("carrito");

		List<Pizza> pedidoAMostrar = servicioPedido.generarPedidoParaMostrar(carrito);	
		
		Integer preciototalportodaslaspizzas = servicioPedido.calcularPrecioTotalPorTodasLasPizzasDelPedido(carrito);
				
		modelo.put("preciototalportodaslaspizzas", preciototalportodaslaspizzas);
		
		modelo.put("pedido", pedido);
		
		modelo.put("pedidos", pedidoAMostrar);
		
		return new ModelAndView("pedidos", modelo);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/validar-pedido", method = RequestMethod.POST)
	public ModelAndView validarPedido(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession sesion2 = request.getSession();

		HttpSession sesion = request.getSession();

		List<Pizza> carrito;
		carrito = (List<Pizza>) sesion.getAttribute("carrito");

		List<Pizza> pizzasDelPedidoParaPersistir = servicioPizza
				.generarListaDePizzasParaPersistirAPartirDeLaListaDePizzasDelCarrito(carrito); 
																						
		List<Pizza> pizzasDelPedidoParaMostrar = servicioPizza
				.generarListaDePizzasParaMostrarAlClienteAPartirDeLaListaDePizzasDelCarrito(carrito);

		Integer preciodelpedido = servicioPizza.calcularPrecioDelPedidoApartirDeLaListaDePizzasDelCarrito(carrito);

		LinkedList<Pedido> pedidos;
									
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
		} // Caso contrario agarro motos ocupadas las recorro y informo un promedio de la demora aproximada

		servicioPedido.guardarPedido(pedidoParaPersistir);
		pedido.setEstado(pedidoParaPersistir.getEstado());
		pedidos.add(pedido);

		sesion.setAttribute("pedidos", pedidos);

		carrito.clear();
		sesion.setAttribute("carrito", carrito);

		return new ModelAndView("redirect:/home");

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
			Pedido ped = servicioPedido.traerElPedidoEnDeliveryAsignadoAUnaMoto(moto);
			ped.setEstado("Entregado");
			servicioPedido.actualizarPedido(ped);


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
