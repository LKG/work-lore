package im.heart.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class DigestUtilsEx extends DigestUtils {
	protected static final Logger logger = LoggerFactory.getLogger(DigestUtilsEx.class);

	public static String md5FileDigestAsHex(File file) {
		return md5FileDigestAsHex(file,true);
	}
	
	public static String md5FileDigestAsHex(File file,boolean isUpperCase) {
		Preconditions.checkArgument(file.isFile(), "MD5文件不能为空");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			MessageDigest messageDigest = updateDigest(getMd5Digest(), fis);
			String md5 = new String(Hex.encodeHex(messageDigest.digest()));
			if(isUpperCase){
				md5=md5.toUpperCase();
			}
			return md5;
		} catch (FileNotFoundException e) {
			logger.error("md5 file {}, failed: {}" , file.getAbsolutePath() , e.getMessage());
		} catch (IOException e) {
			logger.error("md5 file {}, failed: {}" , file.getAbsolutePath() , e.getMessage());
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("md5 file {}, failed: {}" , file.getAbsolutePath() , e.getMessage());
					fis=null;
				}
			}
		}
		return null;
	}
}
