package de.qomet.jodfhelper.odf.text;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.table.OdfTableHeaderRows;
import org.w3c.dom.Node;

class OdfTableHeaderRowsNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;

	OdfTableHeaderRowsNodeHandler(OdfFileDom contentDom,
			OdfTableHeaderRows srcNode) {
		this.contentDom = contentDom;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTableHeaderRows tableHeaderRows = new OdfTableHeaderRows(contentDom);

		return tableHeaderRows;
	}
}