package sistema.vendas.server.formaPagamento;

public class Credicard implements FormaPagamento{
	//Credicard 5 2,5%

	@Override
	public Double parcelar(double totalCompra, int parcelas) {
		if(parcelas>5) {
			return null;
		}else {
			float valorReduzir = (float) totalCompra * ((float) 2.5/100);
			totalCompra = totalCompra - valorReduzir;
			Double total = new Double(totalCompra);
			
			return total;
		}
	}

	@Override
	public boolean quantidadeParcelasValidas(int parcelas) {
		if(parcelas>5) {
			return false;
		}else {
			return true;
		}
	}
}
