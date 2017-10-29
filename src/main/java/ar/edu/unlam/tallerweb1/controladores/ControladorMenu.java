package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.parsing.ParseState.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorMenu {
	
	@Inject
	private ServicioPizza servicioPizza; 

		@RequestMapping("/home")
		public ModelAndView irAHome(HttpServletRequest request, HttpServletResponse response){
					
			ModelMap modelo = new ModelMap();
			Pizza lapizza = new Pizza();
			
			if ( request.getSession().getAttribute("carrito") != null )
			{
			//	List<Pizza> carrito = (LinkedList<Pizza>) request.getSession().getAttribute("carrito"); 
				Map<Pizza, Integer> carrito = (Map<Pizza, Integer>) request.getSession().getAttribute("carrito"); 
				
				List<Pizza> listClave = new LinkedList<Pizza>();
				List<Integer> listValor = new LinkedList<Integer>();
				Iterator it = carrito.entrySet().iterator();
				while (it.hasNext()) {
				    Map.Entry<Pizza, Integer> e = (Map.Entry)it.next();
				    Pizza clave = e.getKey();
				    Integer valor = e.getValue();
				    listClave.add(clave);
				    listValor.add(valor);
				}
				
				modelo.put("cantidadpizzaspedidas", listValor);
				modelo.put("pizzaspedidas", listClave);
			}	
					
			
			 ArrayList<Integer> numeros = new ArrayList<Integer>();
		        for(int i = 1, j = i + 10; i < 100; i++) {
		        	numeros.add(i);
		        }
		        	
		    modelo.put("numeros", numeros);	
				
		//	Pedido pedido = new Pedido();
			
			List<Pizza> listaPizzas = servicioPizza.traerTodasLasPizzas();
			
		 	modelo.put("lapizza", lapizza);
			modelo.put("listaPizzas", listaPizzas);	
			
			if ( request.getSession().getAttribute("elpedido") != null )
			{
							
				 HttpSession sesion = request.getSession();
			        // List<Pizza> carrito;
			        Pedido elpedido; 
			        elpedido = (Pedido) sesion.getAttribute("elpedido");
				
				modelo.put("pizzasdelpedidorealizado",elpedido.getListaPizzas());
			}
			return new ModelAndView("home", modelo);			
		}	
		
		// @RequestMapping(path = "/aniadirCarrito/{nombre}/{cantidad}", method = RequestMethod.GET)
		// public ModelAndView aniadirCarrito(@PathVariable String nombre, @PathVariable Integer cantidad, HttpServletRequest request, HttpServletResponse response) {
		@RequestMapping(path = "/aniadirCarrito", method = RequestMethod.POST)
		public ModelAndView aniadirCarrito(@ModelAttribute("lapizza") Pizza lapizza, HttpServletRequest request, HttpServletResponse response) {
			
			Integer cantidad;
			String nombre = lapizza.getNombre();
			String pizzayaaniadida = nombre;
			
			if(lapizza.getCantidad() == null){
				cantidad = 1;
			}else{
				cantidad = lapizza.getCantidad();
			}
			
			ModelMap modelo = new ModelMap();			
			// List<Pizza> listaPizzas = servicioPizza.traerTodasLasPizzas();
			  //Obtenemos el producto que deseamos añadir al carrito
	        Pizza pizza = servicioPizza.traerUnaPizzaPorSuNombre(nombre);
	        pizza.setCantidad(cantidad);    	       
	        Integer precio = pizza.getPrecio();
	        precio = precio * cantidad;
	        pizza.setPrecio(precio);
	        
			HttpSession sesion = request.getSession();
	        // List<Pizza> carrito;
	        Map<Pizza, Integer> carrito; 
	        //Si no existe la sesion creamos al carrito de cmoras
	        if (sesion.getAttribute("carrito") == null) {
	         // carrito = new LinkedList<Pizza>();
	            carrito = new LinkedHashMap<Pizza, Integer>();
	        } else {
	            carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
	        }
	         
	        List<Pizza> listClave = new LinkedList<Pizza>();
	        Iterator it = carrito.entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry<Pizza, Integer> e = (Map.Entry)it.next();
		//	    if(e.getKey().getNombre() == pizza.getNombre()){
			    	 Pizza clave = e.getKey();
					 listClave.add(clave);
						    		    
			}
			Pizza clavee = null;
			 for (Pizza p: listClave){
	            	
	                if (p.getNombre().equals(pizza.getNombre())){
	                  clavee = p;
	                }
	            }
			 if(clavee != null){
	    	carrito.remove(clavee);
			 }
			 
			 
	        //if(carrito.containsKey(pizza)){
	        //	 carrito.remove(pizza);
	        	// carrito.put(pizza, cantidad);
	        	modelo.put("pizzayaaniadida", pizzayaaniadida);		
	       // }else{
	        //Creamos un detalle para el carrtio
	        boolean aniadida = false;
	        pizza.setAniadida(aniadida);	
	        carrito.put(pizza, cantidad);
	     //  carrito.add(pizza);
	      //  }        
	        //Actualizamos la sesion del carrito de compras
	        sesion.setAttribute("carrito", carrito);
	        //Redireccionamos al formulario de culminar la venta
	        //response.sendRedirect("registrarVenta.jsp");
	        
	        ArrayList<Integer> numeros = new ArrayList<Integer>();
	        for(int i = 1, j = i + 10; i < 100; i++) {
	        	numeros.add(i);
	        }
	        	
			modelo.put("numeros", numeros);		
	        
			modelo.put("precio", precio);		
					
        	modelo.put("nombresdepizzas", listClave);
        	return new ModelAndView("redirect:/home");
        //	return new ModelAndView("home", modelo);
	    }
			
		@RequestMapping(path = "/quitarDelCarrito", method = RequestMethod.GET)
		public ModelAndView quitarDelCarrito(@RequestParam("nombre") String nombre, HttpServletRequest request, HttpServletResponse response) {
			ModelMap modelo = new ModelMap();			
			
	        Pizza pizza = servicioPizza.traerUnaPizzaPorSuNombre(nombre);
	        	          
	        HttpSession sesion = request.getSession();
	        // List<Pizza> carrito;
	        Map<Pizza, Integer> carrito; 

	        if (sesion.getAttribute("carrito") == null) {
	            carrito = new LinkedHashMap<Pizza, Integer>();
	        } else {
	            carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
	        }

	        List<Pizza> listClave = new LinkedList<Pizza>();
	        Iterator it = carrito.entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry<Pizza, Integer> e = (Map.Entry)it.next();
		//	    if(e.getKey().getNombre() == pizza.getNombre()){
			    	 Pizza clave = e.getKey();
					 listClave.add(clave);
						    		    
			}
			Pizza clavee = null;
			 for (Pizza p: listClave){
	            	
	                if (p.getNombre().equals(pizza.getNombre())){
	                  clavee = p;
	                }
	            }
			 if(clavee != null){
	    	carrito.remove(clavee);
			 }
			          	        	                   
	        //Actualizamos la sesion del carrito de compras
	        sesion.setAttribute("carrito", carrito);
	        
	      //Redireccionamos al home
        	return new ModelAndView("redirect:/home");
	    }
		
		@RequestMapping(path = "/validar-pedido", method = RequestMethod.POST)
		// public ModelAndView validarLogin(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request, HttpServletResponse response) {
		 public ModelAndView validarLogin(HttpServletRequest request, HttpServletResponse response) {
			ModelMap modelo = new ModelMap();		
	        	          
	        HttpSession sesion = request.getSession();
	        // List<Pizza> carrito;
	        Map<Pizza, Integer> carrito; 
	        carrito = (Map<Pizza, Integer>) sesion.getAttribute("carrito");
	        
	        List<Pizza> listClave = new LinkedList<Pizza>();
			List<Integer> listValor = new LinkedList<Integer>();
			Iterator it = carrito.entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry<Pizza, Integer> e = (Map.Entry)it.next();
			    Pizza clave = e.getKey();
			    Integer valor = e.getValue();
			    listClave.add(clave);
			    listValor.add(valor);
			}
	        
			List<Pizza> pedidoreal = null;
			 for (Pizza p: listClave){
	            	for (Integer i : listValor){
	            	   for(int j = 0; j <= i.intValue(); j = j + 1){
	            		pedidoreal.add(p);
	            		}
	                }
	            }
			 
			 
			 modelo.put("pedidos", pedidoreal);
			 return new ModelAndView("pedidos", modelo);		 
			 // sesion.removeAttribute("carrito");
			 /*
			 Pedido elpedido = new Pedido();
			 elpedido.setSolicitante("Manuel");
			 
			 elpedido.setListaPizzas(pedidoreal);
		        elpedido = (Pedido) sesion.getAttribute("elpedido");
		        
		        sesion.setAttribute("elpedido", elpedido);
			 
			 return new ModelAndView("redirect:/home");
			 */
			 }
			
		  
			 
			

		
			
}
