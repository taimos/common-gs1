/**
 * 
 */
package de.taimos.common.gs1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.upcean.UPCEANLogicImpl;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 * 
 * 
 * @author hoegertn
 * @copyright Taimos GmbH 2012
 * 
 */
class GS1Utils {
	
	static String addChecksum(final String gtin) {
		// if gtin is null or not one of 12-digit or 7-digit
		if ((gtin == null) || !(gtin.matches("[0-9]{12}") || gtin.matches("[0-9]{7}"))) {
			throw new IllegalArgumentException();
		}
		return gtin + UPCEANLogicImpl.calcChecksum(gtin);
	}
	
	static byte[] renderBarcode(final AbstractBarcodeBean bean, final String message) {
		try {
			final BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvasProvider, message);
			canvasProvider.finish();
			
			final BufferedImage image = canvasProvider.getBufferedImage();
			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(image, "png", output);
			return output.toByteArray();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
}
