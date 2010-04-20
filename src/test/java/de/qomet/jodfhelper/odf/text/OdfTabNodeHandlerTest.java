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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.xml.xpath.XPathConstants;

import org.junit.Test;
import org.odftoolkit.odfdom.doc.text.OdfTextTab;
import org.w3c.dom.Node;

import de.qomet.jodfhelper.odf.BaseBean;

public class OdfTabNodeHandlerTest extends BaseBean {

	@Test
	public void testHandleNode() {
		try {
			Node srcNode = (Node) srcXPath.evaluate("//text:tab[1]",
					srcContentDom, XPathConstants.NODE);

			OdfTextTab srcTab = (OdfTextTab) srcNode;

			OdfTabNodeHandler nodeHandler = new OdfTabNodeHandler(contentDom,
					srcTab);

			Node node = nodeHandler.handleNode();
			assertTrue(node instanceof OdfTextTab);

			OdfTextTab tab = (OdfTextTab) node;

			if (srcTab.getTextTabRefAttribute() != null) {
				assertNotNull(tab.getTextTabRefAttribute());

				assertEquals(tab.getTextTabRefAttribute(), srcTab
						.getTextTabRefAttribute());
			} else {
				assertNull(tab.getTextTabRefAttribute());
			}
		} catch (Exception e) {
			fail("Exception aufgetreten");
		}
	}
}