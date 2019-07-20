package sistema.vendas.util;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;

import org.zefer.pd4ml.PD4Constants;

//import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
 

public class GerarPdf {

	// margens do documento pd4ml
	protected Dimension format = PD4Constants.A4;
	protected boolean landscapeValue = false;
	protected int topValue = 10;
	protected int leftValue = 10;
	protected int rightValue = 10;
	protected int bottomValue = 10;
	protected int userSpaceWidth = 1000;

	public InputStream convert(String conteudo) throws Exception {

		PD4ML pd4ml = new PD4ML();
		pd4ml.generateOutlines(true);
		pd4ml.setHtmlWidth(userSpaceWidth);

		pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);

		pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));

		pd4ml.addStyle(
				"body,p,div{ font-size:14pt !important; font-family:\"Times New Roman\" !important; margin:0 !important}",
				true);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		pd4ml.render(new StringReader(conteudo), baos);

		baos.close();

		InputStream in = new ByteArrayInputStream(baos.toByteArray());
		// Filedownload.save(in,"application/pdf",nomeArquivo);

		return in;

	}
}
