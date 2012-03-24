package de.taimos.common.gs1;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Helper Class for Global Location Numbers (GLN)
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
public class GLNHelper {

	/**
	 * @param gln
	 *            the Global Location Number
	 * @return the list of possible GTIN
	 */
	public static List<String> createGTINList(final String gln) {
		if ((gln == null) || !gln.matches("[0-9]{7,9}")) {
			throw new IllegalArgumentException();
		}

		final List<String> list = Lists.newArrayList();

		// length of article id
		final int len = 12 - gln.length();
		final int count = (int) Math.pow(10, len);

		for (int i = 0; i < count; i++) {
			list.add(GTINHelper.createGTIN(gln, i));
		}
		return list;
	}

}
