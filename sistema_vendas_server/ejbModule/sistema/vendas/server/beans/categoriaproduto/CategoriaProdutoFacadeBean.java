package sistema.vendas.server.beans.categoriaproduto;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=CategoriaProdutoFacadeBean.NAME)
public class CategoriaProdutoFacadeBean {
	public static final String NAME = "sistema_vendas_server_CategoriaProdutoFacadeBean";
	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;

	public CategoriaProduto incluir(CategoriaProduto categoriaProduto) {
		manager.persist(categoriaProduto);
		return categoriaProduto;
	}
	
	public CategoriaProduto update(CategoriaProduto categoriaProduto) {
		manager.merge(categoriaProduto);
		return categoriaProduto;
	}
	
	public void remover(CategoriaProduto categoriaProduto) {
		CategoriaProduto c = manager.find(CategoriaProduto.class, categoriaProduto.getCategoriaProdutoId());
		manager.remove(c);
	}
	
	public CategoriaProduto findByPrimaryKey(Integer categoriaProdutoId) {
		CategoriaProduto categoriaProduto = manager.find(CategoriaProduto.class, categoriaProdutoId);
		return categoriaProduto;
	}
	
	public Collection<CategoriaProduto> findAll(){
		Collection<CategoriaProduto> categoriasProduto = new ArrayList<CategoriaProduto>();
		
		Query q = manager.createQuery("from "+CategoriaProduto.NAME);
		
		for(Object o : q.getResultList()) {
			categoriasProduto.add((CategoriaProduto) o);
		}
		
		return categoriasProduto;
	}
	
	
	
}
