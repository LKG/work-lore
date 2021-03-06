package im.heart.core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 *
 * DES加密解密工具类
 * @author gg
 */
public class DesUtils {
	/**
	 * 
	 * base64加密
	 * @param str
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static String createSign(String str, String sign) throws Exception {
		byte[] bytes = createSignBack(str,sign);
		String base64 = Base64.encodeBase64String(bytes);
		return base64;

	}

	/**
	 * 
	 *  base64解密
	 * @param entry
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static String parseSign(String entry, String sign) throws Exception {
		byte[] bytes = Base64.decodeBase64(entry);
		return parseSignBack(bytes,sign);
	}

	/**
	 * 
	 * base64解密
	 * @param entry
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static String parseSignBack(byte[] entry, String sign) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(sign.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		byte[] ret = cipher.doFinal(entry);
		String str = new String(ret, "UTF-8");
		return str;
	}

	/**
	 * 
	 * base64加密
	 * @param str
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static byte[] createSignBack(String str, String sign) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(sign.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		byte[] bytes = cipher.doFinal(str.getBytes());
		return bytes;

	}
}
