package de.qomet.jodfhelper.odf.text;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.text.OdfTextTab;
import org.w3c.dom.Node;

class OdfTabNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextTab srcNode;

	OdfTabNodeHandler(OdfFileDom contentDom, final OdfTextTab srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextTab tab = new OdfTextTab(contentDom);

		if (srcNode.getTextTabRefAttribute() != null) {
			tab.setTextTabRefAttribute(srcNode.getTextTabRefAttribute());
		}

		return tab;
	}
}