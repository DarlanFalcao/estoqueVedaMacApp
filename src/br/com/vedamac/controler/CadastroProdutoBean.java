package br.com.vedamac.controler;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CadastroProdutoBean {
	private String testeParam;

	public CadastroProdutoBean() {
		// TODO Auto-generated constructor stub
	}
	public String teste() {
		return "TESTE";
	}
	public String getTesteParam() {
		return testeParam;
	}
	public void setTesteParam(String testeParam) {
		this.testeParam = testeParam;
	}
	
	
	
}
