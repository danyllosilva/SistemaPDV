package sistema.vendas.server.beans.cesta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import sistema.vendas.server.beans.produto.Produto;
import sistema.vendas.server.beans.registrovendas.RegistroVendas;

@Entity(name=Cesta.NAME)
@Table(name="cesta")
public class Cesta implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "sistema_vendas_Cesta";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cesta_id")
	private Integer cestaId;
	
	//@ManyToOne
	//@JoinColumn(name="codigo_produto_id", referencedColumnName="codigo_produto_id")
	private Integer produtoId;
	
	@Transient
	private Produto produto;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name="registro_vendas_id", referencedColumnName="registro_vendas_id")
	private RegistroVendas registroVendas;
	
	@Transient
	private Double valorTotal;

	
	public Double getValorTotal() {
		return (this.quantidade*this.produto.getPreco().doubleValue());
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getCestaId() {
		return cestaId;
	}

	public void setCestaId(Integer cestaId) {
		this.cestaId = cestaId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public RegistroVendas getRegistroVendas() {
		return registroVendas;
	}

	public void setRegistroVendas(RegistroVendas registroVendas) {
		this.registroVendas = registroVendas;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	
	
	
	
}
