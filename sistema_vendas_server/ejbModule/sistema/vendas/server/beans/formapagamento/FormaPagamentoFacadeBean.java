package sistema.vendas.server.beans.formapagamento;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name=FormaPagamentoFacadeBean.NAME)
public class FormaPagamentoFacadeBean {
	public static final String NAME = "sistema_vendas_server_FormaPagamentoFacadeBean";
	
	@PersistenceContext(unitName="sistema_vendas_server")
	public EntityManager manager;
	
	public FormaPagamento incluir(FormaPagamento formaPagamento) {
		manager.persist(formaPagamento);
		return formaPagamento;
	}
	
	public FormaPagamento update(FormaPagamento formaPagamento) {
		manager.merge(formaPagamento);
		return formaPagamento;
	}
	
	public void remover(FormaPagamento formaPagamento) {
		FormaPagamento formapag = manager.find(FormaPagamento.class, formaPagamento.getFormaPagamentoId());
		manager.remove(formapag);
	}
	
	public FormaPagamento findByPrimaryKey(Integer formaPagamentoId) {
		FormaPagamento formaPagamento = manager.find(FormaPagamento.class, formaPagamentoId);
		return formaPagamento;
	}
	
	public Collection<FormaPagamento> findAll(){
		Collection<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
		
		Query q = manager.createQuery("from "+FormaPagamento.NAME);
		
		for(Object o : q.getResultList()) {
			formasPagamento.add((FormaPagamento) o );
		}
		
		return formasPagamento;
		
	}
}
