package sistema.vendas.pages.VM;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.BindUtils;
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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.google.javascript.jscomp.CodingConvention.Bind;

import sistema.vendas.server.beans.categoriaproduto.CategoriaProduto;
import sistema.vendas.server.beans.categoriaproduto.CategoriaProdutoFacadeBean;
import sistema.vendas.util.ObjetoSessao;

public class DashboardVM {
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private InitialContext ctx;
	private ObjetoSessao obj;
	
	@Wire("#winProduto")
	private Window winProduto;
	
	@Wire("#winRelatorio")
	private Window winRelatorio;
	
	public DashboardVM() {
		
		try {
			ctx = new InitialContext();
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
			obj = os;
		}
		
		 
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	


	@Command
	@NotifyChange("*")
	public void secaoCategoriaProduto() {
		 
		Sessions.getCurrent().setAttribute("objetoSessao", obj);
		Executions.sendRedirect("categoriaProduto.zul");
	}
	
	@Command
	@NotifyChange("*")
	public void secaoProduto() {
		winProduto.doModal();
	}
	

	@Command
	@NotifyChange("*")
	public void secaoRelatorio() {
		winRelatorio.doModal();
	}
	
	
 
}
