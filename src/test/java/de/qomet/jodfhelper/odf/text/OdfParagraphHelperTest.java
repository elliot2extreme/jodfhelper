package de.qomet.jodfhelper.odf.text;

import org.junit.Test;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;

public class OdfParagraphHelperTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testOdfParagraphSetForImmutability() {
		OdfParagraphHelper.getAllOdfParagraphProperties().remove(
				OdfParagraphProperties.WritingModeAutomatic);
	}
}