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

import org.junit.Test;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;

public class OdfParagraphHelperTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testOdfParagraphSetForImmutability() {
		OdfParagraphHelper.getAllOdfParagraphProperties().remove(
				OdfParagraphProperties.WritingModeAutomatic);
	}
}