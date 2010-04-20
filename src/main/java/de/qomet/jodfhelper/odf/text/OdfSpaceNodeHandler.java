package de.qomet.jodfhelper.odf.text;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.text.OdfTextSpace;
import org.w3c.dom.Node;

public class OdfSpaceNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextSpace srcNode;

	OdfSpaceNodeHandler(OdfFileDom contentDom,
			final OdfTextSpace srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextSpace space = new OdfTextSpace(contentDom);

		Integer c = srcNode.getTextCAttribute();
		if (c != null) {
			space.setTextCAttribute(c);
		}

		return space;
	}
}