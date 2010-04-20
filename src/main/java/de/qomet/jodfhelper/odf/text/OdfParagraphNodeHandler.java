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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.w3c.dom.Node;

class OdfParagraphNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextParagraph srcNode;

	OdfParagraphNodeHandler(OdfFileDom contentDom,
			final OdfTextParagraph srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextParagraph paragraph = new OdfTextParagraph(contentDom);

		paragraph.setProperties(handleStyle(paragraph, srcNode));

		return paragraph;
	}

	private Map<OdfStyleProperty, String> handleStyle(
			OdfTextParagraph paragraph, OdfTextParagraph srcParagraph) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

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
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcParagraph.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcParagraph.getAutomaticStyle();
			StyleStyleElement autoStyle = paragraph
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}