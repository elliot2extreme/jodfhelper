package de.qomet.jodfhelper.odf.text;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.draw.OdfDrawFrame;
import org.w3c.dom.Node;

class OdfDrawFrameNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfDrawFrame srcNode;

	OdfDrawFrameNodeHandler(OdfFileDom contentDom,
			final OdfDrawFrame srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfDrawFrame frame = new OdfDrawFrame(contentDom);

		if (srcNode.getSvgWidthAttribute() != null) {
			frame.setSvgWidthAttribute(srcNode.getSvgWidthAttribute());
		}
		if (srcNode.getSvgHeightAttribute() != null) {
			frame.setSvgHeightAttribute(srcNode.getSvgHeightAttribute());
		}
		if (srcNode.getSvgXAttribute() != null) {
			frame.setSvgXAttribute(srcNode.getSvgXAttribute());
		}
		if (srcNode.getSvgYAttribute() != null) {
			frame.setSvgYAttribute(srcNode.getSvgYAttribute());
		}

		if (srcNode.getDrawLayerAttribute() != null) {
			frame.setDrawLayerAttribute(srcNode.getDrawLayerAttribute());
		}

		return frame;
	}
}