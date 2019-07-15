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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import sistema.vendas.server.beans.produto.Produto;
import sistema.vendas.server.beans.produto.ProdutoFacadeBean;
import sistema.vendas.server.beans.usuario.UsuarioFacadeBean;


public class CaixaVM {

	
	private InitialContext ctx;
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private Produto produto;
	private ProdutoFacadeBean produtoFacadeBean;
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
	public void init() {
		produtosAdicionadosCaixa = new ArrayList<Produto>();
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
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void remover() {
		
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
	
	

	
}
