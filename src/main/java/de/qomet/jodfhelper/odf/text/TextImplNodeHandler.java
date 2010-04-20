package de.qomet.jodfhelper.odf.text;

import org.apache.xerces.dom.TextImpl;
import org.odftoolkit.odfdom.OdfFileDom;
import org.w3c.dom.Node;

class TextImplNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final TextImpl srcNode;

	TextImplNodeHandler(OdfFileDom contentDom, final TextImpl srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		TextImpl textImpl = new TextImpl(contentDom, srcNode.getTextContent());

		return textImpl;
	}
}