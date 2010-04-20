/*******************************************************************************
 * Copyright (c) 2009 Softwareschmiede Höffl GmbH
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	 Markus Buch - initial API and implementation
 *******************************************************************************/

package de.qomet.jodfhelper.odf.text;

import org.apache.xerces.dom.TextImpl;
import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.draw.OdfDrawFrame;
import org.odftoolkit.odfdom.doc.draw.OdfDrawImage;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableColumn;
import org.odftoolkit.odfdom.doc.table.OdfTableHeaderRows;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.doc.text.OdfTextHeading;
import org.odftoolkit.odfdom.doc.text.OdfTextList;
import org.odftoolkit.odfdom.doc.text.OdfTextListItem;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.doc.text.OdfTextSpace;
import org.odftoolkit.odfdom.doc.text.OdfTextSpan;
import org.odftoolkit.odfdom.doc.text.OdfTextTab;
import org.w3c.dom.Node;

public class NodeHandlerFactory {
	OdfFileDom contentDom;

	public NodeHandlerFactory(OdfFileDom contentDom) {
		this.contentDom = contentDom;
	}

	INodeHandler getNodeHandler(Node srcNode) {
		if (srcNode instanceof OdfTextParagraph) {
			return new OdfParagraphNodeHandler(contentDom,
					(OdfTextParagraph) srcNode);
		} else if (srcNode instanceof OdfTextHeading) {
			return new OdfHeadingNodeHandler(contentDom,
					(OdfTextHeading) srcNode);
		} else if (srcNode instanceof TextImpl) {
			return new TextImplNodeHandler(contentDom, (TextImpl) srcNode);
		} else if (srcNode instanceof OdfTextSpan) {
			return new OdfSpanNodeHandler(contentDom, (OdfTextSpan) srcNode);
		} else if (srcNode instanceof OdfTextTab) {
			return new OdfTabNodeHandler(contentDom, (OdfTextTab) srcNode);
		} else if (srcNode instanceof OdfTextSpace) {
			return new OdfSpaceNodeHandler(contentDom, (OdfTextSpace) srcNode);
		} else if (srcNode instanceof OdfTextList) {
			return new OdfListNodeHandler(contentDom, (OdfTextList) srcNode);
		} else if (srcNode instanceof OdfTextListItem) {
			return new OdfListItemNodeHandler(contentDom,
					(OdfTextListItem) srcNode);
		} else if (srcNode instanceof OdfTable) {
			return new OdfTableNodeHandler(contentDom, (OdfTable) srcNode);
		} else if (srcNode instanceof OdfTableColumn) {
			return new OdfTableColumnNodeHandler(contentDom,
					(OdfTableColumn) srcNode);
		} else if (srcNode instanceof OdfTableHeaderRows) {
			return new OdfTableHeaderRowsNodeHandler(contentDom,
					(OdfTableHeaderRows) srcNode);
		} else if (srcNode instanceof OdfTableRow) {
			return new OdfTableRowNodeHandler(contentDom, (OdfTableRow) srcNode);
		} else if (srcNode instanceof OdfTableCell) {
			return new OdfTableCellNodeHandler(contentDom,
					(OdfTableCell) srcNode);
		} else if (srcNode instanceof OdfDrawFrame) {
			return new OdfDrawFrameNodeHandler(contentDom,
					(OdfDrawFrame) srcNode);
		} else if (srcNode instanceof OdfDrawImage) {
			return new OdfDrawImageNodeHandler(contentDom,
					(OdfDrawImage) srcNode);
		} else {
			return new DefaultNodeHandler();
		}
	}
}