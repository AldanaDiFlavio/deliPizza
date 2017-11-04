package ar.edu.unlam.tallerweb1;


import java.util.LinkedList;
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
		
		/*Preparacion*/ 
	Session session = getSession();
	Boolean grande=true;
	Boolean chica=false;
	
	Ingrediente tomate = new Ingrediente();
	tomate.setNombre("Tomate");
	tomate.setPrecio((float) 10);
	session.save(tomate);
	
	
	Ingrediente jamon = new Ingrediente();
	jamon.setNombre("Jamon");
	jamon.setPrecio((float) 12);
	session.save(jamon);
	
	Ingrediente	morron = new Ingrediente();
	morron.setNombre("Morron");
	morron.setPrecio((float) 10);
	session.save(morron);
	
	Ingrediente	cebolla = new Ingrediente();
	cebolla.setNombre("Cebolla");
	cebolla.setPrecio((float) 10);
	session.save(cebolla);
	
	Ingrediente choclo = new Ingrediente();
	choclo.setNombre("Choclo");
	choclo.setPrecio((float) 8);
	session.save(choclo);
	
	Ingrediente huevo = new Ingrediente();
	huevo.setNombre("Huevo");
	huevo.setPrecio((float) 5);
	session.save(huevo);
	
	Ingrediente roquefort = new Ingrediente();
	roquefort.setNombre("Roquefort");
	roquefort.setPrecio((float) 20);
	session.save(roquefort);
	
	Ingrediente provolone = new Ingrediente();
	provolone.setNombre("Provolone");
	provolone.setPrecio((float) 20);
	session.save(provolone);
	
	Ingrediente anchoas = new Ingrediente();
	anchoas.setNombre("Anchoas");
	anchoas.setPrecio((float) 20);
	session.save(anchoas);
	
	Ingrediente longaniza = new Ingrediente();
	longaniza.setNombre("Longaniza");
	longaniza.setPrecio((float) 15);
	session.save(longaniza);
		
	Ingrediente palmito = new Ingrediente();
	palmito.setNombre("Palmito");
	palmito.setPrecio((float) 18);
	session.save(palmito);
	
	Ingrediente anana = new Ingrediente();
	anana.setNombre("Anana");
	anana.setPrecio((float) 20);
	session.save(anana);
	
	Ingrediente rucula = new Ingrediente();
	rucula.setNombre("Rucula");
	rucula.setPrecio((float) 15);
	session.save(rucula);
	
	Ingrediente albahaca = new Ingrediente();
	albahaca.setNombre("Albahaca");
	albahaca.setPrecio((float) 15);
	session.save(albahaca);
	
	
			
	Pizza pizzacalabresa = new Pizza();
	pizzacalabresa.setNombre("Pizza Calabresa");
	pizzacalabresa.setImagen("calabresa.jpg");
	pizzacalabresa.setPrecio(150);
	pizzacalabresa.setTcoccion(20);
	pizzacalabresa.setTamanio(grande);
	pizzacalabresa.setAclaracion(null);

//  no es necesario cuando tengo los objetos dentro del mismo metodo test. se resolveria commo en *
//	Se deja a modo de ejemplo por si separo la carga de ingredientes en otro metodo test 
	List<Ingrediente> ingredientecalabresa;
	ingredientecalabresa =  session.createCriteria(Ingrediente.class)
							.add(Restrictions.eq("nombre", "Longaniza"))
							.list();
	
	pizzacalabresa.setListaIngrediente(ingredientecalabresa);	
	session.save(pizzacalabresa);
	
	
	Pizza pizzacalabresa2 = new Pizza();
	pizzacalabresa2.setNombre("Pizza Calabresa Especial");
	pizzacalabresa2.setImagen("calabresa2.jpg");
	pizzacalabresa2.setPrecio(200);
	pizzacalabresa2.setTcoccion(25);
	pizzacalabresa2.setTamanio(grande);
	pizzacalabresa2.setAclaracion(null);

//	*
	List<Ingrediente> ingredientecalabresa2 = new LinkedList<Ingrediente>();
	ingredientecalabresa2.add(longaniza);
	ingredientecalabresa2.add(tomate);
	ingredientecalabresa2.add(anchoas);
	
	pizzacalabresa2.setListaIngrediente(ingredientecalabresa2);	
	session.save(pizzacalabresa2);
	
	
	Pizza pizzajamonymorron = new Pizza();
	pizzajamonymorron.setNombre("Pizza Jamon y Morron");
	pizzajamonymorron.setImagen("jamonymorron.jpg");
	pizzajamonymorron.setPrecio(150);
	pizzajamonymorron.setTcoccion(20);
	pizzajamonymorron.setTamanio(chica);
	pizzajamonymorron.setAclaracion(null);

	
	
	 Criteria ingredientejamonymorron = session.createCriteria(Ingrediente.class);
     Criterion i1 = Restrictions.eq("nombre", "jamon");
     Criterion i2 = Restrictions.eq("nombre", "morron");
     LogicalExpression orExp = Restrictions.or(i1,i2);
     ingredientejamonymorron.add(orExp);
    
     List<Ingrediente> results = ingredientejamonymorron.list();
	
	pizzajamonymorron.setListaIngrediente(results);	
	session.save(pizzajamonymorron);
	
	
	Pizza pizzafugazzetta = new Pizza();
	pizzafugazzetta.setNombre("Pizza Fugazzetta");
	pizzafugazzetta.setImagen("fugazzetta.jpg");
	pizzafugazzetta.setPrecio(200);
	pizzafugazzetta.setTcoccion(25);
	pizzafugazzetta.setTamanio(grande);
	pizzafugazzetta.setAclaracion(null);

	List<Ingrediente> ingredientefugazzetta = new LinkedList<Ingrediente>();
	ingredientefugazzetta.add(cebolla);
	
	pizzafugazzetta.setListaIngrediente(ingredientefugazzetta);	
	session.save(pizzafugazzetta);
	
	Pizza pizzanapolitana = new Pizza();
	pizzanapolitana.setNombre("Pizza Napolitana");
	pizzanapolitana.setImagen("napolitana.jpg");
	pizzanapolitana.setPrecio(200);
	pizzanapolitana.setTcoccion(25);
	pizzanapolitana.setTamanio(grande);
	pizzanapolitana.setAclaracion(null);

	List<Ingrediente> ingredientenapolitana = new LinkedList<Ingrediente>();
	ingredientenapolitana.add(tomate);
	
	pizzanapolitana.setListaIngrediente(ingredientenapolitana);	
	session.save(pizzanapolitana);
	
	Pizza pizzacapresse = new Pizza();
	pizzacapresse.setNombre("Pizza Capresse");
	pizzacapresse.setImagen("capresse.jpg");
	pizzacapresse.setPrecio(250);
	pizzacapresse.setTcoccion(25);
	pizzacapresse.setTamanio(grande);
	pizzacapresse.setAclaracion(null);

	List<Ingrediente> ingredientecapresse = new LinkedList<Ingrediente>();
	ingredientecapresse.add(tomate);
	ingredientecapresse.add(albahaca);
	
	pizzacapresse.setListaIngrediente(ingredientecapresse);	
	session.save(pizzacapresse);
	
	
	Pizza pizzaroquefort = new Pizza();
	pizzaroquefort.setNombre("Pizza Roquefort");
	pizzaroquefort.setImagen("roquefort.jpg");
	pizzaroquefort.setPrecio(200);
	pizzaroquefort.setTcoccion(25);
	pizzaroquefort.setTamanio(grande);
	pizzaroquefort.setAclaracion(null);

	List<Ingrediente> ingredienteroquefort = new LinkedList<Ingrediente>();
	ingredienteroquefort.add(roquefort);
	
	pizzaroquefort.setListaIngrediente(ingredienteroquefort);	
	session.save(pizzaroquefort);
	
	
	Pizza pizzachoclo = new Pizza();
	pizzachoclo.setNombre("Pizza Choclo");
	pizzachoclo.setImagen("choclo.jpg");
	pizzachoclo.setPrecio(200);
	pizzachoclo.setTcoccion(25);
	pizzachoclo.setTamanio(grande);
	pizzachoclo.setAclaracion(null);

	List<Ingrediente> ingredientechoclo = new LinkedList<Ingrediente>();
	ingredientechoclo.add(choclo);
	
	pizzachoclo.setListaIngrediente(ingredientechoclo);	
	session.save(pizzachoclo);
	
	
	Pizza pizzapalmito = new Pizza();
	pizzapalmito.setNombre("Pizza Palmito");
	pizzapalmito.setImagen("palmito.jpg");
	pizzapalmito.setPrecio(250);
	pizzapalmito.setTcoccion(20);
	pizzapalmito.setTamanio(grande);
	pizzapalmito.setAclaracion(null);

	List<Ingrediente> ingredientepalmito = new LinkedList<Ingrediente>();
	ingredientepalmito.add(palmito);
	
	pizzapalmito.setListaIngrediente(ingredientepalmito);	
	session.save(pizzapalmito);
	
	Pizza pizzaanana = new Pizza();
	pizzaanana.setNombre("Pizza Anana");
	pizzaanana.setImagen("anana.jpg");
	pizzaanana.setPrecio(250);
	pizzaanana.setTcoccion(20);
	pizzaanana.setTamanio(grande);
	pizzaanana.setAclaracion(null);

	List<Ingrediente> ingredienteanana = new LinkedList<Ingrediente>();
	ingredienteanana.add(anana);
	
	pizzaanana.setListaIngrediente(ingredienteanana);	
	session.save(pizzaanana);
	

	Pizza pizzaprovolone = new Pizza();
	pizzaprovolone.setNombre("Pizza Provolone");
	pizzaprovolone.setImagen("provolone.jpg");
	pizzaprovolone.setPrecio(230);
	pizzaprovolone.setTcoccion(20);
	pizzaprovolone.setTamanio(grande);
	pizzaprovolone.setAclaracion(null);

	List<Ingrediente> ingredienteprovolone = new LinkedList<Ingrediente>();
	ingredienteprovolone.add(provolone);
	
	pizzaprovolone.setListaIngrediente(ingredienteprovolone);	
	session.save(pizzaprovolone);
	
	
	Pizza pizzarucula = new Pizza();
	pizzarucula.setNombre("Pizza Rucula");
	pizzarucula.setImagen("rucula.jpg");
	pizzarucula.setPrecio(230);
	pizzarucula.setTcoccion(20);
	pizzarucula.setTamanio(grande);
	pizzarucula.setAclaracion(null);

	List<Ingrediente> ingredienterucula = new LinkedList<Ingrediente>();
	ingredienterucula.add(rucula);
	
	pizzarucula.setListaIngrediente(ingredienterucula);	
	session.save(pizzarucula);
	
	Pizza pizzaespecial = new Pizza();
	pizzaespecial.setNombre("Pizza Especial");
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
