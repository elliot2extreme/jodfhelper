package de.qomet.jodfhelper.odf.text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.table.OdfTableColumn;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.style.StyleStyleElement;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.dom.style.props.OdfTableColumnProperties;
import org.w3c.dom.Node;

class OdfTableColumnNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTableColumn srcNode;

	OdfTableColumnNodeHandler(OdfFileDom contentDom,
			OdfTableColumn srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTableColumn tableColumn = new OdfTableColumn(contentDom);

		if (srcNode.getTableNumberColumnsRepeatedAttribute() != null) {
			tableColumn.setTableNumberColumnsRepeatedAttribute(srcNode
					.getTableNumberColumnsRepeatedAttribute());
		}
		if (srcNode.getTableVisibilityAttribute() != null) {
			tableColumn.setTableVisibilityAttribute(srcNode
					.getTableVisibilityAttribute());
		}
		if (srcNode.getDefaultCellStyle() != null) {
			tableColumn.setDefaultCellStyle(srcNode.getDefaultCellStyle());
		}

		tableColumn.setProperties(handleTableColumnStyle(tableColumn, srcNode));

		return tableColumn;
	}

	private Map<OdfStyleProperty, String> handleTableColumnStyle(
			OdfTableColumn tableColumn, OdfTableColumn srcTableColumn) {
		Map<OdfStyleProperty, String> styles = new HashMap<OdfStyleProperty, String>();

		HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();
		properties.add(OdfTableColumnProperties.BreakAfter);
		properties.add(OdfTableColumnProperties.BreakBefore);
		properties.add(OdfTableColumnProperties.ColumnWidth);
		properties.add(OdfTableColumnProperties.RelColumnWidth);
		properties.add(OdfTableColumnProperties.UseOptimalColumnWidth);

		Map<OdfStyleProperty, String> srcStyles = srcTableColumn
				.getProperties(properties);
		Iterator<Entry<OdfStyleProperty, String>> itrSrcStyles = srcStyles
				.entrySet().iterator();

		while (itrSrcStyles.hasNext()) {
			Entry<OdfStyleProperty, String> srcStyle = itrSrcStyles.next();
			if (srcStyle.getValue() != null) {
				styles.put(srcStyle.getKey(), srcStyle.getValue());
			}
		}

		if (srcTableColumn.hasAutomaticStyle()) {
			OdfStyleBase srcAutoStyle = srcTableColumn.getAutomaticStyle();
			StyleStyleElement autoStyle = tableColumn
					.getOrCreateUnqiueAutomaticStyle();
			autoStyle.setProperties(srcAutoStyle.getStyleProperties());
		}

		return styles;
	}
}