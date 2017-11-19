package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Moto;

public interface ServicioMoto {
	
	void guardarMoto(Moto moto);
	
	List<Moto> traerTodasLasMotos();

	Moto traerUnaMotoPorSuPatente(String patente);

	Moto traerUnaMotoPorSuId(Long id);

	Moto consultarSiHayMotosLibres();

	void actualizarMoto(Moto moto);
	
	List<Moto> traerListaDeMotosOcupadas();

	List<Moto> traerListaDeMotosLibres();

	void liberarMotoDePedido(Moto moto);
}
