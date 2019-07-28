package sistema.vendas.server.formaPagamento;

public class MasterCard implements FormaPagamento {

	// Mastercard 6 1%

	
	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>6) {
			return null;
		}else {
			float valorReduzir = (float) totalCompra *  ((float) 1/100);
			System.out.println(valorReduzir);
			totalCompra = totalCompra - valorReduzir;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>6) {
			return false;
		}else {
			return true;
		}
	}

}
