package de.qomet.jodfhelper.odf.text;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.style.OdfStyleListLevelLabelAlignment;
import org.odftoolkit.odfdom.doc.style.OdfStyleListLevelProperties;
import org.odftoolkit.odfdom.doc.text.OdfTextList;
import org.odftoolkit.odfdom.doc.text.OdfTextListLevelStyleBullet;
import org.odftoolkit.odfdom.doc.text.OdfTextListStyle;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class OdfListNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfTextList srcNode;

	OdfListNodeHandler(OdfFileDom contentDom,
			final OdfTextList srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		OdfTextList list = new OdfTextList(contentDom);

		if (srcNode.getTextContinueNumberingAttribute() != null) {
			list.setTextContinueNumberingAttribute(srcNode
					.getTextContinueNumberingAttribute());
		}

		OdfTextListStyle listStyle = handleListStyle(list, srcNode);
		list.setTextStyleNameAttribute(listStyle.getStyleNameAttribute());

		return list;
	}

	private OdfTextListStyle handleListStyle(OdfTextList list,
			OdfTextList srcList) {
		OdfTextListStyle srcListStyle = srcList.getListStyle();

		if (srcListStyle == null) {
			return null;
		}

		OdfTextListStyle listStyle = list.getOrCreateLocalListStyle();

		if (srcListStyle.hasChildNodes()) {
			NodeList childs = srcListStyle.getChildNodes();

			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);

				if (child instanceof OdfTextListLevelStyleBullet) {
					OdfTextListLevelStyleBullet bullet = handleListLevelStyleBullet((OdfTextListLevelStyleBullet) child);
					listStyle.appendChild(bullet);
				}
			}
		}

		return listStyle;
	}

	private OdfTextListLevelStyleBullet handleListLevelStyleBullet(
			OdfTextListLevelStyleBullet srcListLevelStyleBullet) {
		OdfTextListLevelStyleBullet listLevelStyleBullet = new OdfTextListLevelStyleBullet(
				contentDom);

		listLevelStyleBullet.setStyleNumPrefixAttribute(srcListLevelStyleBullet
				.getStyleNumPrefixAttribute());
		listLevelStyleBullet.setStyleNumSuffixAttribute(srcListLevelStyleBullet
				.getStyleNumSuffixAttribute());

		listLevelStyleBullet.setTextBulletCharAttribute(srcListLevelStyleBullet
				.getTextBulletCharAttribute());
		listLevelStyleBullet
				.setTextBulletRelativeSizeAttribute(srcListLevelStyleBullet
						.getTextBulletRelativeSizeAttribute());

		listLevelStyleBullet.setTextStyleNameAttribute(srcListLevelStyleBullet
				.getTextStyleNameAttribute());

		listLevelStyleBullet.setTextLevelAttribute(srcListLevelStyleBullet
				.getTextLevelAttribute());

		if (srcListLevelStyleBullet.hasChildNodes()) {
			NodeList childs = srcListLevelStyleBullet.getChildNodes();

			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);

				if (child instanceof OdfStyleListLevelProperties) {
					OdfStyleListLevelProperties properties = handleListLevelProperties((OdfStyleListLevelProperties) child);
					listLevelStyleBullet.appendChild(properties);
				}
			}
		}

		return listLevelStyleBullet;
	}

	private OdfStyleListLevelProperties handleListLevelProperties(
			OdfStyleListLevelProperties srcListLevelProperties) {

		OdfStyleListLevelProperties listLevelProperties = new OdfStyleListLevelProperties(
				contentDom);

		if (srcListLevelProperties.getFoHeightAttribute() != null)
			listLevelProperties.setFoHeightAttribute(srcListLevelProperties
					.getFoHeightAttribute());
		if (srcListLevelProperties.getFoTextAlignAttribute() != null)
			listLevelProperties.setFoTextAlignAttribute(srcListLevelProperties
					.getFoTextAlignAttribute());
		if (srcListLevelProperties.getFoWidthAttribute() != null)
			listLevelProperties.setFoWidthAttribute(srcListLevelProperties
					.getFoWidthAttribute());
		if (srcListLevelProperties.getStyleFontNameAttribute() != null)
			listLevelProperties
					.setStyleFontNameAttribute(srcListLevelProperties
							.getStyleFontNameAttribute());
		if (srcListLevelProperties.getStyleVerticalPosAttribute() != null)
			listLevelProperties
					.setStyleVerticalPosAttribute(srcListLevelProperties
							.getStyleVerticalPosAttribute());
		if (srcListLevelProperties.getStyleVerticalRelAttribute() != null)
			listLevelProperties
					.setStyleVerticalRelAttribute(srcListLevelProperties
							.getStyleVerticalRelAttribute());
		if (srcListLevelProperties.getSvgYAttribute() != null)
			listLevelProperties.setSvgYAttribute(srcListLevelProperties
					.getSvgYAttribute());
		if (srcListLevelProperties
				.getTextListLevelPositionAndSpaceModeAttribute() != null)
			listLevelProperties
					.setTextListLevelPositionAndSpaceModeAttribute(srcListLevelProperties
							.getTextListLevelPositionAndSpaceModeAttribute());
		if (srcListLevelProperties.getTextMinLabelDistanceAttribute() != null)
			listLevelProperties
					.setTextMinLabelDistanceAttribute(srcListLevelProperties
							.getTextMinLabelDistanceAttribute());
		if (srcListLevelProperties.getTextMinLabelWidthAttribute() != null)
			listLevelProperties
					.setTextMinLabelWidthAttribute(srcListLevelProperties
							.getTextMinLabelWidthAttribute());
		if (srcListLevelProperties.getTextSpaceBeforeAttribute() != null)
			listLevelProperties
					.setTextSpaceBeforeAttribute(srcListLevelProperties
							.getTextSpaceBeforeAttribute());

		if (srcListLevelProperties.hasChildNodes()) {
			NodeList childs = srcListLevelProperties.getChildNodes();

			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);

				if (child instanceof OdfStyleListLevelLabelAlignment) {
					OdfStyleListLevelLabelAlignment labelAlignment = handleLabelAlignment((OdfStyleListLevelLabelAlignment) child);
					listLevelProperties.appendChild(labelAlignment);
				}
			}
		}

		return listLevelProperties;
	}

	private OdfStyleListLevelLabelAlignment handleLabelAlignment(
			OdfStyleListLevelLabelAlignment srcLabelAlignment) {
		OdfStyleListLevelLabelAlignment labelAlignment = new OdfStyleListLevelLabelAlignment(
				contentDom);

		if (srcLabelAlignment.getFoMarginLeftAttribute() != null)
			labelAlignment.setFoMarginLeftAttribute(srcLabelAlignment
					.getFoMarginLeftAttribute());
		if (srcLabelAlignment.getFoTextIndentAttribute() != null)
			labelAlignment.setFoTextIndentAttribute(srcLabelAlignment
					.getFoTextIndentAttribute());
		if (srcLabelAlignment.getTextLabelFollowedByAttribute() != null)
			labelAlignment.setTextLabelFollowedByAttribute(srcLabelAlignment
					.getTextLabelFollowedByAttribute());
		if (srcLabelAlignment.getTextListTabStopPositionAttribute() != null)
			labelAlignment
					.setTextListTabStopPositionAttribute(srcLabelAlignment
							.getTextListTabStopPositionAttribute());

		return labelAlignment;
	}
}