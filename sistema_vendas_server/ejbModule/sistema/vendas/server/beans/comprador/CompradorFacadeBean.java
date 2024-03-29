package sistema.vendas.server.beans.comprador;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=CompradorFacadeBean.NAME)
public class CompradorFacadeBean {
	public static final String NAME = "sistema_vendas_server_CompradorFacadeBean";
	public static final String JNDI = "sistema_vendas_server/sistema_vendas_server_CompradorFacadeBean!sistema.vendas.server.beans.comprador.CompradorFacadeBean";
	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	public Comprador incluir(Comprador comprador) {
		manager.persist(comprador);
		return comprador;
	}
	
	public Comprador update(Comprador comprador) {
		manager.merge(comprador);
		return comprador;
	}
	
	public void remover(Comprador comprador) {
		Comprador c = manager.find(Comprador.class, comprador.getCompradorId());
		manager.remove(c);
	}
	
	public Comprador findByPrimaryKey(Integer compradorId) {
		Comprador comprador = manager.find(Comprador.class, compradorId);
		return comprador;
	}

	public Collection<Comprador> findAll(){
		Collection<Comprador>  compradores = new ArrayList<Comprador>();
		
		Query q = manager.createQuery("from "+Comprador.NAME);
		
		for(Object o: q.getResultList()) {
			compradores.add((Comprador) o);
		}
		
		return compradores;
	}

	public Comprador findByCpf(String cpf) {
		Query q = manager.createQuery(" select comprador from "+Comprador.NAME+" comprador where comprador.cpf LIKE :arg0 ");
	    q.setParameter("arg0", cpf);
	    
	    Comprador comprador = (Comprador) q.getSingleResult();
		
	    return comprador;
	}
	
}
