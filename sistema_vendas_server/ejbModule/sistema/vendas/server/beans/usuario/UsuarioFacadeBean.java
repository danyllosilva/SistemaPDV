package sistema.vendas.server.beans.usuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=UsuarioFacadeBean.NAME)
public class UsuarioFacadeBean {
	
	public static final String NAME = "sistema_vendas_server_UsuarioFacadeBean";
	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	
	public Usuario insert(Usuario usuario) {
		 manager.persist(usuario);
		 return usuario;
	}
	
	public Usuario update(Usuario usuario) {
		manager.merge(usuario);
		return usuario;
	}

	public void delete(Usuario usuario) {
		Usuario user = manager.find(Usuario.class, usuario.getUsuarioId());
		manager.remove(user);
	}
	
	
	public Usuario findByPrimaryKey(Usuario usuario) {
		Usuario user = manager.find(Usuario.class, usuario.getUsuarioId());
		return user;
	}
	
	public Collection<Usuario> findAll(){
		Collection<Usuario> usuarios = new ArrayList<Usuario>();
		
		Query q = manager.createQuery("from "+Usuario.NAME);
		
		for(Object o : q.getResultList()) {
			usuarios.add((Usuario) o);
		}
		return usuarios;
		
	}

}
