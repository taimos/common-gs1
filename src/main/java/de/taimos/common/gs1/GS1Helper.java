package de.taimos.common.gs1;

import org.krysalis.barcode4j.impl.code128.EAN128Bean;

/**
 * Helper Class for GS1 numbers
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
public class GS1Helper {

	/**
	 * @param message
	 *            the GS1-128 message
	 * @return the image as byte array
	 */
	public static byte[] renderGS1_128(final String message) {
		final EAN128Bean bean = new EAN128Bean();
		bean.setModuleWidth(.3);
		return GS1Utils.renderBarcode(bean, message);
	}

}
