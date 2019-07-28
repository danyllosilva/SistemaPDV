package sistema.vendas.server.formaPagamento;

public class Elo implements FormaPagamento {
	// Elo 3 3%

	
	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>3) {
			return null;
		}else {
			float valorReduzir = (float) totalCompra * ((float) 3/100);
			totalCompra = totalCompra - valorReduzir;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>3) {
			return false;
		}else {
			return true;
		}
	}

}
