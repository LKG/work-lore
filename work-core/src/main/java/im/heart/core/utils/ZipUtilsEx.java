package im.heart.core.utils;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtilsEx extends ZipUtil {
	protected static final Logger logger = LoggerFactory.getLogger(ZipUtilsEx.class);
	private static final int BUFFER = 1024 * 8;

	public static void zip(String zipFilePath,File ... files) {
		File zipFile = new File(zipFilePath);
		zip(zipFile,files);
	}
	/**
	 * 压缩文件或目录
	 * @param fromPath 待压缩文件或路径
	 * @param toPath   压缩文件，如 xx.zip
	 */
	public static void compress(String fromPath, String toPath) throws IOException {
		File fromFile = new File(fromPath);
		File toFile = new File(toPath);
		if (!fromFile.exists()) {
			throw new FileNotFoundException(fromPath + "不存在！");
		}
		try (
				FileOutputStream outputStream = new FileOutputStream(toFile);
				CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
				ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream)
		) {
			compress(fromFile, zipOutputStream, "");
		}
	}

	/**
	 * 压缩文加
	 * @param file
	 * @param zipOut
	 * @param baseDir
	 * @throws IOException
	 */
	private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
		if (file.isDirectory()) {
			compressDirectory(file, zipOut, baseDir);
		} else {
			compressFile(file, zipOut, baseDir);
		}
	}

	/**
	 * 压缩目录
	 * @param dir
	 * @param zipOut
	 * @param baseDir
	 * @throws IOException
	 */
	private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
		File[] files = dir.listFiles();
		if (files != null && ArrayUtils.isNotEmpty(files)) {
			for (File file : files) {
				compress(file, zipOut, baseDir + dir.getName() + "/");
			}
		}
	}

	/**
	 * 文件压缩
	 * @param file
	 * @param zipOut
	 * @param baseDir
	 * @throws IOException
	 */
	private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
		if (!file.exists()) {
			return;
		}
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
			ZipEntry entry = new ZipEntry(baseDir + file.getName());
			zipOut.putNextEntry(entry);
			int count;
			byte[] data = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				zipOut.write(data, 0, count);
			}
		}
	}




	public static void zip(File zipFile,File ... files) {
		ZipArchiveOutputStream zaos = null;
		try {
			zaos = new ZipArchiveOutputStream(zipFile);
			zaos.setUseZip64(Zip64Mode.AsNeeded);
			for (File file : files) {
				if(file==null){
					continue;
				}
				ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file,file.getName());
				zaos.putArchiveEntry(zipArchiveEntry);
				InputStream is = null;
				try{
					is = new FileInputStream(file);
					byte[] buffer = new byte[BUFFER];
					int len = -1;
					while((len = is.read(buffer)) != -1) {
						zaos.write(buffer, 0, len);
					}
					zaos.closeArchiveEntry(); 
				}catch(IOException e) {
					logger.error(e.getStackTrace()[0].getMethodName(), e);
				}finally{
					IOUtils.closeQuietly(is);
				}
			}
			zaos.finish();
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}finally{
			IOUtils.closeQuietly(zaos);
		}
	}
}
