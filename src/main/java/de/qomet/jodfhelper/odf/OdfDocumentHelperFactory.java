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

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import de.qomet.jodfhelper.odf.text.OdfTextDocumentHelper;

public abstract class OdfDocumentHelperFactory {

	public static IOdfDocumentHelper getOdfDocumentHelper(OdfDocument doc)
			throws UnsupportedOperationException {
		if (doc instanceof OdfTextDocument) {
			return new OdfTextDocumentHelper((OdfTextDocument) doc);
		}

		throw new UnsupportedOperationException("Not yet supported.");
	}
}