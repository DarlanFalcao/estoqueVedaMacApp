package br.com.vedamac.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import br.com.vedamac.model.ConexaoFabrica;

public class Programa {

	public Programa(){
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		ConexaoFabrica c = new ConexaoFabrica("localhost", "5432", "nfefacil", "postgres", "iza123");
		c.configUser("postgres", "iza123");
		c.configLocal("nfefacil");
		
		c.conect();
		String sql = "SELECT  nome_reduzido FROM produto ";
		

		ResultSet rs = c.query(sql);
		while(rs.next()){
			String codigo = rs.getString(1);
		System.out.println("C�digo: "+codigo);
		}
	}
	
}
