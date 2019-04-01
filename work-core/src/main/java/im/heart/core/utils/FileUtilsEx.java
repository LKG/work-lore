package im.heart.core.utils;

import im.heart.core.enums.FileHeader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * commons.io.FileUtils 扩展工具类
 * @author gg
 */
public class FileUtilsEx extends FileUtils {
	protected static final Logger logger = LoggerFactory.getLogger(FileUtilsEx.class);

	public static String getHumanReadableFileSize(Long fileSize) {
		if(fileSize == null){
			return null;
		}
		return getHumanReadableFileSize(fileSize.longValue());
	}

	public static String getHumanReadableFileSize(long fileSize) {
		if(fileSize < 0) {
			return String.valueOf(fileSize);
		}
		String result = getHumanReadableFileSize(fileSize, ONE_PB, "PB");
		if(result != null) {
			return result;
		}

		result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
		if(result != null) {
			return result;
		}
		return String.valueOf(fileSize)+"B";
	}

	private static String getHumanReadableFileSize(long fileSize, long unit, String unitName) {
		if(fileSize == 0) return "0";

		if(fileSize / unit >= 1) {
			double value = fileSize / (double)unit;
			DecimalFormat df = new DecimalFormat("######.##"+unitName);
			return df.format(value);
		}
		return null;
	}
	/**
	 * 
	 * 判断输入流是否为压缩文件
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static boolean isZIP(InputStream input) throws IOException {
		byte[] head = IOUtils.toByteArray(input,
				FileHeader.ZIP.value.length() / 2);
		String headHex = byteToHex(head);
		return headHex.equals(FileHeader.ZIP.value);
	}
	/**
	 *  获取文件大小
	 * @param filePath
	 * @return
	 */
	public static Long getFileSize(String filePath){
		File f=new File(filePath);
		return getFileSize(f);
	}
	
	/**
	 *  获取文件大小
	 * @param file
	 * @return
	 */
	public static Long getFileSize(File file){
		if(file.exists()&&file.isFile()){
			return file.length();
		}
		return new Long(0);
	}
	
	/**
	 * 
	 * 根据图片的二进制流获取文件类型
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String getImageTypeFromByteArray(byte[] data)
			throws IOException {
		InputStream is = new ByteArrayInputStream(data);
		ImageInputStream iis = ImageIO.createImageInputStream(is);
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		if (!iter.hasNext()) {
			return null;
		}

		ImageReader reader = iter.next();
		iis.close();
		String fileName = reader.getFormatName();

		return fileName;
	}

	/**
	 * 
	 * 压缩
	 * @param str
	 * @return
	 */
	public static byte[] compressToByte(byte[] str) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str);
			gzip.close();
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return out.toByteArray();
	}

	/**
	 * 
	 * gzip解压
	 * @param b
	 * @return
	 */
	public static byte[] uncompressToStringback(byte[] b) {
		if (b == null || b.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toByteArray();
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}

	/**
	 * 
	 * 判断文件是否为指定类型文件
	 * @param input
	 * @param tag
	 * @return
	 */
	public static boolean isSpecFile(byte[] input, String tag) {
		int len = tag.length() / 2;
		if (input.length < len) {
			return false;
		}
		byte[] head = new byte[len];
		System.arraycopy(input, 0, head, 0, len);
		String headHex = byteToHex(head);
		return headHex.equals(tag);
	}

	/**
	 * 
	 * Hex to byte[]
	 * @param hex
	 * @return
	 */
	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127){
				value -= 256;
			}
			raw[i] = (byte) value;
		}
		return raw;
	}

	/**
	 * 
	 * byte[] 转 Hex
	 * @param raw
	 * @return
	 */
	public static String byteToHex(final byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			final int hiVal = (b & 0xF0) >> 4;
			final int loVal = b & 0x0F;
			hex.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
			hex.append((char) ('0' + (loVal + (loVal / 10 * 7))));
		}
		return hex.toString();
	}

	/**
	 * 
	 * 创建并写入文件
	 * @param filePath
	 * @param fileName
	 * @param data
	 */
	public static void createFileOutString(String filePath, String fileName,
			String data) {
		if (createFile(filePath)) {
			FileWriter fileWriter = null;
			BufferedWriter bufferedWriter = null;
			try {
				fileWriter = new FileWriter(filePath, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(data);
				bufferedWriter.flush();
			} catch (IOException e) {
				logger.error(e.getStackTrace()[0].getMethodName(), e);
			} finally {
				IOUtils.closeQuietly(bufferedWriter);
				IOUtils.closeQuietly(fileWriter);
			}
		}
	}

	/**
	 * 
	 * 创建并写入文件
	 * @param filePath
	 * @param bits
	 * @param bits
	 */
	public static void createFileOutByte(String filePath, byte[] bits) {

		if (createFile(filePath)) {
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(filePath));
				out.write(bits);
				out.flush();
			} catch (FileNotFoundException e) {
				logger.error(e.getStackTrace()[0].getMethodName(), e);
			} catch (IOException e) {
				logger.error(e.getStackTrace()[0].getMethodName(), e);
			} finally {
				IOUtils.closeQuietly(out);
			}
		}

	}

	/**
	 * 创建单个文件
	 * 
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			logger.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			logger.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!");
				return false;
			}
		}
		try {
			if (file.createNewFile()) {
				logger.debug(descFileName + " 文件创建成功!");
				return true;
			}
			logger.debug(descFileName + " 文件创建失败!");
			return false;
		} catch (Exception e) {
			logger.error(descFileName + " 文件创建失败!" + e);
			return false;
		}

	}

	public static void writeFile(String content, String filePath) {
		BufferedWriter bufferedWriter=null;
		FileWriter fileWriter=null;
		try {
			if (createFile(filePath)) {
				fileWriter = new FileWriter(filePath, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
				fileWriter.close();
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}finally {
			IOUtils.closeQuietly(bufferedWriter);
			IOUtils.closeQuietly(fileWriter);
		}
	}
}
