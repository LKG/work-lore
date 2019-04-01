package im.heart.core.utils;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ZipUtilsEx extends ZipUtil {
	protected static final Logger logger = LoggerFactory.getLogger(ZipUtilsEx.class);
	public static void zip(String zipFilePath,File ... files) {
		File zipFile = new File(zipFilePath);
		zip(zipFile,files);
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
					byte[] buffer = new byte[1024 * 5]; 
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
