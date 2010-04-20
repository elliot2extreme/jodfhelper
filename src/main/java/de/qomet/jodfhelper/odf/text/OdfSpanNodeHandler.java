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
import org.odftoolkit.odfdom.doc.text.OdfTextSpan;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.w3c.dom.Node;

class OdfSpanNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextSpan srcNode;

	OdfSpanNodeHandler(OdfFileDom contentDom, final OdfTextSpan srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextSpan span = new OdfTextSpan(contentDom);

		span.setProperties(handleStyle(span, srcNode));

		return span;
	}

	private Map<OdfStyleProperty, String> handleStyle(OdfTextSpan span,
			OdfTextSpan srcSpan) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTextProperties.FontStyleName);
		properties.add(OdfTextProperties.FontFamily);
		properties.add(OdfTextProperties.FontName);
		properties.add(OdfTextProperties.FontSize);

		Map<OdfStyleProperty, String> srcStyles = srcSpan
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcSpan.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcSpan.getAutomaticStyle();
			StyleStyleElement autoStyle = span
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}