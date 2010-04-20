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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathConstants;

import org.junit.Test;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.w3c.dom.Node;

import de.qomet.jodfhelper.odf.BaseBean;

public class OdfParagraphNodeHandlerTest extends BaseBean {

	@Test
	public void testHandleNode() {
		try {
			Node srcNode = (Node) srcXPath.evaluate("//text:p[1]",
					srcContentDom, XPathConstants.NODE);

			OdfTextParagraph srcParagraph = (OdfTextParagraph) srcNode;

			OdfParagraphNodeHandler nodeHandler = new OdfParagraphNodeHandler(
					contentDom, srcParagraph);

			Node node = nodeHandler.handleNode();
			assertTrue(node instanceof OdfTextParagraph);

			OdfTextParagraph paragraph = (OdfTextParagraph) node;

			compareStyles(paragraph, srcParagraph);
		} catch (Exception e) {
			fail("Exception aufgetreten");
		}
	}

	private void compareStyles(OdfTextParagraph paragraph,
			OdfTextParagraph srcParagraph) {
		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTextProperties.FontStyleName);
		properties.add(OdfTextProperties.FontFamily);
		properties.add(OdfTextProperties.FontName);
		properties.add(OdfTextProperties.FontSize);

		properties.addAll(OdfParagraphHelper.getAllOdfParagraphProperties());

		Map<OdfStyleProperty, String> srcStyles = srcParagraph
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				String propertyValue = paragraph.getProperty(srcStyle.getKey());
				assertEquals("Unterschiedliche Styledefinition in Ziel",
						srcStyle.getValue(), propertyValue);
			}
		}
	}
}