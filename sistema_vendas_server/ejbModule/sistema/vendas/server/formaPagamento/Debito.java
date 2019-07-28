package sistema.vendas.server.formaPagamento;

public class Debito implements FormaPagamento {

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>0) {
			return null;
		}else {
			Double total = new Double(totalCompra);
			return total;
		}
	}

}
