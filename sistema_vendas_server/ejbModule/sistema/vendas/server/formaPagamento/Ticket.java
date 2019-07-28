package sistema.vendas.server.formaPagamento;

public class Ticket implements FormaPagamento {

	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>0) {
			return null;
		}else {
			float valorAumentar = (float) totalCompra * ((float) 0.5/100);
			totalCompra = totalCompra + valorAumentar;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>0) {
			return false;
		}else {
			return true;
		}
	}

}
