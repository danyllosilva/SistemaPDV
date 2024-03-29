package sistema.vendas.server.beans.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sistema.vendas.server.beans.tipoUsuario.TipoUsuario;

@Entity(name=Usuario.NAME)
@Table(name="usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String NAME ="sistema_vendas_Usuario";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usuario_id")
	private Integer usuarioId;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;

	@Column(name="tipo_usuario")
	private Integer tipoUsario;
	
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoUsario() {
		return tipoUsario;
	}

	public void setTipoUsario(Integer tipoUsario) {
		this.tipoUsario = tipoUsario;
	}
	
	
	
}
