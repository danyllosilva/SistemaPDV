package sistema.vendas.server.beans.produto;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=ProdutoFacadeBean.NAME)
public class ProdutoFacadeBean {
	public static final String NAME = "sistema_vendas_server_ProdutoFacadeBean";
	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	
	public Produto incluir(Produto produto) {
		manager.persist(produto);
		return produto;
	}
	
	public Produto update(Produto produto) {
		manager.merge(produto);
		return produto;
	}
	
	public void remover(Produto produto) {
		Produto p = manager.find(Produto.class, produto.getCodigoProdutoId());
		manager.remove(p);
	}
	
	public Produto findByPrimaryKey(Integer produtoId) {
		Produto p = manager.find(Produto.class, produtoId);
		return p;
	}
	
	public Collection<Produto> findAll(){
		Collection<Produto> produtos = new ArrayList<Produto>();
		
		Query q = manager.createQuery("from "+Produto.NAME);
		
		for(Object o: q.getResultList()) {
			produtos.add((Produto) o);
		}
		
		return produtos;
	}
}
