package sistema.vendas.server.beans.formapagamento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name=FormaPagamento.NAME)
@Table(name="formaPagamento")
public class FormaPagamento  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String NAME="sistema_vendas_FormaPagamento";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="forma_pagamento_id")
	private Integer formaPagamentoId;
	
	@Column(name="descricao")
	private String descricao;

	public Integer getFormaPagamentoId() {
		return formaPagamentoId;
	}

	public void setFormaPagamentoId(Integer formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
}
