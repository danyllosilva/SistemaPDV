package sistema.vendas.util;

import sistema.vendas.server.beans.comprador.Comprador;
import sistema.vendas.server.beans.usuario.Usuario;

public class ObjetoSessao {
	private Integer usuarioId;
	private Integer compradorId;
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getCompradorId() {
		return compradorId;
	}
	public void setCompradorId(Integer compradorId) {
		this.compradorId = compradorId;
	}

	
	
	
}
