package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesListagemPage extends LeiloesPage {

	public LeiloesListagemPage(WebDriver browser) {
		super(browser);
	}

	@Override
	public LeiloesListagemPage navegarParaListagem() {
		navegador().navigate().to(URL_LEILOES_LISTAGEM);
		return this;
	}

	@Override
	public LeiloesCadastroPage navegarParaCadastro() {
		navegador().navigate().to(URL_LEILOES_CADASTRO);
		return new LeiloesCadastroPage(navegador());
	}

	public boolean isLeilaoCadastrado(String data, String nome, String valor) {
		WebElement linha = navegador().findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linha.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linha.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linha.findElement(By.cssSelector("td:nth-child(3)"));
		
		return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(data) && colunaValorInicial.getText().equals(valor);
	}

	@Override
	public boolean isPaginaAtual() {
		return navegador().getCurrentUrl().equals(URL_LEILOES_LISTAGEM);
	}

	public boolean isMensagensValidacaoVisiveis() {
		boolean mensagensNome = navegador().getPageSource().contains("minimo 3 caracteres") && navegador().getPageSource().contains("não deve estar em branco");
		boolean mensagemValor = navegador().getPageSource().contains("deve ser um valor maior de 0.1");
		boolean mensagemData = navegador().getPageSource().contains("deve ser uma data no formato dd/MM/yyyy");
		return mensagensNome && mensagemValor && mensagemData;
	}
}