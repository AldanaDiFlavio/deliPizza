package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PedidoDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoto;
import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;

@Service("servicioPedido")
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

	@Inject
	private PedidoDao servicioPedidoDao;

	@Inject
	private ServicioMoto servicioMoto;

	@Override
	public List<Pedido> traerTodosLosPedidos() {
		return servicioPedidoDao.traerTodosLosPedidos();
	}

	@Override
	public void guardarPedido(Pedido pedido) {
		servicioPedidoDao.guardarPedido(pedido);

	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		servicioPedidoDao.actualizarPedido(pedido);
	}

	@Override
	public Pedido generarPedidoParaPersistirConLasPizzasDelPedido(Pedido pedido,
			List<Pizza> pizzasDelPedidoParaPersistir) {
		Pedido pedidoParaPersistir = new Pedido();
		pedidoParaPersistir.setSolicitante(pedido.getSolicitante());
		pedidoParaPersistir.setDireccion(pedido.getDireccion());
		pedidoParaPersistir.setDemora(pedido.getDemora());
		pedidoParaPersistir.setPrecio(pedido.getPrecio());
		pedidoParaPersistir.setTelefono(pedido.getTelefono());
		pedidoParaPersistir.setEstado("EnEspera"); // EnEspera
		pedidoParaPersistir.setListaPizzas(pizzasDelPedidoParaPersistir);
		return pedidoParaPersistir;
	}

	@Override
	public List<Pedido> traerListaDePedidosEnEspera() {

		List<Pedido> traerTodosLosPedidos = traerTodosLosPedidos();
		List<Pedido> pedido = new LinkedList<Pedido>();
		for (Pedido pedidos : traerTodosLosPedidos) {
			if (pedidos.getEstado().equals("EnEspera")) {
				pedido.add(pedidos);
			}
		}
		return pedido;
	}

	@Override
	public Pedido traerElPedidoEnDeliveryAsignadoAUnaMoto(Moto moto) {
		return servicioPedidoDao.traerElPedidoEnDeliveryAsignadoAUnaMoto(moto);
	}

	@Override
	public List<Pizza> generarPedidoParaMostrar(List<Pizza> carrito) {
		List<Pizza> pedidoAMostrar = new LinkedList<Pizza>();
		for (Pizza p : carrito) {
			pedidoAMostrar.add(p);
		}
		return pedidoAMostrar;
	}

	@Override
	public Integer calcularPrecioTotalPorTodasLasPizzasDelPedido(List<Pizza> carrito) {
		Integer preciototalportodaslaspizzas = 0;
		for (Pizza p : carrito) {
			preciototalportodaslaspizzas = preciototalportodaslaspizzas + (p.getPrecio() * p.getCantidad());
		}
		return preciototalportodaslaspizzas;
	}

	@Override
	public void marcarUnPedidoComoEntregado(Pedido pedido) {
		pedido.setEstado("Entregado");
	}

	@Override
	public void siHayPedidosEnEsperaAsignarLaMotoQuelibere(Moto moto, List<Pedido> ListaDePedidosEnEspera) {
		Pedido pedido = ListaDePedidosEnEspera.get(0);
		pedido.setMoto(moto);
		pedido.setEstado("EnDelivery");
		actualizarPedido(pedido);
		moto.setEstado("Ocupada");
		List<Pedido> listaPedidos = moto.getlistaPedido();
		listaPedidos.add(pedido);
		moto.setlistaPedido(listaPedidos);
		servicioMoto.actualizarMoto(moto);
	}

}
