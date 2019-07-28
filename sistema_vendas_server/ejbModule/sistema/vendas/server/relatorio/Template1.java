package sistema.vendas.server.relatorio;

import java.util.Date;

public class Template1 {
	private Integer idRegistroVenda;
	private Date dataCompra;
	private String produtoNome;
	private Integer quantidade;
	private Double valorProduto;
	private Double valorFinalCompra;
	private Double valorFinalCompraLoja;
	
	
	public Integer getIdRegistroVenda() {
		return idRegistroVenda;
	}
	public void setIdRegistroVenda(Integer idRegistroVenda) {
		this.idRegistroVenda = idRegistroVenda;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getProdutoNome() {
		return produtoNome;
	}
	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}
	public Double getValorFinalCompra() {
		return valorFinalCompra;
	}
	public void setValorFinalCompra(Double valorFinalCompra) {
		this.valorFinalCompra = valorFinalCompra;
	}
	public Double getValorFinalCompraLoja() {
		return valorFinalCompraLoja;
	}
	public void setValorFinalCompraLoja(Double valorFinalCompraLoja) {
		this.valorFinalCompraLoja = valorFinalCompraLoja;
	}
	
}
/***

 O relatório informa o id da compra, 
 o data, os produtos que foram comprados com suas respectivas quantidades,
  o valor de cada produto, a forma de pagamento 
  e o valor final da compra para o cliente e o valor final para a loja (com a aplicação da taxa).

****/