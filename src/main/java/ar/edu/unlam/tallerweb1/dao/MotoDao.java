package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Moto;

public interface MotoDao {
	
	void guardarMoto(Moto moto);
	
	List<Moto> traerTodasLasMotos();

	Moto traerUnaMotoPorSuPatente(String patente);

	Moto traerUnaMotoPorSuId(Long id);

	void actualizarMoto(Moto moto);
	
}
