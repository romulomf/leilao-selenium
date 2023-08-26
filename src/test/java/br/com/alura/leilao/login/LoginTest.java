package br.com.alura.leilao.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class LoginTest {

	private LoginPage pagina;

	public LoginTest() {
		// construtor padrão
	}

	@BeforeEach
	void preparar() {
		pagina = new LoginPage();
	}

	@AfterEach
	void encerrar() {
		pagina.fechar();
	}

	@Test
	void deveriaEfetuarLoginComDadosValidos() {
		pagina.preencherFormulario("fulano", "pass");
		pagina.processarFormulario();

		assertFalse(pagina.isUrlLogin());
		assertEquals("fulano", pagina.getNomeUsuarioLogado());
	}

	@Test
	void naoDeveriaLogarComDadosInvalidos() {
		pagina.preencherFormulario("fulano", "123456");
		pagina.processarFormulario();
		
		assertTrue(pagina.isUrlAcessoNaoAutorizado());
		assertNull(pagina.getNomeUsuarioLogado());
		assertTrue(pagina.contem("Usuário e senha inválidos."));
	}

	@Test
	void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		pagina.navegarParaPaginaRestrita();

		assertTrue(pagina.isUrlLogin());
		assertFalse(pagina.contem("Dados do Leião"));
	}
}