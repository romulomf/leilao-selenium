package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeiloesListagemPage;

public class LoginPage {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	private WebDriver browser;

	public LoginPage() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		browser = new ChromeDriver();
	}

	public void fechar() {
		browser.quit();
	}

	public void preencherFormulario(String usuario, String senha) {
		browser.navigate().to(URL_LOGIN);
		browser.findElement(By.id("username")).sendKeys(usuario);
		browser.findElement(By.id("password")).sendKeys(senha);
	}

	public LeiloesListagemPage processarFormulario() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesListagemPage(browser);
	}

	public boolean isUrlLogin() {
		return URL_LOGIN.equals(browser.getCurrentUrl());
	}

	public boolean isUrlAcessoNaoAutorizado() {
		return URL_LOGIN.concat("?error").equals(browser.getCurrentUrl());
	}

	public String getNomeUsuarioLogado() {
		try {
			WebElement elemento = browser.findElement(By.id("usuario-logado"));
			return elemento.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public void navegarParaPaginaRestrita() {
		browser.navigate().to("http://localhost:8080/leiloes/1");
	}

	public boolean contem(String conteudo) {
		return browser.getPageSource().contains(conteudo);
	}
}