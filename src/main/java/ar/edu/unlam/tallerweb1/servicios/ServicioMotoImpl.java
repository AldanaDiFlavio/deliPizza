package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.MotoDao;

import ar.edu.unlam.tallerweb1.modelo.Moto;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

@Service("servicioMoto")
@Transactional
public class ServicioMotoImpl implements ServicioMoto {

	@Inject
	private MotoDao servicioMotoDao;
	
	@Inject
	private ServicioPedido servicioPedido;
	
	@Override
	public void guardarMoto(Moto moto) {
		 servicioMotoDao.guardarMoto(moto);
	}
	
	@Override
	public void actualizarMoto(Moto moto) {
		servicioMotoDao.actualizarMoto(moto);	
	}

	@Override
	public List<Moto> traerTodasLasMotos() {
		return servicioMotoDao.traerTodasLasMotos();
	}

	@Override
	public Moto traerUnaMotoPorSuPatente(String patente) {
		return servicioMotoDao.traerUnaMotoPorSuPatente(patente);
	}

	@Override
	public Moto traerUnaMotoPorSuId(Long id) {
		return servicioMotoDao.traerUnaMotoPorSuId(id);
	}

	@Override
	public Moto consultarSiHayMotosLibres() {
			
		List <Moto> traerTodasLasMotos = traerTodasLasMotos();
		Moto moto = new Moto();	
		moto.setEstado("Ocupada"); // Estado - True - Es Ocupada
		for (Moto motos : traerTodasLasMotos) {
			if(motos.getEstado().equals("Libre")){ // Estado - False - Es libre
				moto = motos;
			break;
			}
		}
		if(moto.getEstado() == "Ocupada"){
		return moto; // Sin motos
	}else
		return moto; // Con almenos una moto
	}

	@Override
	public List<Moto> traerListaDeMotosOcupadas() {
		
		List <Moto> traerTodasLasMotos = traerTodasLasMotos();
		List <Moto> moto = new LinkedList<Moto>();	
		for (Moto motos : traerTodasLasMotos) {
			if(motos.getEstado().equals("Ocupada")){ 
				moto.add(motos);
			}
		}
		return moto;
	}

	@Override
	public List<Moto> traerListaDeMotosLibres() {
		List <Moto> traerTodasLasMotos = traerTodasLasMotos();
		List <Moto> moto = new LinkedList<Moto>();	
		for (Moto motos : traerTodasLasMotos) {
			if(motos.getEstado().equals("Libre")){ 
				moto.add(motos);
			}
		}
		return moto;
	}

	@Override
	public void liberarMotoDePedido(Moto moto) {
		moto.setEstado("Libre"); 
		actualizarMoto(moto);
		Pedido pedido = moto.getlistaPedido().get(0);
		pedido.setEstado("Entregado");
		servicioPedido.actualizarPedido(pedido);
	}
		
}