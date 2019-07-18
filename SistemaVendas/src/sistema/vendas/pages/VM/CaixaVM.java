package sistema.vendas.pages.VM;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Init;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import sistema.vendas.server.beans.cesta.Cesta;
import sistema.vendas.server.beans.produto.Produto;
import sistema.vendas.server.beans.produto.ProdutoFacadeBean;
import sistema.vendas.server.beans.registrovendas.RegistroVendas;
import sistema.vendas.util.ObjetoSessao;


public class CaixaVM {

	
	private InitialContext ctx;
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private Produto produto;
	private Produto produtoBanco;
	private Integer quantidade;
	private Double valorTotalCarrinho = 0.00;
	
	private Cesta cesta;
	private RegistroVendas registroVenda;
	private ProdutoFacadeBean produtoFacadeBean;
	
	private Collection<Cesta> produtosAComprarCesta;
	private Collection<Produto> produtosAdicionadosCaixa;
	private Collection<Produto> produtosBanco;
	
	@Wire("#winListagemBanco")
	private Window winListagemBanco;
	
	public CaixaVM() {
		try {
			ctx = new InitialContext();
			produtoFacadeBean = (ProdutoFacadeBean)ctx.lookup(GLOBAL_JNDI+ProdutoFacadeBean.JNDI);
		}catch(NamingException exp) {
			exp.printStackTrace();
		}
	}
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		if (os.getUsuarioId() == null) {
			Executions.sendRedirect("login.zul");
			System.out.println("TESTE TESTE");
		}else if(os.getUsuarioId() > 1){
			Executions.sendRedirect("login.zul");
		}else {
			produtosAdicionadosCaixa = new ArrayList<Produto>();
			produtosAComprarCesta = new ArrayList<Cesta>();
			registroVenda = new RegistroVendas();
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
	public void finalizar() {
		
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
	
	
	
	
	

	
}
