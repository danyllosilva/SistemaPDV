package sistema.vendas.server.beans.categoriaproduto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;	

@Entity(name=CategoriaProduto.NAME)
@Table(name="categoriaProduto")
public class CategoriaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "sistema_vendas_CategoriaProduto";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="categoria_produto_id")
	private Integer categoriaProdutoId;
	
	
	@Column(name="nome")
	private String nome;


	public Integer getCategoriaProdutoId() {
		return categoriaProdutoId;
	}


	public void setCategoriaProdutoId(Integer categoriaProdutoId) {
		this.categoriaProdutoId = categoriaProdutoId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/*****
	 * 
	 * INSERT INTO public.categoriaproduto(categoria_produto_id,nome) VALUES 
	 * (1,' Higiene '),(2,' Beleza   '),(3,' Limpeza '),(4,' Bebidas '), 
	 * (5,' Industrializados '), (6,'Alimentos ');
	 * 
	 * ******/
	
}
