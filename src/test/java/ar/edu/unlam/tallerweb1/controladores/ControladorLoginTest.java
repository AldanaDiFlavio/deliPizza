package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;

public class ControladorLoginTest {

	@Inject
	private ServicioLogin servicioLoginFake;

	@Test
	public void TestQueValidaElLoginDelAdministrador() {
		/* Preparacion */
		Usuario usuarioFake = mock(Usuario.class);

		servicioLoginFake = mock(ServicioLogin.class);
		HttpServletRequest requestFake = mock(HttpServletRequest.class);

		HttpSession sessionFake = mock(HttpSession.class);
		ControladorLogin controladorLogin = new ControladorLogin();

		/* Ejecucion */
		when(servicioLoginFake.consultarUsuario(usuarioFake)).thenReturn(usuarioFake);
		when(requestFake.getSession()).thenReturn(sessionFake);

		controladorLogin.setServicioLogin(servicioLoginFake);

		/* Validacion */
		ModelAndView mv = controladorLogin.validarLogin(usuarioFake, requestFake);

		assertThat(mv.getViewName()).isEqualTo("redirect:/administrar");
	}

	@Test
	public void testAlPasarUsuarioYPassInvalidoDeberiaLlevarALogin() {

		ControladorLogin controladorLogin = new ControladorLogin();

		Usuario usuarioFake = mock(Usuario.class);

		ServicioLogin servicioLoginFake = mock(ServicioLogin.class);
		HttpServletRequest requestFake = mock(HttpServletRequest.class);
		HttpSession sessionFake = mock(HttpSession.class);

		/* Ejecucion */
		when(servicioLoginFake.consultarUsuario(usuarioFake)).thenReturn(null);
		when(requestFake.getSession()).thenReturn(sessionFake);
		controladorLogin.setServicioLogin(servicioLoginFake);
		ModelAndView mav = controladorLogin.validarLogin(usuarioFake, requestFake);

		/* Validacion */
		assertThat(mav.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
	}
}
