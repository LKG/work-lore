package im.heart.core.plugins.qr;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;


/**
 * 
 * @作者 LKG 
 * @功能说明：生成二维码接口 依赖google.zxing
 */
public interface QRCodeService {
	public static final String BEAN_NAME = "qRCodeService";
	/**
	 * 
	 * @功能说明：生成指定大小二维码
	 * @param contents
	 * @return
	 */
	public BufferedImage generateQRcodeImage(String contents);
	
	/**
	 * 
	 * @功能说明：生成二维码
	 * @param contents
	 * @param width
	 * @param height
	 * @param margin
	 * @param errorCorrectionLevel
	 * @param Imgurl
	 * @return
	 */
	public BufferedImage generateQRcodeImage(String contents, int width, int height, int margin, ErrorCorrectionLevel errorCorrectionLevel, String Imgurl);
	 
}
