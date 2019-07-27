package sistema.vendas.pages.VM;

import java.util.Collection;

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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import sistema.vendas.server.beans.categoriaproduto.CategoriaProduto;
import sistema.vendas.server.beans.categoriaproduto.CategoriaProdutoFacadeBean;
import sistema.vendas.server.beans.produto.Produto;
import sistema.vendas.server.beans.produto.ProdutoFacadeBean;
import sistema.vendas.util.ObjetoSessao;

public class ProdutoVM {
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";

	private InitialContext ctx;
	private ObjetoSessao obj;
	
	@Wire("#winProduto")
	private Window winProduto;
	
	private Produto produto;
	private Collection<Produto> produtos;
	
	private CategoriaProduto categoriaProduto;
	private Collection<CategoriaProduto> categoriasProduto;
	
	private ProdutoFacadeBean produtoFacadeBean;
	private CategoriaProdutoFacadeBean categoriaProdutoFacadeBean;
	private boolean desabilitadorBtns;
	private boolean salvarVisible;
	
	public ProdutoVM() {
		try {
			ctx = new InitialContext();
			produtoFacadeBean = (ProdutoFacadeBean) ctx.lookup(GLOBAL_JNDI+ProdutoFacadeBean.JNDI);
			categoriaProdutoFacadeBean = (CategoriaProdutoFacadeBean) ctx.lookup(GLOBAL_JNDI+CategoriaProdutoFacadeBean.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		
		if (os == null) {
			Executions.sendRedirect("index.zul");
		}else if(os.getUsuarioId() != 2){//TODO SET TO :2
			Executions.sendRedirect("index.zul");
		}else {
			obj = os;
			produto = new Produto();
			produtos = produtoFacadeBean.findAll();
			categoriasProduto = categoriaProdutoFacadeBean.findAll();
		}
		
		 
	}
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	
	
	@Command
	@NotifyChange("*")
	public void cadastrarProduto() {
		produto = new Produto();
		desabilitadorBtns = true;
		salvarVisible = true;
	}
	
	@Command
	@NotifyChange("*")
	public void salvarNovoProduto() {
		try {
			if(produto != null) {
				if(produto.getNome() != null && produto.getCodigoProdutoId() == null && produto.getQuantidade() > 0) {
					if(produto.getCategoriaProduto() != null) {
						produto.setCategoriaProdutoId(produto.getCategoriaProduto().getCategoriaProdutoId());
					}
					produto = produtoFacadeBean.incluir(produto);
					produto = new Produto();
					desabilitadorBtns = false;
					
					produtos = produtoFacadeBean.findAll();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void cancelarSalvarNovoProduto() {
		produto = new Produto();
		desabilitadorBtns = false;
		salvarVisible = false;
	}
	 

	@Command
	@NotifyChange("*")
	public void editarProduto() {
		if(produto != null && produto.getCodigoProdutoId() != null) {
			if(produto.getCategoriaProduto() != null) {
				produto.setCategoriaProdutoId(produto.getCategoriaProduto().getCategoriaProdutoId());
			}
			produto = produtoFacadeBean.update(produto);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void removerProduto() {
		if(produto != null && produto.getCodigoProdutoId() != null) {
			try {
				 
				produtoFacadeBean.remover(produto);
				
				produtos.remove(produto);
				produto = new Produto();
				 
			}catch (Exception e) {
				 e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	

	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Collection<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}


	public boolean isDesabilitadorBtns() {
		return desabilitadorBtns;
	}


	public void setDesabilitadorBtns(boolean desabilitadorBtns) {
		this.desabilitadorBtns = desabilitadorBtns;
	}


	public boolean isSalvarVisible() {
		return salvarVisible;
	}


	public void setSalvarVisible(boolean salvarVisible) {
		this.salvarVisible = salvarVisible;
	}


	public Collection<CategoriaProduto> getCategoriasProduto() {
		return categoriasProduto;
	}


	public void setCategoriasProduto(Collection<CategoriaProduto> categoriasProduto) {
		this.categoriasProduto = categoriasProduto;
	}


	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}


	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	 

}
