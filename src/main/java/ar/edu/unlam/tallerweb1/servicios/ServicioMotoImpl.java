package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.MotoDao;

import ar.edu.unlam.tallerweb1.modelo.Moto;

@Service("servicioMoto")
@Transactional
public class ServicioMotoImpl implements ServicioMoto {

	@Inject
	private MotoDao servicioMotoDao;
	
	@Override
	public void guardarMoto(Moto moto) {
		 servicioMotoDao.guardarMoto(moto);
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

}
