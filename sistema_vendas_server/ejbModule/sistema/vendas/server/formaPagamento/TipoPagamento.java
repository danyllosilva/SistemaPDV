package sistema.vendas.server.formaPagamento;

public class TipoPagamento {
	private Integer tipoId;
	
	public enum tipo {
		Dinheiro , 
		Debito  , 
		Ticket ,  
		MasterCard ,  
		Visa ,  
		Elo ,  
		AmericanExpress ,  
		Credicard 
	}

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}
	
	   
}
