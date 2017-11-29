package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente;

@Repository("ingredienteDao")
public class IngredienteDaoImpl implements IngredienteDao {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(ingrediente);

	}

	@Override
	public Ingrediente buscarIngrediente(String ingrediente) {
		return (Ingrediente) (sessionFactory.getCurrentSession().createCriteria(Ingrediente.class)
				.add(Restrictions.eq("nombre", ingrediente)).uniqueResult());

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingrediente> todosLosIngredientes() {
		return (sessionFactory.getCurrentSession().createCriteria(Ingrediente.class).list());

	}

}
