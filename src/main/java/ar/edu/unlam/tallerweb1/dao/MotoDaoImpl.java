package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Moto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("motoDao")
public class MotoDaoImpl implements MotoDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public void guardarMoto(Moto moto) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(moto);		
	}
	
	@Override
	public void actualizarMoto(Moto moto) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(moto);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moto> traerTodasLasMotos() {
		
		return( sessionFactory.getCurrentSession()
				.createCriteria(Moto.class)
				.list());	
	}

	@Override
	public Moto traerUnaMotoPorSuPatente(String patente) {
		
		 return (Moto) ( sessionFactory.getCurrentSession()
		 .createCriteria(Moto.class)
		 .add(Restrictions.eq("patente", patente))
		 .uniqueResult());
		 
	}

	@Override
	public Moto traerUnaMotoPorSuId(Long id) {
		 return (Moto) ( sessionFactory.getCurrentSession()
				 .createCriteria(Moto.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult());
	}

}
