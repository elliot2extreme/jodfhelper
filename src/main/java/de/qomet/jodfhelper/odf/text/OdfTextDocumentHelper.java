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

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeText;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.qomet.jodfhelper.odf.IOdfDocumentHelper;
import de.qomet.jodfhelper.odf.style.OdfStyleHelper;

public class OdfTextDocumentHelper implements IOdfDocumentHelper {

	private OdfTextDocument doc;
	private OdfFileDom contentDom;
	private NodeHandlerFactory nodeHandlerFactory;

	public OdfTextDocumentHelper(OdfTextDocument doc) {
		try {
			this.doc = doc;
			this.contentDom = doc.getContentDom();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addContent(final OdfDocument srcDoc) throws Exception {
		if (!(srcDoc instanceof OdfTextDocument)) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		XPath xPath = doc.getXPath();
		Node parent = (Node) xPath.evaluate("//office:text", contentDom,
				XPathConstants.NODE);

		addContent(srcDoc, parent, null);
	}

	@Override
	public void addContent(final OdfDocument srcDoc, Node parent,
			Node nextSibling) throws Exception {
		if (!(srcDoc instanceof OdfTextDocument)) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		nodeHandlerFactory = new NodeHandlerFactory(contentDom);
		contentDom.setErrorChecking(false);
		srcDoc.getContentDom().setErrorChecking(false);

		OdfStyleHelper styleHelper = new OdfStyleHelper();
		styleHelper.addFontFacesIfNotPresent(doc, srcDoc);

		ArrayList<Node> elementList = buildElementList(srcDoc);

		addElementList(elementList, parent, nextSibling);
	}

	private void addElementList(ArrayList<Node> elementList, Node parent,
			Node nextSibling) throws Exception {
		for (Node element : elementList) {
			addElement(parent, nextSibling, element);
		}
	}

	private void addElement(Node parent, Node where, Node element) {
		if (where == null) {
			parent.appendChild(element);
		} else {
			parent.insertBefore(element, where);
		}
	}

	private ArrayList<Node> buildElementList(final OdfDocument srcDoc)
			throws Exception {
		ArrayList<Node> elementList = new ArrayList<Node>();

		XPath srcXPath = srcDoc.getXPath();
		OdfFileDom srcContentDom = srcDoc.getContentDom();

		Node srcElement = (Node) srcXPath.evaluate("//office:text[1]",
				srcContentDom, XPathConstants.NODE);

		buildElement(elementList, null, srcElement);

		return elementList;
	}

	private void buildElement(ArrayList<Node> elementList, Node parent,
			Node srcElement) throws Exception {
		if (srcElement instanceof OdfTextDocument
				|| srcElement instanceof OdfOfficeText) {
			if (srcElement.hasChildNodes()) {
				NodeList childElements = srcElement.getChildNodes();

				for (int i = 0; i < childElements.getLength(); i++) {
					Node childElement = childElements.item(i);
					buildElement(elementList, parent, childElement);
				}
			}
		} else {
			Node destElement = nodeHandlerFactory.getNodeHandler(srcElement)
					.handleNode();
			if (destElement != null) {
				if (parent != null) {
					parent.appendChild(destElement);
				} else {
					elementList.add(destElement);
				}

				if (srcElement.hasChildNodes()) {
					NodeList childElements = srcElement.getChildNodes();

					for (int i = 0; i < childElements.getLength(); i++) {
						Node childElement = childElements.item(i);
						buildElement(elementList, destElement, childElement);
					}
				}
			}
		}
	}

	@Override
	public OdfDocument getOdfDocument() {
		return doc;
	}

	@Override
	public OdfFileDom getOdfContentDom() {
		return contentDom;
	}
}