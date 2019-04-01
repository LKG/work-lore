package im.heart.media.service;


import im.heart.core.service.CommonService;
import im.heart.media.entity.PeriodicalConfig;
import im.heart.media.enums.PackageType;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author gg
 * 期刊配置表对外Service 接口
 */
public interface PeriodicalConfigService extends CommonService<PeriodicalConfig, BigInteger> {
	public static final String BEAN_NAME = "periodicalConfigService";
	/**
	 *
	 * @功能说明：批量保存
	 * @param entities
	 * @return
	 */
	public List<PeriodicalConfig>  saveAll(Iterable<PeriodicalConfig> entities);
	
	public boolean exits(String cityId, Integer year, String periodicalType);
	
	public Optional<PeriodicalConfig> findBycityIdAndYearAndType(String cityId, Integer year, PackageType periodicalType);
	public List<PeriodicalConfig> findByCityIdStartingWithAndTypeAndYear(String cityId, String type, Integer year);

}
