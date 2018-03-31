package br.com.vedamac.teste;

import java.sql.SQLException;

import br.com.vedamac.model.ConexaoFabrica;
import br.com.vedamac.model.ProdutoDAO;

public class Teste {

	public static void main(String[] args) {
		ConexaoFabrica conexao = new ConexaoFabrica("localhost", "5432", "nfefacil", "postgres", "iza123");
		conexao.configUser("postgres", "iza123");
		conexao.configLocal("nfefacil");

		conexao.conect();
		ProdutoDAO pdao =  new ProdutoDAO();
		try {
			System.out.println(pdao.geraCodigo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
