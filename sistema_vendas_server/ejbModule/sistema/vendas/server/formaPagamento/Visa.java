package sistema.vendas.server.formaPagamento;

public class Visa implements FormaPagamento {
	//Visa 8 2%

	
	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>8) {
			return null;
		}else {
			float valorReduzir = (float) totalCompra * ((float) 2/100);
			totalCompra = totalCompra - valorReduzir;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>8) {
			return false;
		}else {
			return true;
		}
	}
}
