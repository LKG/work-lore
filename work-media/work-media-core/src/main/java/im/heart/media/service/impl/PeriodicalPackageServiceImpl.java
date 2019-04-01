package im.heart.media.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import im.heart.media.entity.PeriodicalPackage;
import im.heart.media.enums.PackageType;
import im.heart.media.repository.PeriodicalPackageRepository;
import im.heart.media.service.PeriodicalPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;


@Service(value = PeriodicalPackageService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class PeriodicalPackageServiceImpl extends CommonServiceImpl<PeriodicalPackage, BigInteger> implements PeriodicalPackageService {
	@Autowired
	private PeriodicalPackageRepository periodicalPackageRepository;

	@Override
	public List<PeriodicalPackage> saveAll(
			Iterable<PeriodicalPackage> entities) {
		return this.periodicalPackageRepository.saveAll(entities);
	}
	@Override
	public Optional<PeriodicalPackage> findByAreaAndCodeAndType(String cityId,
				String packageCode, PackageType packageType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("packageType", Operator.EQ, packageType.value+""));
		filters.add(new SearchFilter("packageCode", Operator.EQ, packageCode));
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		Specification<PeriodicalPackage> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalPackage.class);
		return this.periodicalPackageRepository.findOne(spec);
	}
	@Override
	public Optional<PeriodicalPackage> findByPeriodConfigIdAndType(BigInteger periodConfigId,String packageCode, PackageType packageType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("packageType", Operator.EQ, packageType.value+""));
		filters.add(new SearchFilter("packageCode", Operator.EQ, packageCode));
		filters.add(new SearchFilter("periodConfigId", Operator.EQ, periodConfigId));
		Specification<PeriodicalPackage> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalPackage.class);
		return this.periodicalPackageRepository.findOne(spec);
	}

	@Override
	public Page<PeriodicalPackage> findSearchFilters(
			Collection<SearchFilter> filters, Pageable pageable) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<PeriodicalPackage> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalPackage.class);
		return this.periodicalPackageRepository.findAll(spec,pageable);
	}

}
