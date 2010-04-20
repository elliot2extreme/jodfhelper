package de.qomet.jodfhelper.odf;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import de.qomet.jodfhelper.odf.text.OdfTextDocumentHelper;

public abstract class OdfDocumentHelperFactory {

	public static IOdfDocumentHelper getOdfDocumentHelper(OdfDocument doc)
			throws UnsupportedOperationException {
		if (doc instanceof OdfTextDocument) {
			return new OdfTextDocumentHelper((OdfTextDocument) doc);
		}

		throw new UnsupportedOperationException("Not supported yet.");
	}
}