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