package sistema.vendas.server.formaPagamento;

public interface FormaPagamento {
	public boolean quantidadeParcelasValidas(int parcelas);
	public Double parcelar(double totalCompra, int parcelas);
}
