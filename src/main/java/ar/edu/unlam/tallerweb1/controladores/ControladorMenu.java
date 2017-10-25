package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Pizza;
import ar.edu.unlam.tallerweb1.servicios.ServicioPizza;

@Controller
public class ControladorMenu {
	
	@Inject
	private ServicioPizza servicioPizza; 

		@RequestMapping("/menu")
		public ModelAndView irAHome(){
		
			ModelMap modelo = new ModelMap();
			
			List<Pizza> listaPizzas = servicioPizza.traerTodasLasPizzas();
			
			// int cantidadentotal = listaPizzas.size();
			
			modelo.put("pizzas", listaPizzas);		
					
		return new ModelAndView("menu", modelo);				
	}
}
