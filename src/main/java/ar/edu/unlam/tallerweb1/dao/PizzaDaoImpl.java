package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Pizza;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("pizzaDao")
public class PizzaDaoImpl implements PizzaDao {

	@Inject
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> traerTodasLasPizzas() {
		
		return(sessionFactory.getCurrentSession()
				.createCriteria(Pizza.class)
				.list());	
	}

	@Override
	public void guardarPizza(Pizza pizza) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(pizza);		
	}

	@Override
	public Pizza traerUnaPizzaPorSuNombre(String string) {
		
		 return (Pizza) ( sessionFactory.getCurrentSession()
		 .createCriteria(Pizza.class)
		 .add(Restrictions.eq("nombre", string))
		 .uniqueResult());
		 
	}

	@Override
	public Pizza traerUnaPizzaPorSuId(Long id) {
		 return (Pizza) ( sessionFactory.getCurrentSession()
				 .createCriteria(Pizza.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult());
	}

}
