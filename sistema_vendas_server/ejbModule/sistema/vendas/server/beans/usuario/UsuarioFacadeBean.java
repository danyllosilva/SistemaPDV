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
	public static final String JNDI = "sistema_vendas_server/sistema_vendas_server_UsuarioFacadeBean!sistema.vendas.server.beans.usuario.UsuarioFacadeBean";

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
	
	public Usuario findByPrimaryKey(Integer usuarioId) {
		Usuario user = manager.find(Usuario.class, usuarioId);
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

	
	public Usuario findByLogin(String login) {
		Query q = manager.createQuery(" select usr from "+Usuario.NAME+" usr where usr.login LIKE :arg0 ");
		q.setParameter("arg0", login.trim());
		
		Usuario usuario = (Usuario) q.getSingleResult();
		
		return usuario;
		
	}
	
	
	/**
	 * INSERT INTO public.usuario(
	usuario_id, login, nome, senha, tipo_usuario)
	VALUES (1, 'levimmartins@gmail.com', 'Levi', '123', 1),
	       (2, 'admin', 'Admin', '123', 2)
	 * 
	 * ***/
}
