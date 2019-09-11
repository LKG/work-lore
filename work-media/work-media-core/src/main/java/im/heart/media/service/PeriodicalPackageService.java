package im.heart.media.service;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.CommonService;
import im.heart.media.entity.PeriodicalPackage;
import im.heart.media.enums.PackageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author gg
 * 材料打包对外Service 接口
 */
public interface PeriodicalPackageService extends CommonService<PeriodicalPackage, BigInteger>{
	public static final String BEAN_NAME = "periodicalPackageService";

	public Optional<PeriodicalPackage> findByAreaAndCodeAndType(String cityId, String packageCode, PackageType packageType);

	public Optional<PeriodicalPackage> findByPeriodConfigIdAndType(BigInteger periodconfigId, String packageCode, PackageType packageType);

	public Page<PeriodicalPackage> findSearchFilters(Collection<SearchFilter> searchFilters, Pageable pageable);


}
