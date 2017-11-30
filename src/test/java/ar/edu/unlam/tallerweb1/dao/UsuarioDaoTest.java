package ar.edu.unlam.tallerweb1.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class UsuarioDaoTest extends SpringTest {

	@Inject
	private UsuarioDao usuDao;

	@Test
	@Transactional
	@Rollback(false)
	public void TestQueVerificaSiExisteUnUsuarioDeberiaDevolverEseUsuario() {

		/* Preparacion */
		Usuario usuario = new Usuario();
		usuario.setId(28l);
		usuario.setEmail("clara@gmail.com");
		usuario.setPassword("clara");
		usuDao.guardarUsuario(usuario);

		/* Ejecucion */
		Usuario resultado = usuDao.consultarUsuario(usuario);

		// verificacion
		assertThat(resultado.getEmail()).isEqualTo(usuario.getEmail());

	}

}
