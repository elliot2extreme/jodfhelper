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

import java.util.UUID;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.draw.OdfDrawImage;
import org.odftoolkit.odfdom.pkg.OdfPackage;
import org.odftoolkit.odfdom.pkg.manifest.OdfFileEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

class OdfDrawImageNodeHandler implements INodeHandler {

	private final OdfFileDom contentDom;
	private final OdfDrawImage srcNode;

	OdfDrawImageNodeHandler(OdfFileDom contentDom, final OdfDrawImage srcNode) {
		this.contentDom = contentDom;
		this.srcNode = srcNode;
	}

	@Override
	public Node handleNode() throws Exception {
		Document srcNodeDoc = srcNode.getOwnerDocument();

		if (srcNodeDoc instanceof OdfFileDom) {
			OdfFileDom srcFileDom = (OdfFileDom) srcNodeDoc;

			OdfDocument srcDoc = srcFileDom.getOdfDocument();
			OdfPackage pkgSource = srcDoc.getPackage();

			String srcPath = srcNode.getImageUri().getPath();

			if (pkgSource.contains(srcPath)) {
				OdfFileEntry entry = pkgSource.getFileEntry(srcPath);

				OdfDrawImage image = new OdfDrawImage(contentDom);

				String imgExt = srcPath.substring(srcPath.lastIndexOf("."));

				UUID id = UUID.randomUUID();
				String destPath = "Pictures/" + id + imgExt;

				image.newImage(pkgSource.getInputStream(srcPath), destPath,
						entry.getMediaType());

				return image;
			}
		}

		return null;
	}
}