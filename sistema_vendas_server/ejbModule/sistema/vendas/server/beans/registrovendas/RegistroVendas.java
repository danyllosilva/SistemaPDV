package sistema.vendas.server.beans.registrovendas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sistema.vendas.server.beans.comprador.Comprador;
import sistema.vendas.server.beans.formapagamento.FormaPagamento;

@Entity(name=RegistroVendas.NAME)
@Table(name="RegistroVendas")
public class RegistroVendas {
	public static final String NAME = "sistema_vendas_RegistroVendas";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="registro_vendas_id")
	private Integer registroVendasId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_venda")
	private Date dataVenda;
	
	@ManyToOne
	@JoinColumn(name="comprador_id", referencedColumnName="comprador_id")
	private Comprador comprador;
	
	//@ManyToOne
	//@JoinColumn(name="forma_pagamento_id", referencedColumnName="forma_pagamento_id")
	//private FormaPagamento formaPagamento;
	private Integer formaPagamentoId;

	public Integer getRegistroVendasId() {
		return registroVendasId;
	}

	public void setRegistroVendasId(Integer registroVendasId) {
		this.registroVendasId = registroVendasId;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Integer getFormaPagamentoId() {
		return formaPagamentoId;
	}

	public void setFormaPagamentoId(Integer formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
	}

	
	
}
