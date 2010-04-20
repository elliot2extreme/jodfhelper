package de.qomet.jodfhelper.odf;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.w3c.dom.Node;

public interface IOdfDocumentHelper {

	public void addContent(final OdfDocument srcDoc) throws Exception;

	public void addContent(final OdfDocument srcDoc, Node parent,
			Node nextSibling) throws Exception;

	public OdfDocument getOdfDocument();

	public OdfFileDom getOdfContentDom();
}