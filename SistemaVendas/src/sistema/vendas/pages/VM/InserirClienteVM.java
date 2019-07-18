package sistema.vendas.pages.VM;

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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import sistema.vendas.server.beans.comprador.Comprador;
import sistema.vendas.server.beans.comprador.CompradorFacadeBean;
import sistema.vendas.util.ObjetoSessao;

 
public class InserirClienteVM {
	
	private InitialContext ctx;
	
	private Comprador comprador;
	private CompradorFacadeBean compradorFacadeBean;
	private ObjetoSessao objetoSessao;
	
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	
	public InserirClienteVM() {
		
			try {
				ctx= new InitialContext();
				compradorFacadeBean = (CompradorFacadeBean) ctx.lookup(GLOBAL_JNDI+CompradorFacadeBean.JNDI);
				
			} catch (NamingException e) {
				Messagebox.show(e.getMessage(), "Erro Lookup", Messagebox.OK, Messagebox.EXCLAMATION);
				e.printStackTrace();
			}
	}
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		if (os.getUsuarioId() == null) {
			Executions.sendRedirect("login.zul");
		}else {
			objetoSessao = os;
			comprador = new Comprador();
		}
		 
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("*")
	public void prosseguir() {
		comprador = new Comprador();
		try {
			if(comprador.getCpf().trim() != null) {
				try {
					comprador = compradorFacadeBean.findByCpf(comprador.getCpf().trim());
					
					if(comprador.getCompradorId() != null) {
						objetoSessao.setCompradorId(comprador.getCompradorId());
						Executions.sendRedirect("caixa.zul");
					}
					
				}catch(NullPointerException exp) {
					exp.printStackTrace();
	
				}
				
			}
			
		}catch(NullPointerException exp) {
			Executions.sendRedirect("caixa.zul");
			Clients.showNotification("CPF Não consta em nossa base de dados!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);

		}
		
	}

	@Command
	public void sair() {
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("login.zul");
	}
	
	
	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	

	
	
	
}
