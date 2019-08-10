package im.heart.media.packs.impl;

import java.io.File;
import java.sql.SQLException;

import im.heart.media.entity.PeriodicalPackage;
import im.heart.media.packs.PackageDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPacker<T> implements PackageDataService {
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractPacker.class);
	/**
	 * 
	 * 写入数据包信息
	 * @param dbpath
	 * @param materialPackage
	 * @throws SQLException
	 */
	protected void insertDataInfo(String dbpath, PeriodicalPackage materialPackage) throws SQLException{

	}
	public abstract File createDbFile(String templateFilePath,File file) throws Exception ;

}
