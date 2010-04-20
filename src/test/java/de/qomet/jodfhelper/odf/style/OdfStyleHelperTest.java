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

package de.qomet.jodfhelper.odf.style;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odftoolkit.odfdom.doc.style.OdfStyleFontFace;

import de.qomet.jodfhelper.odf.BaseBean;
import de.qomet.jodfhelper.odf.style.OdfStyleHelper.FontFace;

public class OdfStyleHelperTest extends BaseBean {

	@Test
	public void testMyFontFaceEquals() {
		OdfStyleHelper styleHelper = new OdfStyleHelper();

		OdfStyleFontFace lucidaFontFace = getLucidaFontTestData();

		FontFace fontFaceLucida1 = styleHelper.new FontFace(lucidaFontFace);
		FontFace fontFaceLucida2 = styleHelper.new FontFace(lucidaFontFace);
		FontFace fontFaceLucida3 = styleHelper.new FontFace(lucidaFontFace);

		// Reflexivity check
		assertTrue("Equals check for reflexivity faild", fontFaceLucida1
				.equals(fontFaceLucida1));

		// Symmetry check
		assertTrue("Equals check for symmetry faild", fontFaceLucida1
				.equals(fontFaceLucida2)
				&& fontFaceLucida2.equals(fontFaceLucida1));

		// Transitivity check
		assertTrue("Equals check for transitivity faild", fontFaceLucida1
				.equals(fontFaceLucida2)
				&& fontFaceLucida2.equals(fontFaceLucida3)
				&& fontFaceLucida1.equals(fontFaceLucida3));

		FontFace fontFaceArial = styleHelper.new FontFace(
				getArialFontTestData());

		assertFalse(fontFaceArial.equals(fontFaceLucida1));

		FontFace fontFaceTahomaSwiss = styleHelper.new FontFace(
				getTahomaSwissFontTestData());
		FontFace fontFaceTahomaSystem = styleHelper.new FontFace(
				getTahomaSystemFontTestData());

		assertFalse(fontFaceTahomaSwiss.equals(fontFaceTahomaSystem));
	}

	private OdfStyleFontFace getLucidaFontTestData() {
		OdfStyleFontFace odfFontFace = new OdfStyleFontFace(contentDom);
		odfFontFace.setStyleNameAttribute("Lucida Sans Unicode");
		odfFontFace.setSvgFontFamilyAttribute("'Lucida Sans Unicode'");
		odfFontFace.setStyleFontFamilyGenericAttribute("system");
		odfFontFace.setStyleFontPitchAttribute("variable");

		return odfFontFace;
	}

	private OdfStyleFontFace getArialFontTestData() {
		OdfStyleFontFace odfFontFace = new OdfStyleFontFace(contentDom);
		odfFontFace.setStyleNameAttribute("Arial");
		odfFontFace.setSvgFontFamilyAttribute("Arial");
		odfFontFace.setStyleFontFamilyGenericAttribute("swiss");
		odfFontFace.setStyleFontPitchAttribute("variable");

		return odfFontFace;
	}

	private OdfStyleFontFace getTahomaSwissFontTestData() {
		OdfStyleFontFace odfFontFace = new OdfStyleFontFace(contentDom);
		odfFontFace.setStyleNameAttribute("Tahoma");
		odfFontFace.setSvgFontFamilyAttribute("Tahoma");
		odfFontFace.setStyleFontFamilyGenericAttribute("swiss");
		odfFontFace.setStyleFontPitchAttribute("variable");

		return odfFontFace;
	}

	private OdfStyleFontFace getTahomaSystemFontTestData() {
		OdfStyleFontFace odfFontFace = new OdfStyleFontFace(contentDom);
		odfFontFace.setStyleNameAttribute("Tahoma");
		odfFontFace.setSvgFontFamilyAttribute("Tahoma");
		odfFontFace.setStyleFontFamilyGenericAttribute("system");
		odfFontFace.setStyleFontPitchAttribute("variable");

		return odfFontFace;
	}
}