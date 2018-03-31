
package br.com.vedamac.controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.vedamac.model.Produto;
import br.com.vedamac.model.ProdutoDAO;
import br.com.vedamac.model.Unidade;

/**
 *
 * @author DarlanFalcao
 */
@ManagedBean(name="estoqueBean")
@ViewScoped
public class EstoqueBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Unidade> unidades = new ArrayList<Unidade>();
	private String idUnidade;
	private boolean isEdicao;
	private int limEstoque = 10;
	private Produto produtoSelecionado = new Produto();
	private ListDataModel<Produto> produtosSelecionados = new ListDataModel<Produto>();


	
	public EstoqueBean() {

		limEstoque = 5;
		ProdutoDAO pDao = new ProdutoDAO();
		try {
			produtos = pDao.listarProdutos();
			unidades = pDao.retornaListaUnidade();
			produtosSelecionados = new ListDataModel<Produto>(pDao.listarProdutos());
			for(Produto p : produtosSelecionados) {
				System.out.println(p.getFornecedor());
				//System.out.println(p.getFornecedor().length());
			}
		} catch (SQLException ex) {
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	public static void main(String[] args) {
		EstoqueBean est = new EstoqueBean();
	}

	public void carregarPagina(ComponentSystemEvent event) {

		ProdutoDAO pDao = new ProdutoDAO();
		produto = new Produto();
		try {
			unidades = pDao.retornaListaUnidade();
			produtos = pDao.listarProdutos();
		} catch (SQLException ex) {
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		produto.setCodigo(obterSequence());
		produto.setNomeReduzido("Teste");

	}

	public void carregarPagListarProduto(PreRenderViewEvent event) {
		// limEstoque = 5;
		// ProdutoDAO pDao = new ProdutoDAO();
		// produto = new Produto();
		// produto.setDescricao("Teste");
		// produtos.add(produto);

		// unidades = pDao.retornaListaUnidade();
		// produtosSelecionados = new ListDataModel<Produto>(pDao.listarProdutos());
		//
	}

	public void editarProduto(ActionEvent event) {
		isEdicao = true;
		produto = produtosSelecionados.getRowData();
		idUnidade = produto.getUnidade();

	}

	public void prepararExclusao(ActionEvent event) {

		produto = produtosSelecionados.getRowData();

	}

	public void excluirProduto(ActionEvent event) {

		ProdutoDAO pdao = new ProdutoDAO();
		try {
			pdao.excluirProduto(produto.getCodigo());
			produtosSelecionados = new ListDataModel<Produto>(pdao.listarProdutos());

		} catch (SQLException ex) {
			Messages.addGlobalError("Erro ao Excluir" + ex.getMessage());
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public String cadastrarProduto() {
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			produtos = pdao.listarProdutos();
			produto = new Produto();
			produto.setCodigo(obterSequence());
			unidades = pdao.retornaListaUnidade();
			produtosSelecionados = new ListDataModel<Produto>(pdao.listarProdutos());

		} catch (SQLException ex) {
			Messages.addGlobalError("Erro ao Carregar" + ex.getMessage());
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "cadastroProduto";
	}

	public String listarProduto() {
		return "listarProdutos";
	}

	public String imprimirRelatorio() {
		return "index";
	}
   public void unidadeChange() {
	System.out.println("VALUE: "+produto.getUnidade());
	setIdUnidade(produto.getUnidade());
  }
	public String salvarEdicao() {

		ProdutoDAO pdao = new ProdutoDAO();
		//produto = new Produto();
		System.out.println("FORNECEDOR BEAN: " + produto.getFornecedor());
		try {
			setIdUnidade(produto.getUnidade());
			pdao.editarProduto(produto);
			Messages.addGlobalInfo("Produto Salvo com sucesso!");
		} catch (SQLException ex) {
			Messages.addGlobalError(ex.getLocalizedMessage());
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "listarProdutos";

	}

	public String salvarProduto() {
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			pdao.inserirProduto(produto);
			Messages.addGlobalInfo("Produto Cadastrado com Sucesso!", "Sucesso!");
			produto = new Produto();
			produto.setCodigo(obterSequence());

		} catch (SQLException ex) {
			Messages.addGlobalError(ex.getMessage());
			Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "cadastroProduto";

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean isEdicao() {
		return isEdicao;
	}

	public void setEdicao(boolean isEdicao) {
		this.isEdicao = isEdicao;
	}

	@Override
	public String toString() {
		System.out.println(getClass().getSimpleName());
		return String.format("%s[id=%s]", getClass().getSimpleName(), getIdUnidade());
	}
	

	public int obterSequence() {
		int id = 0;
		for (Produto p : produtos) {
			if (id < p.getCodigo()) {
				id = p.getCodigo();
			}
		}

		return id + 1;

	}

	public boolean isIsEdicao() {
		return isEdicao;
	}

	public int getLimEstoque() {
		return limEstoque;
	}

	public void setIsEdicao(boolean isEdicao) {
		this.isEdicao = isEdicao;
	}

	public void setLimEstoque(int limEstoque) {
		this.limEstoque = limEstoque;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public ListDataModel<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void setProdutosSelecionados(ListDataModel<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}

}
