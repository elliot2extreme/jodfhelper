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
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTableCellProperties;
import org.w3c.dom.Node;

class OdfTableCellNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTableCell srcNode;

	OdfTableCellNodeHandler(OdfFileDom contentDom, OdfTableCell srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTableCell tableCell = new OdfTableCell(contentDom);

		// Nur Methoden aus OdfTableCellElement
		if (srcNode.getTableNumberColumnsSpannedAttribute() != null) {
			tableCell.setTableNumberColumnsSpannedAttribute(srcNode
					.getTableNumberColumnsSpannedAttribute());
		}
		if (srcNode.getTableNumberMatrixColumnsSpannedAttribute() != null) {
			tableCell.setTableNumberMatrixColumnsSpannedAttribute(srcNode
					.getTableNumberMatrixColumnsSpannedAttribute());
		}
		if (srcNode.getTableNumberMatrixRowsSpannedAttribute() != null) {
			tableCell.setTableNumberMatrixRowsSpannedAttribute(srcNode
					.getTableNumberMatrixRowsSpannedAttribute());
		}
		if (srcNode.getTableNumberRowsSpannedAttribute() != null) {
			tableCell.setTableNumberRowsSpannedAttribute(srcNode
					.getTableNumberRowsSpannedAttribute());
		}

		tableCell.setProperties(handleTableCellStyle(tableCell, srcNode));

		return tableCell;
	}

	private Map<OdfStyleProperty, String> handleTableCellStyle(
			OdfTableCell tableCell, OdfTableCell srcTableCell) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTableCellProperties.BackgroundColor);
		properties.add(OdfTableCellProperties.Border);
		properties.add(OdfTableCellProperties.BorderBottom);
		properties.add(OdfTableCellProperties.BorderLeft);
		properties.add(OdfTableCellProperties.BorderLineWidth);
		properties.add(OdfTableCellProperties.BorderLineWidthBottom);
		properties.add(OdfTableCellProperties.BorderLineWidthLeft);
		properties.add(OdfTableCellProperties.BorderLineWidthRight);
		properties.add(OdfTableCellProperties.BorderLineWidthTop);
		properties.add(OdfTableCellProperties.BorderRight);
		properties.add(OdfTableCellProperties.BorderTop);
		properties.add(OdfTableCellProperties.CellProtect);
		properties.add(OdfTableCellProperties.DecimalPlaces);
		properties.add(OdfTableCellProperties.DiagonalBlTr);
		properties.add(OdfTableCellProperties.DiagonalBlTrWidths);
		properties.add(OdfTableCellProperties.DiagonalTlBr);
		properties.add(OdfTableCellProperties.DiagonalTlBrWidths);
		properties.add(OdfTableCellProperties.Direction);
		properties.add(OdfTableCellProperties.GlyphOrientationVertical);
		properties.add(OdfTableCellProperties.Padding);
		properties.add(OdfTableCellProperties.PaddingBottom);
		properties.add(OdfTableCellProperties.PaddingLeft);
		properties.add(OdfTableCellProperties.PaddingRight);
		properties.add(OdfTableCellProperties.PaddingTop);
		properties.add(OdfTableCellProperties.PrintContent);
		properties.add(OdfTableCellProperties.RepeatContent);
		properties.add(OdfTableCellProperties.RotationAlign);
		properties.add(OdfTableCellProperties.RotationAngle);
		properties.add(OdfTableCellProperties.StyleShadow);
		properties.add(OdfTableCellProperties.ShrinkToFit);
		properties.add(OdfTableCellProperties.TextAlignSource);
		properties.add(OdfTableCellProperties.VerticalAlign);
		properties.add(OdfTableCellProperties.WrapOption);

		Map<OdfStyleProperty, String> srcStyles = srcTableCell
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcTableCell.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcTableCell.getAutomaticStyle();
			StyleStyleElement autoStyle = tableCell
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}