package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IngredienteDao;
import ar.edu.unlam.tallerweb1.modelo.Ingrediente;

@Service("servicioIngrediente")
@Transactional
public class ServicioIngredienteImpl implements ServicioIngrediente {

	@Inject
	private IngredienteDao servicioIngredienteDao;

	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		servicioIngredienteDao.guardarIngrediente(ingrediente);
	}

	@Override
	public Ingrediente buscarIngrediente(String ingrediente) {
		return servicioIngredienteDao.buscarIngrediente(ingrediente);

	}

	@Override
	public List<Ingrediente> todosLosIngredientes() {
		return servicioIngredienteDao.todosLosIngredientes();
	}

}
