package sistema.vendas.server.formaPagamento;

public class AmericanExpress implements FormaPagamento{
	//American Express 10 0.5%


	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>10) {
			return null;
		}else {
			float valorReduzir = (float) totalCompra * ((float) 0.5/100);
			totalCompra = totalCompra - valorReduzir;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>10) {
			return false;
		}else {
			return true;
		}
	}
	
	

	 

}
