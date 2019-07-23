package sistema.vendas.server.beans.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
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
	
	/****
	 * 	INSERT INTO public.produto(codigo_produto_id,nome,preco,quantidade,
	 * unidade_medida,categoria_produto_id) VALUES (1,'vehicula',3,137,'Quilograma ',1),
	 * (2,'risus.',31,307,'Quilograma ',6),(3,'et,',182,201,'Quilograma ',5),
	 * (4,'ligula.',19,378,'Quilograma ',5),(5,'a',101,280,'Quilograma ',2),
	 * (6,'placerat.',49,245,'Quilograma ',4),(7,'Integer',17,170,' Unidade',5),
	 * (8,'elit.',181,142,'Quilograma ',5),(9,'tempus',142,62,'Quilograma ',5),
	 * (10,'natoque',101,65,' Unidade',3),(11,'diam.',185,333,' Unidade',5),
	 * (12,'a',130,55,' Unidade',6),(13,'luctus',175,74,'Quilograma ',5),
	 * (14,'tincidunt',15,343,'Quilograma ',3),(15,'nonummy.',45,156,'Quilograma ',4),
	 * (16,'magna',22,140,'Quilograma ',1),(17,'est',77,91,' Unidade',3),
	 * (18,'arcu',115,112,'Quilograma ',4),(19,'augue.',183,134,'Quilograma ',3),
	 * (20,'in',200,191,' Unidade',6),(21,'mi',48,179,'Quilograma ',3),
	 * (22,'orci.',143,369,'Quilograma ',1),(23,'neque',122,56,' Unidade',2),
	 * (24,'ridiculus',190,384,' Unidade',2),(25,'erat',58,160,' Unidade',4),
	 * (26,'volutpat',77,324,' Unidade',5),(27,'neque.',98,121,'Quilograma ',2),
	 * (28,'sit',12,150,' Unidade',2),(29,'pede.',15,266,'Quilograma ',2),(30,'ac',11,118,'Quilograma ',5);
	 * 
	 * ***/
	
}
