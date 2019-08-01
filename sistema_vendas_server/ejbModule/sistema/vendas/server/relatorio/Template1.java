package sistema.vendas.server.relatorio;

import java.math.BigDecimal;
import java.util.Date;

public class Template1 {
	private Integer registroVendasId;//
	private Date dataCompra;
	private String nomeProduto; //
	private Integer quantidade; //
	private BigDecimal valorTotal; //
	private Double valorFinalCompra;
	private Double valorFinalCompraLoja;
	private Integer formaPagamentoId;
	private String formaPagamentoNome;
	


	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
 
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
	public Integer getRegistroVendasId() {
		return registroVendasId;
	}
	public void setRegistroVendasId(Integer registroVendasId) {
		this.registroVendasId = registroVendasId;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getFormaPagamentoId() {
		return formaPagamentoId;
	}
	public void setFormaPagamentoId(Integer formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
		
		this.formaPagamentoNome = " ";
		
		if(this.formaPagamentoId == 0) {
			this.formaPagamentoNome = "Dinheiro";
		}
		
		if(this.formaPagamentoId == 1) {
			this.formaPagamentoNome = "Débito";
		}
		
		if(this.formaPagamentoId == 2) {
			this.formaPagamentoNome = "Ticket";
		}
		
		if(this.formaPagamentoId == 3) {
			this.formaPagamentoNome = "Mastercard";
		}
		
		if(this.formaPagamentoId == 4) {
			this.formaPagamentoNome = "Visa";
		}
		
		if(this.formaPagamentoId == 5) {
			this.formaPagamentoNome = "Elo";
		}
		
		if(this.formaPagamentoId == 6) {
			this.formaPagamentoNome = "American Express";
		}
		
		if(this.formaPagamentoId == 7) {
			this.formaPagamentoNome = "Credicard";
		}


	}
	
	public String getFormaPagamentoNome() {
		return formaPagamentoNome;
	}
	public void setFormaPagamentoNome(String formaPagamentoNome) {
		this.formaPagamentoNome = formaPagamentoNome;
	}
	 
	
	
	
}
/***

 O relatório informa o id da compra, 
 o data, os produtos que foram comprados com suas respectivas quantidades,
  o valor de cada produto, a forma de pagamento 
  e o valor final da compra para o cliente e o valor final para a loja (com a aplicação da taxa).

****/