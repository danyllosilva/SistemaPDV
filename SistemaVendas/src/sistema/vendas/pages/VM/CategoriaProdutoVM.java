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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import sistema.vendas.server.beans.categoriaproduto.CategoriaProduto;
import sistema.vendas.server.beans.categoriaproduto.CategoriaProdutoFacadeBean;
import sistema.vendas.server.beans.produto.ProdutoFacadeBean;
import sistema.vendas.util.ObjetoSessao;

public class CategoriaProdutoVM {
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private InitialContext ctx;
	
	private CategoriaProduto categoriaProduto;
	private CategoriaProdutoFacadeBean categoriaProdutoFacadeBean;
	private ProdutoFacadeBean produtoFacadeBean;
	
	@Wire("#winCategoriaProduto")
	private Window winCategoriaProduto;
	
	
	private Collection<CategoriaProduto> categoriasProdutos;
	
	private boolean desabilitadorBtns;
	private boolean salvarVisible;
	
	public CategoriaProdutoVM(){
		try {
			ctx = new InitialContext();
			categoriaProdutoFacadeBean = (CategoriaProdutoFacadeBean) ctx.lookup(GLOBAL_JNDI+CategoriaProdutoFacadeBean.JNDI);
			produtoFacadeBean = (ProdutoFacadeBean) ctx.lookup(GLOBAL_JNDI+ProdutoFacadeBean.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		
		if (os == null) {
			Executions.sendRedirect("index.zul");
		}else if(os.getUsuarioId() != 1){//TODO SET TO :2
			Executions.sendRedirect("index.zul");
		}else {
			categoriaProduto = new CategoriaProduto();
			categoriasProdutos = categoriaProdutoFacadeBean.findAll();
		}
		
	}
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	
	/***********************************************INICIO: CATEGORIA PRODUTO********************************************************************/
	
	@Command
	@NotifyChange("*")
	public void cadastrarCategoriaProduto() {
		categoriaProduto = new CategoriaProduto();
		desabilitadorBtns = true;
		salvarVisible = true;
	}
	
	@Command
	@NotifyChange("*")
	public void salvarNovaCategoriaProduto() {
		if(categoriaProduto != null) {
			if(categoriaProduto.getNome() != null && categoriaProduto.getCategoriaProdutoId() == null) {
				categoriaProduto = categoriaProdutoFacadeBean.incluir(categoriaProduto);
				categoriaProduto = new CategoriaProduto();
				desabilitadorBtns = false;
				
				categoriasProdutos = categoriaProdutoFacadeBean.findAll();
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void cancelarSalvarNovaCategoriaProduto() {
		categoriaProduto = new CategoriaProduto();
		desabilitadorBtns = false;
		salvarVisible = false;
	}
	
	@Command
	@NotifyChange("*")
	public void editarCategoriaProduto() {
		if(categoriaProduto != null && categoriaProduto.getCategoriaProdutoId() != null) {
			categoriaProduto = categoriaProdutoFacadeBean.update(categoriaProduto);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void removerCategoriaProduto() {
		if(categoriaProduto != null && categoriaProduto.getCategoriaProdutoId() != null) {
			try {
				produtoFacadeBean.removerByCategoria(categoriaProduto);
				categoriaProdutoFacadeBean.remover(categoriaProduto);
				
				categoriasProdutos.remove(categoriaProduto);
				categoriaProduto = new CategoriaProduto();
				 
			}catch (Exception e) {
				 e.printStackTrace();
			}
		}
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public Collection<CategoriaProduto> getCategoriasProdutos() {
		return categoriasProdutos;
	}

	public void setCategoriasProdutos(Collection<CategoriaProduto> categoriasProdutos) {
		this.categoriasProdutos = categoriasProdutos;
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
	
	/***********************************************FIM: CATEGORIA PRODUTO********************************************************************/

	
	
}




