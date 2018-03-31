package br.com.vedamac.controler;

import javax.faces.bean.ManagedBean;

import br.com.vedamac.model.Produto;

@ManagedBean
public class ProdutoBean {
	
	private Produto produto;

	public ProdutoBean(){
		
		
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
}
