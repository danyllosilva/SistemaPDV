package sistema.vendas.util;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.InvalidParameterException;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zkoss.zul.Filedownload;

public class PDFUtil {

	// margens do documento pd4ml
	protected Dimension format = PD4Constants.A4;
	protected boolean landscapeValue = false;
	protected int topValue = 10;
	protected int leftValue = 10;
	protected int rightValue = 10;
	protected int bottomValue = 10;
	protected int userSpaceWidth = 1110;

	public InputStream gerarPdf(String conteudo, String fileName) {

		try {
			PD4ML pd4ml = new PD4ML();

			pd4ml.generateOutlines(true);
			pd4ml.setHtmlWidth(userSpaceWidth); // set frame width of "virtual
												// web browser"
			pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			pd4ml.addStyle("BODY {margin: 0}", true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			// System.out.println("conteudo");
			// conteudo = Jsoup.clean(conteudo,
			// Whitelist.relaxed().addTags("table","tr","td","th","tbody").addEnforcedAttribute("table",
			// "border", "1"));
			// conteudo = Jsoup.clean(conteudo,
			// Whitelist.relaxed().addEnforcedAttribute("table", "style",
			// "border: 1px solid black").addEnforcedAttribute("table",
			// "cellpadding", "0").addEnforcedAttribute("table", "cellspacing",
			// "0").addEnforcedAttribute("td", "style", "border: 1px solid
			// black").addAttributes("p", "align"));
			// addEnforcedAttribute("table", "style", "border: 1px solid black")

			// System.out.println(conteudo);

			/*
			 * PolicyFactory policy = Sanitizers.FORMATTING; conteudo =
			 * policy.sanitize(conteudo);
			 */

			pd4ml.render(new StringReader(conteudo), baos);

			baos.close();
			InputStream in = new ByteArrayInputStream(baos.toByteArray());
			Filedownload.save(in, "application/pdf", fileName);
			return in;

		} catch (InvalidParameterException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}