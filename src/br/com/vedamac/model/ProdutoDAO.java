package br.com.vedamac.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private Produto produto;
	private ConexaoFabrica conexao;
	private List<Unidade> listaUnidade;

	public ProdutoDAO() {
		conexao = new ConexaoFabrica("localhost", "5432", "nfefacil", "postgres", "iza123");
		conexao.configUser("postgres", "iza123");
		conexao.configLocal("nfefacil");

		conexao.conect();

	}

	public void inserirProduto(Produto produto) throws SQLException {

		produto.setGarantia(produto.getGarantia().replaceAll(",", "."));
		produto.setPrecoCompra(produto.getPrecoCompra().replaceAll(",", "."));
		produto.setPreco(produto.getPreco().replaceAll(",", "."));
		produto.setPeso(produto.getPeso().replaceAll(",", "."));
		produto.setIcms_perc(produto.getIcms_perc().replaceAll(",", "."));
		produto.setIcms_pred(produto.getIcms_pred().replaceAll(",", "."));
		produto.setIpi_perc(produto.getIpi_perc().replaceAll(",", "."));
		produto.setPis_perc(produto.getPis_perc().replaceAll(",", "."));
		produto.setConfins_perc(produto.getConfins_perc().replaceAll(",", "."));

		
		String sql = "insert into produto ( " + "codigo, " + "codigo_fornec, " + "fornecedor, " + "descricao, "
				+ "unidade, " + "nome_reduzido, " + "seg_name, " + "marca, " + "garantia, " + "ean, " + "ncm, "
				+ "cest, " + "origem, " + "preco_compra, " + "preco, " + "peso, " + "icms_cst, " + "icms_perc, "
				+ "icms_pred, " + "ipi_cst, " + "ipi_perc, " + "pis_cst, " + "pis_perc, " + "cofins_cst, "
				+ "cofins_perc, " + "images " + ")" + " values (" + produto.getCodigo() + ", " + "'"
				+ produto.getCodigoForn() + "', " + "'" + produto.getFornecedor() + "', " + "'" + produto.getDescricao()
				+ "', " + "'" + produto.getUnidade() + "', " + "'" + produto.getNomeReduzido() + "', " + "'"
				+ produto.getSegNome() + "', " + "'" + produto.getMarca() + "', " + "" + produto.getGarantia() + ", "
				+ "'" + produto.getEan() + "', " + "'" + produto.getNcm() + "', " + "'" + produto.getCest() + "', "
				+ "'" + produto.getOrigem() + "', " + "" + produto.getPrecoCompra() + ", " + "" + produto.getPreco()
				+ ", " + "" + produto.getPeso() + ", " + "'" + produto.getIcms_cst() + "', " + ""
				+ produto.getIcms_perc() + ", " + "" + produto.getIcms_pred() + ", " + "'" + produto.getIpi_cst()
				+ "', " + "" + produto.getIpi_perc() + ", " + "'" + produto.getPis_cst() + "', " + ""
				+ produto.getPis_perc() + ", " + "'" + produto.getConfins_cst() + "', " + "" + produto.getConfins_perc()
				+ ", " + "'" + produto.getImages() + "'"+"," +produto.getQtde()+ ")";

		
			conexao.getStatment().executeUpdate(sql);
				// TODO Auto-generated catch block
			
		

	}

	public void editarProduto(Produto produto) throws SQLException {

		produto.setGarantia(produto.getGarantia().replaceAll(",", "."));
		produto.setPrecoCompra(produto.getPrecoCompra().replaceAll(",", "."));
		produto.setPreco(produto.getPreco().replaceAll(",", "."));
		produto.setPeso(produto.getPeso().replaceAll(",", "."));
		produto.setIcms_perc(produto.getIcms_perc().replaceAll(",", "."));
		produto.setIcms_pred(produto.getIcms_pred().replaceAll(",", "."));
		produto.setIpi_perc(produto.getIpi_perc().replaceAll(",", "."));
		produto.setPis_perc(produto.getPis_perc().replaceAll(",", "."));
		produto.setConfins_perc(produto.getConfins_perc().replaceAll(",", "."));
                produto.setImages("''");
                System.out.println("CODFORN: "+produto.getCodigoForn());
                System.out.println("FORNECEDOR: "+produto.getFornecedor());
                System.out.println("UNIDADE: "+produto.getUnidade());

		String sql2 = "update produto set codigo = "+produto.getCodigo()+",codigo_fornec = " + produto.getCodigoForn().trim() + ",fornecedor = '"
				+ produto.getFornecedor() + "',descricao = '" + produto.getDescricao().trim() + "',unidade ='"
				+ produto.getUnidade() + "',nome_reduzido ='" + produto.getNomeReduzido()+ "',seg_name ='"
				+ produto.getSegNome() + "',marca ='" + produto.getMarca()+ "',garantia =  "
				+ produto.getGarantia() + ",ean ='" + produto.getEan() + "',ncm ='" + produto.getNcm() + "',cest ="
				+ produto.getCest() + ",origem =  " + produto.getOrigem() + ",preco_compra = " + produto.getPrecoCompra()
				+ "" + ",preco =" + produto.getPreco() + ",peso =" + produto.getPeso() + ",icms_cst =  "
				+ produto.getIcms_cst() + "" + ",icms_perc =  " + produto.getIcms_perc() + ",icms_pred =  "
				+ produto.getIcms_pred() + "" + ",ipi_cst =  " + produto.getIpi_cst() + ",ipi_perc =  "
				+ produto.getIpi_perc() + ",pis_cst = " + produto.getPis_cst() + ",pis_perc = " + produto.getPis_perc()
				+ ",cofins_cst =  " + produto.getConfins_cst() + ",cofins_perc = " + produto.getConfins_perc()
				+ ",trib_st_perc="+produto.getConfins_perc()+",images =  " + produto.getImages() +",qtde = "+produto.getQtde()+" where codigo = " + produto.getCodigo()+";";
		System.out.println(sql2);
		
			conexao.getStatment().executeUpdate(sql2);
		

	}

	public void deletarProduto() {
	}

	public int geraCodigo() throws SQLException {
		Integer codigo = new Integer(0);
		String sql = "SELECT MAX(codigo) from produto";
		conexao.conect();
		ResultSet rs = conexao.query(sql);
		
			while (rs.next()) {
				codigo = rs.getInt(1) + 1;
				System.out.println("Novo C�digo: " + codigo);
			}
		
		return codigo;
	}

	public List<Unidade> retornaListaUnidade() throws SQLException {
		listaUnidade = new ArrayList<Unidade>();
		listaUnidade.clear();

		String sql = "SELECT * FROM unidade ";
		ResultSet rs = conexao.query(sql);
			while (rs.next()) {
				Unidade unid = new Unidade();
				unid.setIdUnidade(rs.getString(1));
				unid.setUnidade(rs.getString(2));

				listaUnidade.add(unid);

			}
		

		return listaUnidade;
	}

	public List<Produto> listarProdutos() throws SQLException {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		String sql = "SELECT codigo, nome_reduzido, seg_name, unidade, ean, ncm, cest, origem, \r\n"
				+ "       preco_compra, preco, produto.peso, codigo_fornec, fornecedor, marca, \r\n"
				+ "       link, images, source_fat, estoque, garantia, produto.icms_cst, produto.icms_perc, \r\n"
				+ "       produto.icms_pred, produto.ipi_cst, produto.ipi_perc, produto.pis_cst, produto.pis_perc, produto.cofins_cst, \r\n"
				+ "       produto.cofins_perc, produto.trib_st_perc, descnovo, descricao, cnpj_fornecedor, \r\n"
				+ "       qtde,(qtde - count(cod_produto))as estoque\r\n"
				+ "  FROM public.produto,nf_produtos group by codigo;";

		ResultSet rs = conexao.query(sql);
		
			while (rs.next()) {
				Produto prod = new Produto();
				prod.setCodigo(rs.getInt(1));
				prod.setNomeReduzido(rs.getString(2));
				prod.setSegNome(rs.getString(3));
				prod.setUnidade(rs.getString(4));
				prod.setEan(rs.getString(5));
				prod.setNcm(rs.getString(6));
				prod.setCest(rs.getString(7));
				char origem = rs.getString(8).charAt(0);
				prod.setOrigem(origem);
				prod.setPrecoCompra(rs.getString(9));
				prod.setPreco(rs.getString(10));
				prod.setPeso(rs.getString(11));
				prod.setCodigoForn(rs.getString(12));
				prod.setFornecedor(rs.getString(13));
				prod.setMarca(rs.getString(14));
				prod.setLink(rs.getString(15));
				prod.setImages(rs.getString(16));
				prod.setSouceFat(rs.getString(17));
				prod.setEstoque(rs.getInt(18));
				prod.setGarantia(rs.getString(19));
				prod.setIcms_cst(rs.getString(20));
				prod.setIcms_perc(rs.getString(21));
				prod.setIcms_pred(rs.getString(22));
				prod.setIpi_cst(rs.getString(23));
				prod.setIpi_perc(rs.getString(24));
				prod.setPis_cst(rs.getString(25));
				prod.setPis_perc(rs.getString(26));
				prod.setConfins_cst(rs.getString(27));
				prod.setConfins_perc(rs.getString(28));
				prod.setTrib_st_perc(rs.getString(29));
				prod.setDesc_novo(rs.getString(30));
				prod.setDescricao(rs.getString(31));
				prod.setCnpjForn(rs.getString(32));
				prod.setQtde(rs.getInt(33));
                                prod.setEstoque(rs.getInt(34));
				listaProdutos.add(prod);

			}
		
		return listaProdutos;
	}

	public Produto buscarProdutoPorCod(Integer cod) throws SQLException {

		String sql = "SELECT codigo, nome_reduzido, seg_name, unidade, ean, ncm, cest, origem, \r\n"
				+ "       preco_compra, preco, produto.peso, codigo_fornec, fornecedor, marca, \r\n"
				+ "       link, images, source_fat, estoque, garantia, produto.icms_cst, produto.icms_perc, \r\n"
				+ "       produto.icms_pred, produto.ipi_cst, produto.ipi_perc, produto.pis_cst, produto.pis_perc, produto.cofins_cst, \r\n"
				+ "       produto.cofins_perc, produto.trib_st_perc, descnovo, descricao, cnpj_fornecedor, \r\n"
				+ "       qtde,(qtde - count(cod_produto))as estoque\r\n"
				+ "  FROM public.produto,nf_produtos group by codigo;";
		Produto prod = new Produto();

		ResultSet rs = conexao.query(sql);
		
			while (rs.next()) {
				prod.setCodigo(rs.getInt(1));
				prod.setNomeReduzido(rs.getString(2));
				prod.setSegNome(rs.getString(3));
				prod.setUnidade(rs.getString(4));
				prod.setEan(rs.getString(5));
				prod.setNcm(rs.getString(6));
				prod.setNcm(rs.getString(7));
				prod.setCest(rs.getString(8));
				char origem = rs.getString(9).charAt(0);
				prod.setOrigem(origem);
				prod.setPrecoCompra(rs.getString(10));
				prod.setPreco(rs.getString(11));
				prod.setPeso(rs.getString(12));
				prod.setCodigoForn(rs.getString(13));
				prod.setFornecedor(rs.getString(14));
				prod.setMarca(rs.getString(15));
				prod.setLink(rs.getString(16));
				prod.setImages(rs.getString(17));
				prod.setSouceFat(rs.getString(18));
				prod.setGarantia(rs.getString(19));
				prod.setIcms_cst(rs.getString(20));
				prod.setIcms_perc(rs.getString(21));
				prod.setIcms_pred(rs.getString(22));
				prod.setIpi_cst(rs.getString(23));
				prod.setIpi_perc(rs.getString(24));
				prod.setPis_cst(rs.getString(25));
				prod.setPis_perc(rs.getString(26));
				prod.setConfins_cst(rs.getString(27));
				prod.setConfins_perc(rs.getString(28));
				prod.setTrib_st_perc(rs.getString(29));
				prod.setDesc_novo(rs.getString(30));
				prod.setDescricao(rs.getString(31));
				prod.setCnpjForn(rs.getString(32));
				prod.setQtde(rs.getInt(33));
				prod.setEstoque(rs.getInt(34));

			}
		

		return prod;
	}
        public void excluirProduto(Integer codigo) throws SQLException{
            String sql = "delete from produto where codigo = "+codigo+";";
            conexao.getStatment().executeUpdate(sql);
        }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Unidade> getListaUnidade() {
		return listaUnidade;
	}

	public void setListaUnidade(List<Unidade> listaUnidade) {
		this.listaUnidade = listaUnidade;
	}

}
