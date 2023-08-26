package br.com.alura.leilao.leiloes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.leilao.login.LoginPage;

@TestInstance(Lifecycle.PER_CLASS)
class LeiloesTest {

	private LeiloesListagemPage paginaListagem;

	private LeiloesCadastroPage paginaCadastro;

	public LeiloesTest() {
		// construtor padrão
	}

	@BeforeEach
	void preparar() {
		LoginPage paginaLogin = new LoginPage();
		paginaLogin.preencherFormulario("fulano", "pass");
		paginaListagem = paginaLogin.processarFormulario();
	}

	@AfterEach
	void encerrar() {
		paginaListagem.fechar();
	}

	@Test
	void deveriaCadastrarLeilao() {
		String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilão do dia ".concat(data);
		String valor = "500.00";
		
		paginaCadastro = paginaListagem.navegarParaCadastro();
		paginaCadastro.cadastrar(data, nome, valor);

		LeiloesPage pagina = paginaCadastro.processar();

		assertTrue(pagina instanceof LeiloesListagemPage);
		
		LeiloesListagemPage paginaListagem = (LeiloesListagemPage) pagina;
		
		assertTrue(paginaListagem.isLeilaoCadastrado(data, nome, valor));
	}

	@Test
	void deveriaValidarCadastroLeilao() {
		paginaCadastro = paginaListagem.navegarParaCadastro();
		paginaCadastro.cadastrar("", "", "");

		LeiloesPage pagina = paginaCadastro.processar();
		
		assertTrue(pagina instanceof LeiloesListagemPage);
		
		LeiloesListagemPage paginaCadastro = (LeiloesListagemPage) pagina;
		assertTrue(paginaCadastro.isMensagensValidacaoVisiveis());
	}
}