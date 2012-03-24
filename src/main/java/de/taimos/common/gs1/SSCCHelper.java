package de.taimos.common.gs1;

import com.google.common.base.Strings;

/**
 * Helper Class for Serial Shipping Container Codes (SSCC)
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
public class SSCCHelper {

	/**
	 * @param gln
	 *            the GLN
	 * @param id
	 *            the id part
	 * @return the complete SSCC
	 */
	public static String createSSCC(final String gln, final int id) {
		return SSCCHelper.createSSCC("0", gln, id);
	}

	/**
	 * @param extensionDigit
	 *            the SSCC extension digit
	 * @param gln
	 *            the GLN
	 * @param id
	 *            the id part
	 * @return the complete SSCC
	 */
	public static String createSSCC(final String extensionDigit, final String gln, final int id) {
		if ((extensionDigit == null) || !extensionDigit.matches("[0-9]")) {
			throw new IllegalArgumentException("Invalid extension digit");
		}
		if ((gln == null) || !gln.matches("[0-9]{7,9}")) {
			throw new IllegalArgumentException("Invalid gln");
		}
		final String idPart = Strings.padStart(Integer.toString(id), (16 - gln.length()), '0');
		return GS1Utils.addChecksum(extensionDigit + gln + idPart);
	}

	/**
	 * @param sscc
	 *            the SSCC
	 * @return the GS1-128 barcode as PNG
	 */
	public static byte[] getAsGS1_128(final String sscc) {
		if ((sscc != null) && !sscc.isEmpty() && sscc.matches("[0-9]{18}")) {
			// add GS1-128 identifier "00" (SSCC)
			GS1Helper.renderGS1_128("00" + sscc);
		}
		return new byte[0];
	}

}
