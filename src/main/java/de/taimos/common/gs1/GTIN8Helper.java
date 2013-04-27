package de.taimos.common.gs1;

import org.krysalis.barcode4j.impl.upcean.EAN8Bean;

import com.google.common.base.Strings;

/**
 * Helper Class for GTIN-8 numbers
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
public class GTIN8Helper {
	
	/**
	 * @param prefix the GTIN8 prefix
	 * @param id the id part
	 * @return the complete GTIN
	 */
	public static String createGTIN8(final String prefix, final int id) {
		final String idPart = Strings.padStart(Integer.toString(id), (7 - prefix.length()), '0');
		return GS1Utils.addChecksum(prefix + idPart);
	}
	
	/**
	 * @param gtin the GTIN
	 * @return the GTIN8 barcode as PNG
	 */
	public static byte[] getAsGTIN8(final String gtin) {
		if ((gtin != null) && !gtin.isEmpty()) {
			final EAN8Bean bean = new EAN8Bean();
			return GS1Utils.renderBarcode(bean, gtin);
		}
		return new byte[0];
	}
	
}
