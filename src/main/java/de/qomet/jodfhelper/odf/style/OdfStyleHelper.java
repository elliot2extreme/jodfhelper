package de.qomet.jodfhelper.odf.style;

import java.util.HashSet;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeFontFaceDecls;
import org.odftoolkit.odfdom.doc.style.OdfStyleFontFace;
import org.odftoolkit.odfdom.dom.element.style.StyleFontFaceElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OdfStyleHelper {

	public void addFontFacesIfNotPresent(OdfDocument doc,
			final OdfDocument srcDoc) throws Exception {
		addFontFacesStyles(doc, srcDoc);
		addFontFacesContent(doc, srcDoc);
	}

	public void addFontFacesStyles(OdfDocument doc, final OdfDocument srcDoc)
			throws Exception {
		HashSet<FontFace> fontFaceSet = new HashSet<FontFace>();

		XPath xPath = doc.getXPath();
		OdfFileDom stylesDom = doc.getStylesDom();
		appendFontFaceSet(fontFaceSet, xPath, stylesDom);

		XPath srcXPath = srcDoc.getXPath();
		OdfFileDom srcStylesDom = srcDoc.getStylesDom();
		appendFontFaceSet(fontFaceSet, srcXPath, srcStylesDom);

		addFontFaces(xPath, stylesDom, fontFaceSet);
	}

	public void addFontFacesContent(OdfDocument doc, final OdfDocument srcDoc)
			throws Exception {
		HashSet<FontFace> fontFaceSet = new HashSet<FontFace>();

		XPath xPath = doc.getXPath();
		OdfFileDom contentDom = doc.getContentDom();
		appendFontFaceSet(fontFaceSet, xPath, contentDom);

		XPath srcXPath = srcDoc.getXPath();
		OdfFileDom srcContentDom = srcDoc.getContentDom();
		appendFontFaceSet(fontFaceSet, srcXPath, srcContentDom);

		addFontFaces(xPath, contentDom, fontFaceSet);
	}

	private void addFontFaces(XPath xPath, OdfFileDom fileDom,
			HashSet<FontFace> fontFaceSet) throws Exception {
		Node fontFaceDecls = (Node) xPath.evaluate("//office:font-face-decls",
				fileDom, XPathConstants.NODE);

		if (fontFaceDecls instanceof OdfOfficeFontFaceDecls) {
			NodeList childs = fontFaceDecls.getChildNodes();

			for (int i = 0; i < childs.getLength(); i++) {
				fontFaceDecls.removeChild(childs.item(i));
			}

			for (FontFace myFontFace : fontFaceSet) {
				OdfStyleFontFace fontFace = new OdfStyleFontFace(fileDom);

				fontFace.setStyleNameAttribute(myFontFace.styleName);
				fontFace.setSvgFontFamilyAttribute(myFontFace.svgFontFamily);
				if (myFontFace.styleFontFamilyGeneric != null) {
					fontFace
							.setStyleFontFamilyGenericAttribute(myFontFace.styleFontFamilyGeneric);
				}
				if (myFontFace.styleFontPitch != null) {
					fontFace
							.setStyleFontPitchAttribute(myFontFace.styleFontPitch);
				}

				fontFaceDecls.appendChild(fontFace);
			}
		}
	}

	private void appendFontFaceSet(HashSet<FontFace> fontFaceSet, XPath xPath,
			OdfFileDom fileDom) throws Exception {
		Node fontFaceDecls = (Node) xPath.evaluate("//office:font-face-decls",
				fileDom, XPathConstants.NODE);

		if (fontFaceDecls instanceof StyleFontFaceElement
				|| fontFaceDecls instanceof OdfOfficeFontFaceDecls) {
			handleFontFaces(fontFaceSet, fontFaceDecls);
		}
	}

	private void handleFontFaces(HashSet<FontFace> fontFaceSet,
			Node fontFaceDecls) {
		if (fontFaceDecls.hasChildNodes()) {
			NodeList childElements = fontFaceDecls.getChildNodes();

			for (int i = 0; i < childElements.getLength(); i++) {
				Node childElement = childElements.item(i);

				if (childElement instanceof StyleFontFaceElement) {
					StyleFontFaceElement sffe = (StyleFontFaceElement) childElement;
					fontFaceSet.add(new FontFace(sffe));
				}
			}
		}
	}

	class FontFace {

		final String styleName;
		final String svgFontFamily;
		final String styleFontFamilyGeneric;
		final String styleFontPitch;

		FontFace(StyleFontFaceElement odfFontFace) {
			this.styleName = odfFontFace.getStyleNameAttribute();
			this.svgFontFamily = odfFontFace.getSvgFontFamilyAttribute();
			this.styleFontFamilyGeneric = odfFontFace
					.getStyleFontFamilyGenericAttribute();
			this.styleFontPitch = odfFontFace.getStyleFontPitchAttribute();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (!(obj instanceof FontFace))
				return false;

			// Nun steht fest das Obj vom Typ FontFace ist und man kann
			// es casten.
			FontFace ff = (FontFace) obj;

			if (!(styleName == ff.styleName || (styleName != null && styleName
					.equals(ff.styleName))))
				return false;

			if (!(svgFontFamily == ff.svgFontFamily || (svgFontFamily != null && svgFontFamily
					.equals(ff.svgFontFamily))))
				return false;

			if (!(styleFontFamilyGeneric == ff.styleFontFamilyGeneric || (styleFontFamilyGeneric != null && styleFontFamilyGeneric
					.equals(ff.styleFontFamilyGeneric))))
				return false;

			if (!(styleFontPitch == ff.styleFontPitch || (styleFontPitch != null && styleFontPitch
					.equals(ff.styleFontPitch))))
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result
					+ ((styleName == null) ? 0 : styleName.hashCode());
			result = 31 * result
					+ ((svgFontFamily == null) ? 0 : svgFontFamily.hashCode());
			result = 31
					* result
					+ ((styleFontFamilyGeneric == null) ? 0
							: styleFontFamilyGeneric.hashCode());
			result = 31
					* result
					+ ((styleFontPitch == null) ? 0 : styleFontPitch.hashCode());

			return result;
		}
	}
}