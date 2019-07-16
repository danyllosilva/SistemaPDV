package sistema.vendas.server.beans.cesta;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=CestaFacadeBean.NAME)
public class CestaFacadeBean {
	public static final String NAME = "sistema_vendas_server_CestaFacadeBean";
	public static final String JNDI = "sistema_vendas_server/sistema_vendas_server_CestaFacadeBean!sistema.vendas.server.beans.cesta.CestaFacadeBean";

	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	public Cesta incluir(Cesta cesta) {
		manager.persist(cesta);
		return cesta;
	}
	
	public Cesta update(Cesta cesta) {
		manager.merge(cesta);
		return cesta;
	}
	
	public void remover(Cesta cesta) {
		Cesta c = manager.find(Cesta.class, cesta.getCestaId());
		manager.remove(c);
	}
	
	public Cesta findByPrimaryKey(Integer cestaId) {
		Cesta cesta = manager.find(Cesta.class, cestaId);
		return cesta;
	}
	
	public Collection<Cesta> findAll(){
		Collection<Cesta> cestas = new ArrayList<Cesta>();
		
		Query q = manager.createQuery("from "+Cesta.NAME);
		
		for(Object o: q.getResultList()) {
			cestas.add((Cesta) o );
		}
		
		return cestas;
	}
	
	
}
