package sistema.vendas.pages.VM;

import javax.ejb.Init;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import sistema.vendas.server.beans.usuario.Usuario;
import sistema.vendas.server.beans.usuario.UsuarioFacadeBean;
import sistema.vendas.util.ObjetoSessao;

 

public class LoginVM {

	private InitialContext ctx;
	
	private String login = "";
	private String senha = "";
	
	private Usuario usuario;
	private UsuarioFacadeBean usuarioFacadeBean;
	
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	public LoginVM() {
		try {
			ctx = new InitialContext();
			usuarioFacadeBean = (UsuarioFacadeBean)ctx.lookup(GLOBAL_JNDI+UsuarioFacadeBean.JNDI);
		}catch(NamingException exp) {
			exp.printStackTrace();
		}
	}
	
	@Init
	public void init() {
			usuario = new Usuario();
		 
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange("*")
	public void logar() {
		try {
			if ((login == null || login.trim().length() == 0) || (senha == null || senha.trim().length() == 0)) {
				senha = "";
				login = "";
				Clients.showNotification("Os campos Login e Senha devem ser preenchidos!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2500);
			}else {
				usuario = usuarioFacadeBean.findByLogin(login);
				if(usuario != null) {
					if(usuario.getSenha().equals(senha)) {
						//Clients.showNotification("Usuário Logado!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 2000);
						
						ObjetoSessao obj = new ObjetoSessao();
						obj.setUsuarioId(usuario.getUsuarioId());
						Sessions.getCurrent().setAttribute("objetoSessao", obj);
						
						//CAIXA
						if(usuario.getTipoUsario() == 1) {
							Executions.sendRedirect("inserirCliente.zul");
						}
						
					
					}
				}
			}
		}catch(NullPointerException exp) {
			exp.printStackTrace();
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
