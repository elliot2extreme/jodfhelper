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