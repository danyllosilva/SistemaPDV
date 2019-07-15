package sistema.vendas.server.beans.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import sistema.vendas.server.beans.categoriaproduto.CategoriaProduto;

@Entity(name=Produto.NAME)
@Table(name="produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "sistema_vendas_Produto";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo_produto_id")
	private Integer codigoProdutoId;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="unidade_medida")
	private String unidadeMedida;
	
	@Column(name="preco")
	private BigDecimal preco;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name="categoria_produto_id", referencedColumnName="categoria_produto_id")
	private CategoriaProduto categoriaProduto;
	
	@Transient
	private Double precoTotal;

	public Double getPrecoTotal() {
		return (this.quantidade*this.preco.doubleValue());
	}
	
	public Integer getCodigoProdutoId() {
		return codigoProdutoId;
	}

	public void setCodigoProdutoId(Integer codigoProdutoId) {
		this.codigoProdutoId = codigoProdutoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	
}
