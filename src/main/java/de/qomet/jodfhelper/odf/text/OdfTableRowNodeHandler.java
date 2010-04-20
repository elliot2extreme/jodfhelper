package de.qomet.jodfhelper.odf.text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTableRowProperties;
import org.w3c.dom.Node;

class OdfTableRowNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTableRow srcNode;

	OdfTableRowNodeHandler(OdfFileDom contentDom, OdfTableRow srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTableRow tableRow = new OdfTableRow(contentDom);

		if (srcNode.getTableNumberRowsRepeatedAttribute() != null) {
			tableRow.setTableNumberRowsRepeatedAttribute(srcNode
					.getTableNumberRowsRepeatedAttribute());
		}
		if (srcNode.getTableVisibilityAttribute() != null) {
			tableRow.setTableVisibilityAttribute(srcNode
					.getTableVisibilityAttribute());
		}

		tableRow.setProperties(handleTableRowStyle(tableRow, srcNode));

		return tableRow;
	}

	private Map<OdfStyleProperty, String> handleTableRowStyle(
			OdfTableRow tableRow, OdfTableRow srcTableRow) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTableRowProperties.BackgroundColor);
		properties.add(OdfTableRowProperties.BreakAfter);
		properties.add(OdfTableRowProperties.BreakBefore);
		properties.add(OdfTableRowProperties.KeepTogether);
		properties.add(OdfTableRowProperties.MinRowHeight);
		properties.add(OdfTableRowProperties.RowHeight);
		properties.add(OdfTableRowProperties.UseOptimalRowHeight);

		Map<OdfStyleProperty, String> srcStyles = srcTableRow
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcTableRow.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcTableRow.getAutomaticStyle();
			StyleStyleElement autoStyle = tableRow
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}