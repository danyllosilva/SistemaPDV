package sistema.vendas.server.beans.comprador;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = Comprador.NAME)
@Table(name="comprador")
public class Comprador implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME ="sistema_vendas_Comprador";


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comprador_id")
	private Integer compradorId;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="endereco")
	private String endereco;

	
	
	public Integer getCompradorId() {
		return compradorId;
	}

	public void setCompradorId(Integer compradorId) {
		this.compradorId = compradorId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	


}
