package im.heart.media.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.entity.PeriodicalContent;
import im.heart.media.repository.PeriodicalContentRepository;
import im.heart.media.service.PeriodicalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author gg
 * PeriodicalContent对外Service 接口实现类
 */
@Service(value = PeriodicalContentService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class PeriodicalContentServiceImpl extends CommonServiceImpl<PeriodicalContent, BigInteger> implements PeriodicalContentService {

	@Autowired
	private PeriodicalContentRepository periodicalContentRepository;

	@Override
	public void addSaveTask(PeriodicalContent periodicalContent){
		this.periodicalContentRepository.save(periodicalContent);
	}
	@Override
	public List<PeriodicalContent> saveAll(Iterable<PeriodicalContent> entities) {
		return this.periodicalContentRepository.saveAll(entities);
	}

	@Override
	public boolean exit(String periodicalCode, Integer pageNum, String cityId) {
		final Collection<SearchFilter> filters  = Sets.newHashSet();
		filters.add(new SearchFilter("pageNum", Operator.EQ, pageNum));
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("periodicalCode", Operator.EQ, periodicalCode));
		Specification<PeriodicalContent> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalContent.class);
		long countSign = this.periodicalContentRepository.count(spec);
		return countSign <= 0;
	}

	@Override
	public List<PeriodicalContent> findByCityIdAndPeriodicalCode(
			String cityId, String periodicalCode) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("periodicalCode", Operator.EQ, periodicalCode));
		Specification<PeriodicalContent> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalContent.class);
		return this.periodicalContentRepository.findAll(spec);
	}

	@Override
	public Page<PeriodicalContent> findSearchFilters(
			Collection<SearchFilter> filters, Pageable pageable) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<PeriodicalContent> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalContent.class);
		return this.periodicalContentRepository.findAll(spec,pageable);

	}
}
