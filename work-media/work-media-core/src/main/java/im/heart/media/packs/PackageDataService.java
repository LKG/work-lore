package im.heart.media.packs;

import im.heart.media.entity.PeriodicalPackage;

public interface PackageDataService {
	
	public static final String BEAN_SUFFIX = "Packer";
	/**
	 * 
	 * @功能说明：打包信息价
	 * @param materialPackage
	 * @return
	 */
	public PeriodicalPackage addPackageData(PeriodicalPackage materialPackage);
	/**
	 * 
	 * @功能说明：打包信息价异步处理
	 * @param materialPackage
	 * @return
	 */
	public PeriodicalPackage addPackageDataTask(PeriodicalPackage materialPackage) ;

}
