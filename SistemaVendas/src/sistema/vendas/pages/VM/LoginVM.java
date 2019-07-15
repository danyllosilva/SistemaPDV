package sistema.vendas.pages.VM;

import javax.ejb.Init;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import sistema.vendas.server.beans.usuario.Usuario;
import sistema.vendas.server.beans.usuario.UsuarioFacadeBean;

 

public class LoginVM {

	private InitialContext ctx;
	
	private Usuario usuario;
	private UsuarioFacadeBean usuarioFacadeBean;
	
	public LoginVM() {
		try {
			ctx = new InitialContext();
			
		}catch(NamingException exp) {
			exp.printStackTrace();
		}
	}
	
	@Init
	public void init() {
	 
		 
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
