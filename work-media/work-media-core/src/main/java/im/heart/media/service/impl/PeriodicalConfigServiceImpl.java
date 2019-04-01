package im.heart.media.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import im.heart.media.entity.PeriodicalConfig;
import im.heart.media.enums.PackageType;
import im.heart.media.repository.PeriodicalConfigRepository;
import im.heart.media.service.PeriodicalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
@Service(value = PeriodicalConfigService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class PeriodicalConfigServiceImpl extends CommonServiceImpl<PeriodicalConfig, BigInteger> implements PeriodicalConfigService {

	@Autowired
	private PeriodicalConfigRepository periodicalConfigRepository;


	@Override
	public List<PeriodicalConfig> saveAll(
			Iterable<PeriodicalConfig> entities) {
		return this.periodicalConfigRepository.saveAll(entities);
	}


	@Override
	public Optional<PeriodicalConfig> findBycityIdAndYearAndType(String cityId, Integer year, PackageType packageType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("type", Operator.EQ, packageType.value+""));
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("year", Operator.EQ, year));
		Specification<PeriodicalConfig> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalConfig.class);
		return this.periodicalConfigRepository.findOne(spec);
	}

	@Override
	public boolean exits(String cityId, Integer year, String periodicalType) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("type", Operator.EQ, periodicalType));
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("year", Operator.EQ, year));
		Specification<PeriodicalConfig> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalConfig.class);
		long count = this.periodicalConfigRepository.count(spec);
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	public List<PeriodicalConfig> findByCityIdStartingWithAndTypeAndYear(String cityId, String type, Integer year) {
		return this.periodicalConfigRepository.findByCityIdStartingWithAndTypeAndYear(cityId, type, year);
	}

}
