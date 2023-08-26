package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public abstract class LeiloesPage {

	protected static final String URL_LEILOES_LISTAGEM = "http://localhost:8080/leiloes";

	protected static final String URL_LEILOES_CADASTRO = "http://localhost:8080/leiloes/new";

	private WebDriver browser;

	public LeiloesPage(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = browser;
	}

	protected final WebDriver navegador() {
		return browser;
	}

	public final void fechar() {
		browser.quit();
	}

	public abstract boolean isPaginaAtual();

	public abstract LeiloesPage navegarParaListagem();

	public abstract LeiloesPage navegarParaCadastro();
}