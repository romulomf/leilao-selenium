package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeiloesCadastroPage extends LeiloesPage {

	public LeiloesCadastroPage(WebDriver browser) {
		super(browser);
	}

	@Override
	public LeiloesListagemPage navegarParaListagem() {
		navegador().navigate().to(URL_LEILOES_LISTAGEM);
		return new LeiloesListagemPage(navegador());
	}

	@Override
	public LeiloesCadastroPage navegarParaCadastro() {
		navegador().navigate().to(URL_LEILOES_CADASTRO);
		return this;
	}

	public void cadastrar(String data, String nome, String valor) {
		navegador().findElement(By.id("dataAbertura")).sendKeys(data);
		navegador().findElement(By.id("nome")).sendKeys(nome);
		navegador().findElement(By.id("valorInicial")).sendKeys(valor);		
	}

	public LeiloesPage processar() {
		navegador().findElement(By.id("button-submit")).submit();
		// caso ainda esteja na mesma página, na página de cadastro, quer dizer que não houve sucesso no cadastro.
		// FIXME o spring não mantém a url quando o formulário é submetido e ele acaba voltando para página de listagem. é preciso ter uma forma de deixar isso mais claro
//		if (isPaginaAtual()) {
//			return new LeiloesCadastroPage(navegador());
//		}
		return new LeiloesListagemPage(navegador());
	}

	@Override
	public boolean isPaginaAtual() {
		return navegador().getCurrentUrl().equals(URL_LEILOES_CADASTRO);
	}
}