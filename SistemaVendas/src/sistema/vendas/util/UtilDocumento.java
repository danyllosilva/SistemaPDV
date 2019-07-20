package sistema.vendas.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import sistema.vendas.server.beans.cesta.Cesta;
import sistema.vendas.server.beans.comprador.Comprador;
import sistema.vendas.server.beans.registrovendas.RegistroVendas;



public class UtilDocumento {

	static SimpleDateFormat dataDoc = new SimpleDateFormat("dd' de 'MMMMMMMM' de 'yyyy' às 'HH:mm:ss",
			new Locale("pt", "BR"));
	
	static SimpleDateFormat dataDocSemHoras = new SimpleDateFormat("dd' de 'MMMMMMMM' de 'yyyy", new Locale("pt", "BR"));
	
	
	public static String gerarNotaFiscalCompra(Comprador comprador, RegistroVendas registroVenda, ArrayList<Cesta> cesta, Double valorTotal) {
		StringBuffer html = new StringBuffer();
		
		String nomeComprador = "SEM NOME REGISTRADO";
		String cpfComprador = ""; 
		
		try {
			cpfComprador = comprador.getCpf();
			nomeComprador = comprador.getNome();
			 
		}catch(NullPointerException exp) {
			exp.printStackTrace();
		}
		html.append(
				"  <!DOCTYPE html5>    " + 
				"<html>    " + 
				"	 <meta charset=\"UTF-8\">     " + 
				"	<head>    " + 
				"	</head>    " + 
				"	<title>Nota Fiscal</title>    " + 
				"    " + 
				"	<style type=\"text/css\">    " + 
				"    " + 
				"    " + 
				"		body{  margin-top: 1cm; margin-left: 1cm; margin-bottom: 2cm; margin-right: 1cm; text-align: justify; }    " + 
				"		.titulo{ font-size: 16px; text-align: center;font-family: Arial, Helvetica, sans-serif; }    " + 
				"		#idProtocolo {float: left;}    " + 
				"		#idProcesso {float: right;}    " + 
				"		     " + 
				"		.block{    " + 
				"				-webkit-print-color-adjust: exact;    " + 
				"				width: 99%;    " + 
				"				padding: -1px;    " + 
				"				padding-top: 2%;    " + 
				"				     " + 
				"				border-right-style: dashed;    " + 
				"				border-left-style: dashed;    " + 
				"				margin-top: 19px;     " + 
				"				border-left: 0px;         " + 
				"				border-left-width: 0px; border-right: 0px;   border-right-width: 0px; border-width: 1px;    " + 
				"    " + 
				"			     " + 
				"			    margin-top: 18px;    " + 
				"}    " + 
				"			}    " + 
				"		.titulo2{margin-top: 19px;    " + 
				"color: #2260cb;}    " + 
				"    " + 
				"	img{    " + 
				"		padding-bottom: 19px;    " + 
				"		padding-top: 19px;    " + 
				"		padding: 9px;    " + 
				"    " + 
				"		}    " + 
				"    " + 
				"		h4{    " + 
				"    		margin-block-end: 1px;    " + 
				"    		  margin-block-start: 1px;    " + 
				"    " + 
				"		}    " + 
				"    " + 
				"		.highlight-gray{    " + 
				"				-webkit-print-color-adjust: exact;    " + 
				"			    background-color: #9492927a;    " + 
				"		}    " + 
				"    " + 
				"	</style>    " + 
				"    " + 
				"	<body>    " + 
				"		<div align=\"left\" >    " + 
				"		<!--	<img src=\"https://www.juridoc.com.br/wp-content/uploads/2017/09/nota-fiscal-juridoc.png\" alt=\"logo tce\"/>  -->    " + 
				"		</div>     " + 
				"		<div id=\"block1\">    " + 
				"				<div align=\"left\">    " + 
				"					<h4 >Comprador nº CPF " +comprador.getCpf()+"</h4>    " + 
				"					<h4>Comprador Nome: "+nomeComprador+"</h4>    " + 
				"					<h4>Cód. Registro de Venda: "+registroVenda.getRegistroVendasId()+"</h4>    " + 
				"					<h4>Data Venda: "+ new String(dataDocSemHoras.format(registroVenda.getDataVenda().getTime()))+" </h4>    " + 
				"				</div>    " + 
				"    " + 
				"				<div class=\"highlight-gray\"><h3> REGISTRO VENDA Nº"+registroVenda.getRegistroVendasId()+"</h3></div>    " + 
				"    " + 
				"				<section align=\"justify\" class=\"block\">    " + 
				"			 		<h4>Item:</h4>    " + 
				"			 	  "); 
									int item = 1;
									for(Cesta c: cesta) {
										html.append("<p>");
										html.append(""+item+".");
										html.append(""+c.getProduto().getNome()+" ---- "+c.getQuantidade()+"-----R$"+c.getValorTotal()+"");
										html.append("</p>");
										
										item++;
									}
				html.append("  ");
							 
				html.append(" 					<h3>TOTAL GERAL:</h3>   "
												+ "<p>"
												+ "R$ "+valorTotal+" "
												+ "</p> "
												+ ""
												+ "<h3>Forma de Pagamento: </h3>  "
												+ "<p>"
												+ ""+registroVenda.getFormaPagamento().getDescricao()+""
												+ "</p>");
				
				html.append(
				"				</section>    " + 
				"	   </div>     " + 
				"	</body>    " + 
				"</html>"
			);
	
	return html.toString();
		
		
	}
	
 }
