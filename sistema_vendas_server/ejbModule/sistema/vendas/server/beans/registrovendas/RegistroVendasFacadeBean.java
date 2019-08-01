package sistema.vendas.server.beans.registrovendas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sistema.vendas.server.relatorio.Template1;

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
	
	
	public Collection<Template1> findByFiltroRelatorioTemplate1(Date dataInicial, Date dataFinal){
		String sql = "SELECT registrovendas.registro_vendas_id, data_venda, produto.nome, produto.preco, cesta.quantidade,"
				+ " cesta.valorcomdescontoformapagamento, comprador_id, formapagamentoid "
				+"FROM public.registrovendas  as  registrovendas "
				+"LEFT JOIN  public.cesta as cesta  ON "
				+"cesta.registro_vendas_id = registrovendas.registro_vendas_id "
				+"LEFT JOIN public.produto as produto ON "
				+"cesta.produtoId = produto.codigo_produto_id "

				+"WHERE data_venda BETWEEN "+dateFormatter(dataInicial)+" AND "+dateFormatter(dataFinal)+"";
		
		
		Query q = manager.createNativeQuery(sql);
		Collection<Template1> relatorioTemplate1 = new ArrayList<Template1>();
		
		List<Object[]> result = q.getResultList();
		
		
		for(Object[] o: result) {
			Template1 relatorio = new Template1();
			
			relatorio.setIdRegistroVenda((Integer) o[0]);
			relatorio.setDataCompra((Date) o[1]);
			relatorio.setProdutoNome((String) o[2]);
			relatorio.setValorProduto((Double) o[3]);
			relatorio.setQuantidade((Integer) o[4]);
			relatorio.setValorFinalCompraLoja((Double) o[5]);

			relatorioTemplate1.add(relatorio);
		}
		
		return relatorioTemplate1;
	}
	
	
	public Date dateFormatter(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = null;
		
		try {
			dateWithoutTime = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateWithoutTime ;
	}
	

}
