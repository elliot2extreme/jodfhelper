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