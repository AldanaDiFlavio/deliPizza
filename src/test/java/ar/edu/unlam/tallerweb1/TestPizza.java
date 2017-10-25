package ar.edu.unlam.tallerweb1;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Ingrediente;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;

public class TestPizza extends SpringTest{
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(false)
	public void testInsertarPizza() {
		
		/*Preparacio)*/ 
	Session session = getSession();
	Boolean grande=true;
	Boolean chica=false;
	
	Ingrediente tomate = new Ingrediente();
	tomate.setNombre("Tomate");
	tomate.setPrecio((float) 10);
	session.save(tomate);
	
	Ingrediente choclo = new Ingrediente();
	choclo.setNombre("choclo");
	choclo.setPrecio((float) 10);
	session.save(choclo);
	
	Ingrediente jamon = new Ingrediente();
	jamon.setNombre("jamon");
	jamon.setPrecio((float) 10);
	session.save(jamon);
	
	Ingrediente	morron = new Ingrediente();
	morron.setNombre("morron");
	morron.setPrecio((float) 10);
	session.save(morron);
	
	Ingrediente anchoas = new Ingrediente();
	anchoas.setNombre("anchoas");
	anchoas.setPrecio((float) 10);
	session.save(anchoas);
	
	Ingrediente longaniza = new Ingrediente();
	longaniza.setNombre("longaniza");
	longaniza.setPrecio((float) 10);
	session.save(longaniza);
	
	Ingrediente jym = new Ingrediente();
	jym.setNombre("jamonymorron");
	jym.setPrecio((float) 10);
	session.save(jym);
		
	Pizza pizzacalabresa = new Pizza();
	pizzacalabresa.setNombre("pizza Calabresa");
	pizzacalabresa.setImagen("calabresa.jpg");
	pizzacalabresa.setPrecio(150);
	pizzacalabresa.setTcoccion(20);
	pizzacalabresa.setTamanio(grande);
	pizzacalabresa.setAclaracion(null);

	List<Ingrediente> ingredientecalabresa;
	ingredientecalabresa =  session.createCriteria(Ingrediente.class)
							.add(Restrictions.eq("nombre", "longaniza"))
							.list();
	
	pizzacalabresa.setListaIngrediente(ingredientecalabresa);	
	session.save(pizzacalabresa);
	
	
	Pizza pizzacalabresa2 = new Pizza();
	pizzacalabresa2.setNombre("pizza Calabresa 2");
	pizzacalabresa2.setImagen("calabresa2.jpg");
	pizzacalabresa2.setPrecio(150);
	pizzacalabresa2.setTcoccion(20);
	pizzacalabresa2.setTamanio(grande);
	pizzacalabresa2.setAclaracion(null);

	List<Ingrediente> ingredientecalabresa2;
	ingredientecalabresa2 =  session.createCriteria(Ingrediente.class)
							.add(Restrictions.eq("nombre", "longaniza"))
							.list();
	
	pizzacalabresa2.setListaIngrediente(ingredientecalabresa2);	
	session.save(pizzacalabresa2);
	
	
	Pizza pizzajamonymorron = new Pizza();
	pizzajamonymorron.setNombre("pizza Jamon y Morron");
	pizzajamonymorron.setImagen("jamonymorron.jpg");
	pizzajamonymorron.setPrecio(150);
	pizzajamonymorron.setTcoccion(20);
	pizzajamonymorron.setTamanio(chica);
	pizzajamonymorron.setAclaracion(null);

	
	
	 Criteria ingredientejamonymorron = session.createCriteria(Ingrediente.class);
     Criterion i1 = Restrictions.eq("nombre", "jamonymorron");
     Criterion i2 = Restrictions.eq("nombre", "jamon");
     LogicalExpression orExp = Restrictions.or(i1,i2);
     ingredientejamonymorron.add(orExp);
    
     List<Ingrediente> results = ingredientejamonymorron.list();

	
	pizzajamonymorron.setListaIngrediente(results);	
	session.save(pizzajamonymorron);
	
	
	Pizza pizzaespecial = new Pizza();
	pizzaespecial.setNombre("pizza especial");
	pizzaespecial.setImagen("pizzaespecial.jpg");
	pizzaespecial.setPrecio(150);
	pizzaespecial.setTcoccion(20);
	pizzaespecial.setTamanio(chica);
	pizzaespecial.setAclaracion(null);
			
	List<Ingrediente> todosLosIngredientes;
	todosLosIngredientes = session.createCriteria(Ingrediente.class).list();
	
	pizzaespecial.setListaIngrediente(todosLosIngredientes);
	session.save(pizzaespecial);
	
	}

	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(false)
	public void testInsertarPedido() {
		Session session = getSession();	
		
			
		
		Pedido primerpedido = new Pedido();
		primerpedido.setSolicitante("Juan");
		primerpedido.setTelefono(1162343596);
		primerpedido.setDireccion("Florencio Varela 699");
//		primerpedido.setFecha(22-10-17);
		primerpedido.setCantidad(2);
		primerpedido.setEstado(false);
		

		List<Pizza> pizzasprimerpedido;
	
		pizzasprimerpedido = session.createCriteria(Pizza.class)
				    .add(Restrictions.eq("nombre", "pizza Calabresa"))
					.list();
		
		primerpedido.setListaPizzas(pizzasprimerpedido);
		
		session.save(primerpedido);
		
		
		
		
		Pedido segundopedido = new Pedido();
		segundopedido.setSolicitante("Clara");
		segundopedido.setTelefono(1533839081);
		segundopedido.setDireccion("Marmol 9879");
//		segundopedido.setFecha(22-10-17);
		segundopedido.setCantidad(5);
		segundopedido.setEstado(false);
		

	
		
		Criteria pizzasegundopedido = session.createCriteria(Pizza.class);
	     Criterion i1 = Restrictions.eq("nombre", "pizza Jamon y Morron");
	     Criterion i2 = Restrictions.eq("nombre", "pizza Calabresa");
	     LogicalExpression orExp = Restrictions.or(i1,i2);
	     pizzasegundopedido.add(orExp);
	    
	     List<Pizza> results = pizzasegundopedido.list();

	     segundopedido.setListaPizzas(results);
		 session.save(segundopedido);

	}
	
	
	}
