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
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTableProperties;
import org.w3c.dom.Node;

class OdfTableNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTable srcNode;

	OdfTableNodeHandler(OdfFileDom contentDom, OdfTable srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTable table = new OdfTable(contentDom);

		if (srcNode.getTableIsSubTableAttribute() != null) {
			table.setTableIsSubTableAttribute(srcNode
					.getTableIsSubTableAttribute());
		}
		if (srcNode.getTableNameAttribute() != null) {
			table.setTableNameAttribute(srcNode.getTableNameAttribute());
		}
		if (srcNode.getTablePrintAttribute() != null) {
			table.setTablePrintAttribute(srcNode.getTablePrintAttribute());
		}
		if (srcNode.getTablePrintRangesAttribute() != null) {
			table.setTablePrintRangesAttribute(srcNode
					.getTablePrintRangesAttribute());
		}
		if (srcNode.getTableProtectedAttribute() != null) {
			table.setTableProtectedAttribute(srcNode
					.getTableProtectedAttribute());
		}
		if (srcNode.getTableProtectionKeyAttribute() != null) {
			table.setTableProtectionKeyAttribute(srcNode
					.getTableProtectionKeyAttribute());
		}

		table.setProperties(handleTableStyle(table, srcNode));

		return table;
	}

	private Map<OdfStyleProperty, String> handleTableStyle(OdfTable table,
			OdfTable srcTable) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTableProperties.Align);
		properties.add(OdfTableProperties.BackgroundColor);
		properties.add(OdfTableProperties.BorderModel);
		properties.add(OdfTableProperties.BreakAfter);
		properties.add(OdfTableProperties.BreakBefore);
		properties.add(OdfTableProperties.Display);
		properties.add(OdfTableProperties.KeepWithNext);
		properties.add(OdfTableProperties.Margin);
		properties.add(OdfTableProperties.MarginBottom);
		properties.add(OdfTableProperties.MarginLeft);
		properties.add(OdfTableProperties.MarginRight);
		properties.add(OdfTableProperties.MarginTop);
		properties.add(OdfTableProperties.MayBreakBetweenRows);
		properties.add(OdfTableProperties.PageNumber);
		properties.add(OdfTableProperties.RelWidth);
		properties.add(OdfTableProperties.StyleShadow);
		properties.add(OdfTableProperties.Width);
		properties.add(OdfTableProperties.WritingMode);

		Map<OdfStyleProperty, String> srcStyles = srcTable
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcTable.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcTable.getAutomaticStyle();
			StyleStyleElement autoStyle = table
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}