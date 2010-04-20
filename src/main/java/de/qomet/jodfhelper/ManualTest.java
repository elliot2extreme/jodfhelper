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

package de.qomet.jodfhelper;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.w3c.dom.Node;

import de.qomet.jodfhelper.odf.IOdfDocumentHelper;
import de.qomet.jodfhelper.odf.OdfDocumentHelperFactory;

public class ManualTest {

	final static String desktop = "C:\\Dokumente und Einstellungen\\user\\"
			+ "Desktop\\jOdfHelper Testdokumente\\";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			if (args[0].equalsIgnoreCase("stresstest"))
				new ManualTest().stressTest();
		} else {
			new ManualTest().test();
		}

		System.exit(0);
	}

	private void test() {
		try {
			OdfDocument docDestination = OdfDocument.loadDocument(desktop
					+ "Auftrag_leer.odt");
			final OdfDocument docSource = OdfDocument.loadDocument(desktop
					+ "Aufzaehlung.odt");

			IOdfDocumentHelper odfDocumentHelper = OdfDocumentHelperFactory
					.getOdfDocumentHelper(docDestination);

			XPath xPath = odfDocumentHelper.getOdfDocument().getXPath();

			Node where = (Node) xPath.evaluate("//text:hidden-text[1]",
					odfDocumentHelper.getOdfContentDom(), XPathConstants.NODE);

			Node nextSibling = where.getParentNode().getNextSibling();

			Node parent = where.getParentNode().getParentNode();
			parent.removeChild(where.getParentNode());

			odfDocumentHelper.addContent(docSource, parent, nextSibling);

			docDestination.save(desktop + "Result.odt");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void stressTest() {
		try {
			OdfDocument docDestination = OdfDocument.loadDocument(desktop
					+ "H08-20.odt");

			IOdfDocumentHelper odfDocumentHelper = OdfDocumentHelperFactory
					.getOdfDocumentHelper(docDestination);

			XPath xPath = odfDocumentHelper.getOdfDocument().getXPath();

			for (int i = 1; i <= 52; i++) {
				final OdfDocument docSource = OdfDocument.loadDocument(desktop
						+ "H08-20\\" + i + ".odt");

				Node where = (Node) xPath.evaluate("//text:hidden-text[1]",
						odfDocumentHelper.getOdfContentDom(),
						XPathConstants.NODE);

				Node parent = where.getParentNode().getParentNode();
				parent.removeChild(where.getParentNode());

				odfDocumentHelper.addContent(docSource, parent, null);
			}

			docDestination.save(desktop + "H08-20_neu.odt");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}