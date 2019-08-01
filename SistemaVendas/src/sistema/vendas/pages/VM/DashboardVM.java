package sistema.vendas.pages.VM;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import sistema.vendas.server.beans.registrovendas.RegistroVendasFacadeBean;
import sistema.vendas.util.ObjetoSessao;
import sistema.vendas.util.Report;

public class DashboardVM {
	public static final String GLOBAL_JNDI = "java:global/sistema_vendas_ear/";
	
	private InitialContext ctx;
	private ObjetoSessao obj;
	
	@Wire("#winRelatorio")
	private Window winRelatorio;
	
	private Date dataInicial;
	private Date dataFinal;
	private Media media;

	private List<String> formatosRelatorio = Arrays.asList(new String[] { "PDF", "CSV", "XLS", "HTML" });
	private String formatoRelatorio = "CSV";
	
	private RegistroVendasFacadeBean registroVendaFacadeBean;
 	
	public DashboardVM() {
		
		try {
			ctx = new InitialContext();
			registroVendaFacadeBean = (RegistroVendasFacadeBean) ctx.lookup(GLOBAL_JNDI+RegistroVendasFacadeBean.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Init
	public void init(@ScopeParam(scopes = Scope.SESSION, value = "objetoSessao") ObjetoSessao os) {
		
		if (os == null) {
			Executions.sendRedirect("index.zul");
		}else if(os.getUsuarioId() != 2){
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
		Sessions.getCurrent().setAttribute("objetoSessao", obj);
		Executions.sendRedirect("produto.zul");
		 
	}
	

	@Command
	@NotifyChange("*")
	public void secaoRelatorio() {
		winRelatorio.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void sair() {
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("index.zul");
	}

	@Command
	@NotifyChange("*") 
	public void gerarRelatorio() throws FileNotFoundException {
		Collection<?> relatorioVendas = registroVendaFacadeBean.findByFiltroRelatorioTemplate1(dataInicial, dataFinal);
	
		if(!relatorioVendas.isEmpty()) {
				if(relatorioVendas != null) {
					
						Map<String, Object> param = new HashMap<String, Object>();
						Properties properties = Report.getProperties(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/relatorios"), relatorioVendas, param,
								formatoRelatorio, "/relatorioDeVendas.jasper");
						
						String titulo = String.valueOf(properties.get("nome"));
						String extensao = String.valueOf(properties.get("extensao"));
						String formato = String.valueOf(properties.get("formato"));
						File arquivo = (File) properties.get("arquivo");
						
						try {
							   media = new AMedia(titulo, extensao, formato, arquivo, true);
							 
						 	if (formatoRelatorio.equals("PDF") || formatoRelatorio.equals("HTML") || formatoRelatorio.equals("XLS") || formatoRelatorio.equals("CSV") ) {
						 		//winRelatorio.doModal();
						 	}
						}catch(Exception exp) {
							 exp.printStackTrace();
						}
			
			} 
		}
		
		
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
 
}
