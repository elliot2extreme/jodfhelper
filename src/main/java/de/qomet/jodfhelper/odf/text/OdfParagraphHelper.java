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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;

class OdfParagraphHelper {

	private static final HashSet<OdfStyleProperty> properties = new HashSet<OdfStyleProperty>();

	static {
		properties.add(OdfParagraphProperties.AutoTextIndent);
		properties.add(OdfParagraphProperties.BackgroundColor);
		properties.add(OdfParagraphProperties.BackgroundTransparency);
		properties.add(OdfParagraphProperties.Border);
		properties.add(OdfParagraphProperties.BorderBottom);
		properties.add(OdfParagraphProperties.BorderLeft);
		properties.add(OdfParagraphProperties.BorderLineWidth);
		properties.add(OdfParagraphProperties.BorderLineWidthBottom);
		properties.add(OdfParagraphProperties.BorderLineWidthLeft);
		properties.add(OdfParagraphProperties.BorderLineWidthRight);
		properties.add(OdfParagraphProperties.BorderLineWidthTop);
		properties.add(OdfParagraphProperties.BorderRight);
		properties.add(OdfParagraphProperties.BorderTop);
		properties.add(OdfParagraphProperties.BreakAfter);
		properties.add(OdfParagraphProperties.BreakBefore);
		properties.add(OdfParagraphProperties.FontIndependentLineSpacing);
		properties.add(OdfParagraphProperties.HyphenationKeep);
		properties.add(OdfParagraphProperties.HyphenationLadderCount);
		properties.add(OdfParagraphProperties.JoinBorder);
		properties.add(OdfParagraphProperties.JustifySingleWord);
		properties.add(OdfParagraphProperties.KeepTogether);
		properties.add(OdfParagraphProperties.KeepWithNext);
		properties.add(OdfParagraphProperties.LineBreak);
		properties.add(OdfParagraphProperties.LineHeight);
		properties.add(OdfParagraphProperties.LineHeightAtLeast);
		properties.add(OdfParagraphProperties.LineNumber);
		properties.add(OdfParagraphProperties.LineSpacing);
		properties.add(OdfParagraphProperties.Margin);
		properties.add(OdfParagraphProperties.MarginBottom);
		properties.add(OdfParagraphProperties.MarginLeft);
		properties.add(OdfParagraphProperties.MarginRight);
		properties.add(OdfParagraphProperties.MarginTop);
		properties.add(OdfParagraphProperties.NumberLines);
		properties.add(OdfParagraphProperties.Orphans);
		properties.add(OdfParagraphProperties.Padding);
		properties.add(OdfParagraphProperties.PaddingBottom);
		properties.add(OdfParagraphProperties.PaddingLeft);
		properties.add(OdfParagraphProperties.PaddingRight);
		properties.add(OdfParagraphProperties.PaddingTop);
		properties.add(OdfParagraphProperties.PageNumber);
		properties.add(OdfParagraphProperties.PunctuationWrap);
		properties.add(OdfParagraphProperties.RegisterTrue);
		properties.add(OdfParagraphProperties.StyleShadow);
		properties.add(OdfParagraphProperties.SnapToLayoutGrid);
		properties.add(OdfParagraphProperties.TabStopDistance);
		properties.add(OdfParagraphProperties.TextAlign);
		properties.add(OdfParagraphProperties.TextAlignLast);
		properties.add(OdfParagraphProperties.TextAutospace);
		properties.add(OdfParagraphProperties.TextIndent);
		properties.add(OdfParagraphProperties.VerticalAlign);
		properties.add(OdfParagraphProperties.Widows);
		properties.add(OdfParagraphProperties.WritingMode);
		properties.add(OdfParagraphProperties.WritingModeAutomatic);
	};

	static Set<OdfStyleProperty> getAllOdfParagraphProperties() {
		return Collections.unmodifiableSet(properties);
	}
}