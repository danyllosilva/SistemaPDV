package sistema.vendas.pages.VM;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;


import sistema.vendas.server.beans.cesta.Cesta;
import sistema.vendas.server.beans.cesta.CestaFacadeBean;
import sistema.vendas.server.beans.comprador.Comprador;
import sistema.vendas.server.beans.comprador.CompradorFacadeBean;
import sistema.vendas.server.beans.produto.Produto;
import sistema.vendas.server.beans.produto.ProdutoFacadeBean;
import sistema.vendas.server.beans.registrovendas.RegistroVendas;
import sistema.vendas.server.beans.registrovendas.RegistroVendasFacadeBean;
import sistema.vendas.server.formaPagamento.AmericanExpress;
import sistema.vendas.server.formaPagamento.Debito;
import sistema.vendas.server.formaPagamento.Dinheiro;
import sistema.vendas.server.formaPagamento.Elo;
import sistema.vendas.server.formaPagamento.FormaPagamento;
import sistema.vendas.server.formaPagamento.MasterCard;
import sistema.vendas.server.formaPagamento.Ticket;
import sistema.vendas.server.formaPagamento.TipoPagamento;
import sistema.vendas.server.formaPagamento.TipoPagamento.tipo;
import sistema.vendas.server.formaPagamento.Visa;
import sistema.vendas.util.ObjetoSessao;
import sistema.vendas.util.PDFUtil;
import sistema.vendas.util.UtilDocumento;


public class CaixaVM {

	
	private InitialContext ctx;
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private ObjetoSessao objetoSessao;
	private Comprador comprador;
	private Produto produto;
	private Produto produtoBanco;
	private Integer quantidade;
	private Integer quantidadeParcelasDesejadas;
	private Double valorTotalCarrinho = 0.00;
	
	private Cesta cesta;
	private RegistroVendas registroVenda;
	
	private ProdutoFacadeBean produtoFacadeBean;
	private CompradorFacadeBean compradorFacadeBean;
	private RegistroVendasFacadeBean registroVendaFacadeBean;
	private CestaFacadeBean cestaFacadeBean;
	
	private Collection<Cesta> produtosAComprarCesta;
	private Collection<Produto> produtosAdicionadosCaixa;
	private Collection<Produto> produtosBanco;
	private Integer codProduto = new Integer(0);
	
	private String[] userList = { "Dinheiro", "Débito" ,"Ticket", "MasterCard", "Visa", "Elo", "American Express", "Credicard" };
	private ListModelList model = new ListModelList(userList);
	
	@Wire("#winListagemBanco")
	private Window winListagemBanco;
	
	@Wire("#winListagemFormasPagamento")
	private Window winListagemFormasPagamento;
	
	public CaixaVM() {
		try {
			ctx = new InitialContext();
			produtoFacadeBean = (ProdutoFacadeBean)ctx.lookup(GLOBAL_JNDI+ProdutoFacadeBean.JNDI);
			compradorFacadeBean = (CompradorFacadeBean) ctx.lookup(GLOBAL_JNDI+CompradorFacadeBean.JNDI);
			registroVendaFacadeBean = (RegistroVendasFacadeBean) ctx.lookup(GLOBAL_JNDI+RegistroVendasFacadeBean.JNDI);
			cestaFacadeBean = (CestaFacadeBean) ctx.lookup(GLOBAL_JNDI+CestaFacadeBean.JNDI);

		}catch(NamingException exp) {
			exp.printStackTrace();
		}
	}
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		
		if (os == null) {
			Executions.sendRedirect("index.zul");
		}else if(os.getUsuarioId() > 1){
			Executions.sendRedirect("index.zul");
		}else {
			inicializacao(os);
		}
		
		 
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	
	@Command
	@NotifyChange("*")
	public void adicionar() {
		winListagemBanco.setClosable(true);
		winListagemBanco.doModal();
		
		produtosBanco = produtoFacadeBean.findAll();
		
		if(produtosAdicionadosCaixa == null) {
			produtosAdicionadosCaixa = new ArrayList<Produto>();
		}
		
		
		if(produtosAComprarCesta == null) {
			produtosAComprarCesta = new ArrayList<Cesta>();
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void buscarPorCodigoProduto() {
		try {
			if(codProduto != null && codProduto>0) {
				produtoBanco = produtoFacadeBean.findByPrimaryKey(codProduto);
				if(produtoBanco == null) {
					Clients.showNotification("Não existe produto para o cód. informado!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
				}
			}else {
				produtoBanco = new Produto();
				Clients.showNotification("Não existe produto para o cód. informado!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
			}
		}catch(Exception exp) {
			exp.printStackTrace();

		}
	}
	
	@Command
	@NotifyChange("*")
	public void adicionarNoCarrinho() {
		try {
			if(quantidade != null) {
			
				if((produtoBanco.getQuantidade() - quantidade) < 0 ) {
					Clients.showNotification("Quantidade inválida!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
				}else {
					cesta = new Cesta();
					cesta.setProduto(produtoBanco);
					cesta.setQuantidade(quantidade);
					cesta.setRegistroVendas(registroVenda);
					
					produtosAComprarCesta.add(cesta);
					valorTotalCarrinho+=cesta.getValorTotal();
					 
					 
					produtosAdicionadosCaixa.add(produto);
					
					Clients.showNotification("Produto Adicionado!", Clients.NOTIFICATION_TYPE_INFO, null, null, 2500);
					winListagemBanco.setVisible(false);
				}
				
			}
		}catch(NullPointerException exp) {
			Clients.showNotification("Insira a quantidade!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
			exp.printStackTrace();
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void remover() {
		try {
			if(cesta !=null) {
				produtosAComprarCesta.remove(cesta);
				valorTotalCarrinho-=cesta.getValorTotal();
				Clients.showNotification("Produto Removido do carrinho!", Clients.NOTIFICATION_TYPE_INFO, null, null, 2500);

			}
		}catch(NullPointerException exp) {
			Clients.showNotification("Selecione um produto!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
			exp.printStackTrace();
		}	
	}
	
	
	@Command
	@NotifyChange("*")
	public void cancelarCarrinho() {
		Executions.sendRedirect("inserirCliente.zul");
	}
	
	
	@Command
	@NotifyChange("*")
	public void finalizar() {
		 winListagemFormasPagamento.doModal();
		
	}

	@Command
	@NotifyChange("*")
	public void finalizarCompra() {
		try {
			
			FormaPagamento formaPagamento = null;
			Integer tipoPagamento = null;
			if(model.getSelection().toString().equals("[Dinheiro]")) {
				formaPagamento = new Dinheiro();
				tipoPagamento = TipoPagamento.tipo.Dinheiro.ordinal();	
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");
				
			}
			
			if(model.getSelection().toString().equals("[Débito]")) {
				formaPagamento = new Debito();
				tipoPagamento = TipoPagamento.tipo.Debito.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");
				
			}
			
			if(model.getSelection().toString().equals("[Ticket]")) {
				formaPagamento = new Ticket();
				tipoPagamento = TipoPagamento.tipo.Ticket.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");
			}
			
			if(model.getSelection().toString().equals("[MasterCard]")) {
				formaPagamento = new MasterCard();
				tipoPagamento = TipoPagamento.tipo.MasterCard.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");
			}
			
			if(model.getSelection().toString().equals("[Visa]")) {
				formaPagamento = new Visa();
				tipoPagamento = TipoPagamento.tipo.Visa.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");

			}
			
			if(model.getSelection().toString().equals("[Elo]")) {
				formaPagamento = new Elo();
				tipoPagamento = TipoPagamento.tipo.Elo.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");

			}
			 
			if(model.getSelection().toString().equals("[American Express]")) {
				formaPagamento = new AmericanExpress();
				tipoPagamento = TipoPagamento.tipo.AmericanExpress.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");

			}
			
			if(model.getSelection().toString().equals("[Credicard]")) {
				formaPagamento = new AmericanExpress();
				tipoPagamento = TipoPagamento.tipo.Credicard.ordinal();
				System.out.println("****************************");
				System.out.println(tipoPagamento);
				System.out.println("****************************");
			}
			
			if(!formaPagamento.quantidadeParcelasValidas(quantidadeParcelasDesejadas)) {
				Clients.showNotification("Número de Parcelas Inválido!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
				return;
			}
			 
			
			if(produtosAComprarCesta.size()>0) {
						registroVenda.setFormaPagamentoId(tipoPagamento);
						registroVenda.setDataVenda(new Date());
						
						try {
								registroVenda = registroVendaFacadeBean.incluir(registroVenda);
								try {
									if(formaPagamento != null && quantidadeParcelasDesejadas != null) {
										 
										
										for(Cesta c: produtosAComprarCesta) {
											System.out.println("Quantidade: "+quantidadeParcelasDesejadas.intValue());
											System.out.println(formaPagamento.parcelar(c.getValorTotal(), quantidadeParcelasDesejadas.intValue()));
											c.setValorComDescontoFormaPagamento(formaPagamento.parcelar(c.getValorTotal(), quantidadeParcelasDesejadas.intValue()));
										}
									}
								}catch (Exception e) {
									e.printStackTrace();
									Clients.showNotification("Forma de Pagamento Inválida / Número de Parcelas Inválido!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);

								}
								
							Produto p;	
							for(Cesta c: produtosAComprarCesta) {
								c.setProdutoId(c.getProduto().getCodigoProdutoId());
								c = cestaFacadeBean.incluir(c);
								
								
								p = c.getProduto();
								p = produtoFacadeBean.findByPrimaryKey(p.getCodigoProdutoId());
								p.setQuantidade(p.getQuantidade() - c.getQuantidade());
								produtoFacadeBean.update(p);
								
							}
							
							String notaFiscal = UtilDocumento.gerarNotaFiscalCompra(registroVenda.getComprador(), registroVenda,  (ArrayList<Cesta>) produtosAComprarCesta , valorTotalCarrinho, model.getSelection().toString());
							PDFUtil pdfUtil = new PDFUtil();
							final InputStream mediais = pdfUtil.gerarPdf(notaFiscal , "NotaFiscal.pdf");
							final AMedia amedia = new AMedia("Protocolo.pdf", "pdf", "application/pdf", mediais);
							
							winListagemFormasPagamento.setVisible(false);
							
							//Executions.sendRedirect("inserirCliente.zul");
							inicializacao(objetoSessao);
							
						}catch(Exception exp) {
							exp.printStackTrace();
						}
					
			}else {
				Clients.showNotification("Não há produtos no carrinho!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);

			}  
		}catch(Exception exp){
			exp.printStackTrace();
		}
		
	}
	
	
	public void inicializacao(ObjetoSessao os) {
		objetoSessao = os;
		produtosAdicionadosCaixa = new ArrayList<Produto>();
		produtosAComprarCesta = new ArrayList<Cesta>();
		registroVenda = new RegistroVendas();
		valorTotalCarrinho = 0.00;
		
		try {
			if(os.getCompradorId() != null) {
				registroVenda.setComprador(compradorFacadeBean.findByPrimaryKey(os.getCompradorId()));
			}
		}catch(NullPointerException exp) {
			exp.printStackTrace();
		}
	}
 
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Collection<Produto> getProdutosAdicionadosCaixa() {
		return produtosAdicionadosCaixa;
	}

	public void setProdutosAdicionadosCaixa(Collection<Produto> produtosAdicionadosCaixa) {
		this.produtosAdicionadosCaixa = produtosAdicionadosCaixa;
	}

	public Collection<Produto> getProdutosBanco() {
		return produtosBanco;
	}

	public void setProdutosBanco(Collection<Produto> produtosBanco) {
		this.produtosBanco = produtosBanco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProdutoBanco() {
		return produtoBanco;
	}

	public void setProdutoBanco(Produto produtoBanco) {
		this.produtoBanco = produtoBanco;
	}

	public Cesta getCesta() {
		return cesta;
	}

	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}

	public RegistroVendas getRegistroVenda() {
		return registroVenda;
	}

	public void setRegistroVenda(RegistroVendas registroVenda) {
		this.registroVenda = registroVenda;
	}

	public Collection<Cesta> getProdutosAComprarCesta() {
		return produtosAComprarCesta;
	}

	public void setProdutosAComprarCesta(Collection<Cesta> produtosAComprarCesta) {
		this.produtosAComprarCesta = produtosAComprarCesta;
	}

	public Double getValorTotalCarrinho() {
		return valorTotalCarrinho;
	}

	public void setValorTotalCarrinho(Double valorTotalCarrinho) {
		this.valorTotalCarrinho = valorTotalCarrinho;
	}


	public Integer getQuantidadeParcelasDesejadas() {
		return quantidadeParcelasDesejadas;
	}

	public void setQuantidadeParcelasDesejadas(Integer quantidadeParcelasDesejadas) {
		this.quantidadeParcelasDesejadas = quantidadeParcelasDesejadas;
	}

	public ListModelList getModel() {
		return model;
	}

	public void setModel(ListModelList model) {
		this.model = model;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	 
}
