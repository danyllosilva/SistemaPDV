package sistema.vendas.server.beans.registrovendas;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=RegistroVendasFacadeBean.NAME)
public class RegistroVendasFacadeBean {
	public static final String NAME = "sistema_vendas_server_RegistroVendasFacadeBean";
	public static final String JNDI = "sistema_vendas_server/sistema_vendas_server_RegistroVendasFacadeBean!sistema.vendas.server.beans.registrovendas.RegistroVendasFacadeBean";

	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	public RegistroVendas incluir(RegistroVendas registroVenda) {
		manager.persist(registroVenda);
		return registroVenda;
	}
	
	public RegistroVendas update(RegistroVendas registroVenda) {
		manager.merge(registroVenda);
		return registroVenda;
	}
	
	public void remover(RegistroVendas registroVenda) {
		RegistroVendas rgvendas = manager.find(RegistroVendas.class, registroVenda.getRegistroVendasId());
		manager.remove(rgvendas);
	}
	
	public RegistroVendas findByPrimaryKey(Integer registroVendasId) {
		RegistroVendas rgvendas = manager.find(RegistroVendas.class, registroVendasId);
		return rgvendas;
	}
	
	public Collection<RegistroVendas> findAll(){
		Collection<RegistroVendas> registrosVendas = new ArrayList<RegistroVendas>();
		
		Query q = manager.createQuery("from "+RegistroVendas.NAME);
		
		for(Object o: q.getResultList()) {
			registrosVendas.add((RegistroVendas) o);
		}
		
		return registrosVendas;
	}
	
	
	

}
