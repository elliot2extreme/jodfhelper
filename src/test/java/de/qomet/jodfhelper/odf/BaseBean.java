package de.qomet.jodfhelper.odf;

import javax.xml.xpath.XPath;

import org.junit.After;
import org.junit.Before;
import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

public abstract class BaseBean {

	protected OdfDocument srcDoc;
	protected OdfFileDom srcContentDom;
	protected XPath srcXPath;

	protected OdfDocument doc;
	protected OdfFileDom contentDom;

	@Before
	public void setUp() {
		try {
			srcDoc = OdfDocument
					.loadDocument("./target/test-classes/Postext.odt");
			srcContentDom = srcDoc.getContentDom();
			srcXPath = srcDoc.getXPath();

			doc = OdfTextDocument.newTextDocument();
			contentDom = doc.getContentDom();
		} catch (Exception e) {
		}
	}

	@After
	public void tearDown() {
		srcDoc.close();
		doc.close();
	}
}