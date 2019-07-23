package sistema.vendas.server.beans.produto;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sistema.vendas.server.beans.categoriaproduto.CategoriaProduto;

@Stateless(name=ProdutoFacadeBean.NAME)
public class ProdutoFacadeBean {
	public static final String NAME = "sistema_vendas_server_ProdutoFacadeBean";
	public static final String JNDI = "sistema_vendas_server/sistema_vendas_server_ProdutoFacadeBean!sistema.vendas.server.beans.produto.ProdutoFacadeBean";

	
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
	
	public void removerByCategoria(CategoriaProduto categoriaProduto) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("DELETE FROM produto WHERE produto.categoria_produto_id = "+categoriaProduto.getCategoriaProdutoId()+" ");
		
		Query q = manager.createNativeQuery(sql.toString());
		q.executeUpdate();

		return;
	}
}
