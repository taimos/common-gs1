package de.taimos.common.gs1;

import org.krysalis.barcode4j.impl.upcean.EAN13Bean;

import com.google.common.base.Strings;

/**
 * Helper Class for Global Trade Item Numbers (GTIN)
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
public class GTINHelper {

	/**
	 * @param gln
	 *            the GLN
	 * @param id
	 *            the id part
	 * @return the complete GTIN
	 */
	public static String createGTIN(final String gln, final int id) {
		final String idPart = Strings.padStart(Integer.toString(id), (12 - gln.length()), '0');
		return GS1Utils.addChecksum(gln + idPart);
	}

	/**
	 * @param gtin
	 *            the GTIN
	 * @return the EAN13 barcode as PNG
	 */
	public static byte[] getAsEAN13(final String gtin) {
		if ((gtin != null) && !gtin.isEmpty()) {
			final EAN13Bean bean = new EAN13Bean();
			// bean.setBarHeight(15);
			return GS1Utils.renderBarcode(bean, gtin);
		}
		return new byte[0];
	}

	/**
	 * @param gtin
	 *            the GTIN
	 * @return the GS1-128 barcode as PNG
	 */
	public static byte[] getAsGS1_128(final String gtin) {
		if ((gtin != null) && !gtin.isEmpty()) {
			GS1Helper.renderGS1_128(getGS1128Code(gtin));
		}
		return new byte[0];
	}

	private static String getGS1128Code(final String gtin) {
		if (!gtin.matches("[0-9]{13,14}")) {
			return "";
		}
		// add GS1-128 identifier "01" (GTIN)
		if (gtin.length() == 13) {
			return "010" + gtin;
		}
		return "01" + gtin;
	}

	/**
	 * @param gtin
	 *            the GTIN
	 * @param type
	 *            the type of the barcode [gs1-128, gtin-13, gtin-8]
	 * @return the image as byte array
	 */
	public static byte[] getAsBytes(final String gtin, final String type) {
		byte[] image = new byte[0];
		switch (type) {
		case "gtin-13":
			image = GTINHelper.getAsEAN13(gtin);
			break;
		case "gs1-128":
			image = GTINHelper.getAsGS1_128(gtin);
			break;
		}
		return image;
	}
}
