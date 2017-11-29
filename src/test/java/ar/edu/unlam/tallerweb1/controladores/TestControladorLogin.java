package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import static org.mockito.Mockito.*;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class TestControladorLogin extends SpringTest {

	@Inject
	private ServicioLogin servicioLoginFake;

	@Test
	public void testAlPasarUsuarioYPassInvalidoDeberiaLlevarALoginComentado() {

		/* Preparacion */ // Exista en la session HTTP un ROL
		ControladorLogin cont = new ControladorLogin();
		// Dependecias del SUT? / Por dependencias de un objeto controlado
		// Mockin Tecnica falcificar dependencias / pruebas unitarias en objetos
		// con dependencias

		// Creacion de falso usuario

		Usuario usuarioFake = mock(Usuario.class);
		Long id = (long) 1;
		usuarioFake.setId(id);
		usuarioFake.setEmail("matias@gmail.com");
		usuarioFake.setPassword("1234");
		usuarioFake.setRol("Administrador");

		// Creacion de falsa session
		servicioLoginFake = mock(ServicioLogin.class);

		HttpServletRequest requestFake = mock(HttpServletRequest.class);

		/* Ejecucion */
		ControladorLogin Controladorlogin = new ControladorLogin();
		// when(servicioLoginFake.consultarUsuario(usuarioFake)).thenReturn(usuarioFake);

		ModelAndView mav = cont.validarLogin(usuarioFake, requestFake);

		// servicio.validar()
		/* Validacion */
		assertThat(mav.getView()).isEqualTo("Login");
		assertThat(mav.getModel().get("error")).isEqualTo("usuario-invalido");
		// dependencias complejas como hacer

	} // Manipulo entrada ejecuto valida salida procedimiento de un test
		// Paso la entrada reviso la salida
		// Meterme en el codigo y saber sin usar el valor de retorno
		// Espias FAKE Objet
		// Tragamonedas prodria decir verify(t1,times(1).girar();) // Si no
		// ocurre
		// da rojo es un Assert de algo interno
		// Tragamonedas prodria decir verify(t1,never().girar();)

	// No se puede pedir verify a algo que no es un MOCK
	// Test de caja trasnparente porque se el como

	// SUT sujeto de prueba que pruebo EL TRAGAMONEDAS
	// No tiene sentido MOCKEAR EL SUT
	// Mokear -
	// Depen
	// Mockear todas las dependencias

	// Si lo que pruebo tiene dependencias usar mockito
	// Leer documentacion moquito

	@Test
	public void testAlPasarUsuarioYPassInvalidoDeberiaLlevarALogin() {

		ControladorLogin cont = new ControladorLogin();

		Usuario usuarioFake = mock(Usuario.class);
		Long id = (long) 1;
		usuarioFake.setId(id);
		usuarioFake.setEmail("matias@gmail.com");
		usuarioFake.setPassword("1234");
		usuarioFake.setRol("Administrador");

		HttpServletRequest requestFake = mock(HttpServletRequest.class);
		HttpSession sessionFake = mock(HttpSession.class);
		sessionFake.setAttribute("ROL", usuarioFake.getRol());

		/* Ejecucion */
		when(servicioLoginFake.consultarUsuario(usuarioFake)).thenReturn(usuarioFake);
		when(requestFake.getSession()).thenReturn(sessionFake);

		ModelAndView mav = cont.validarLogin(usuarioFake, requestFake);

		/* Validacion */

		// Tragamonedas prodria decir verify(t1,times(1).girar();) // Si no
		// ocurre da rojo es un Assert de algo interno
		// Tragamonedas prodria decir verify(t1,never().girar();)

		assertThat(mav.getView()).isEqualTo("login");
	}

	/*
	 * @Test public void validarLoginUsuarioBien() { Usuario usuarioFake =
	 * mock(Usuario.class); servicioLoginFake = mock(ServicioLogin.class);
	 * HttpServletRequest requestFake = mock(HttpServletRequest.class);
	 * 
	 * HttpSession sessionFake = mock(HttpSession.class); ControladorLogin
	 * loginControllerFake = new ControladorLogin();
	 * 
	 * when(servicioLoginFake.consultarUsuario(usuarioFake)).thenReturn(
	 * usuarioFake); when(requestFake.getSession()).thenReturn(sessionFake);
	 * 
	 * loginControllerFake.setServicioLogin(servicioLoginFake); ModelAndView mv
	 * = loginControllerFake.validarLogin(usuarioFake, requestFake);
	 * 
	 * assertThat(mv.getViewName()).isEqualTo("login"); }
	 * 
	 * @Test public void validarLoginUsuario() {
	 * 
	 * HttpServletRequest requestFake = mock(HttpServletRequest.class);
	 * 
	 * HttpSession sessionFake = mock(HttpSession.class);
	 * 
	 * Usuario usuarioFake = mock(Usuario.class);
	 * 
	 * servicioLoginFake = mock(ServicioLogin.class);
	 * 
	 * when(servicioLoginFake.consultarUsuario(null)).thenReturn(null);
	 * 
	 * ControladorLogin loginControllerFake = new ControladorLogin();
	 * 
	 * when(requestFake.getSession()).thenReturn(sessionFake);
	 * 
	 * loginControllerFake.setServicioLogin(servicioLoginFake);
	 * 
	 * ModelAndView mv = loginControllerFake.validarLogin(usuarioFake,
	 * requestFake);
	 * 
	 * assertThat(mv.getViewName()).isEqualTo("login"); }
	 */
}