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
import org.odftoolkit.odfdom.doc.text.OdfTextHeading;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.w3c.dom.Node;

class OdfHeadingNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextHeading srcNode;

	OdfHeadingNodeHandler(OdfFileDom contentDom, final OdfTextHeading srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextHeading heading = new OdfTextHeading(contentDom);

		if (srcNode.getTextIsListHeaderAttribute() != null) {
			heading.setTextIsListHeaderAttribute(srcNode
					.getTextIsListHeaderAttribute());
		}
		if (srcNode.getTextOutlineLevelAttribute() != null) {
			heading.setTextOutlineLevelAttribute(srcNode
					.getTextOutlineLevelAttribute());
		}
		if (srcNode.getTextRestartNumberingAttribute() != null) {
			heading.setTextRestartNumberingAttribute(srcNode
					.getTextRestartNumberingAttribute());
		}
		if (srcNode.getTextStartValueAttribute() != null) {
			heading.setTextStartValueAttribute(srcNode
					.getTextStartValueAttribute());
		}

		heading.setProperties(handleStyle(heading, srcNode));

		return heading;
	}

	private Map<OdfStyleProperty, String> handleStyle(OdfTextHeading heading,
			OdfTextHeading srcHeading) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTextProperties.FontStyleName);
		properties.add(OdfTextProperties.FontFamily);
		properties.add(OdfTextProperties.FontName);
		properties.add(OdfTextProperties.FontSize);

		Map<OdfStyleProperty, String> srcStyles = srcHeading
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcHeading.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcHeading.getAutomaticStyle();
			StyleStyleElement autoStyle = heading
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}