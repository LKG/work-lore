package im.heart.media.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Sets;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import im.heart.media.entity.PeriodicalCategory;
import im.heart.media.repository.PeriodicalCategoryRepository;
import im.heart.media.service.PeriodicalCategoryService;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
/**
 * @author gg
 */
@Service(value = PeriodicalCategoryService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class PeriodicalCategoryServiceImpl   extends CommonServiceImpl<PeriodicalCategory, BigInteger> implements 	PeriodicalCategoryService {

	@Autowired
	private PeriodicalCategoryRepository periodicalCategoryRepository;

	@Override
	public List<PeriodicalCategory> saveAll(Iterable<PeriodicalCategory> entities) {
		return this.periodicalCategoryRepository.saveAll(entities);
	}

	@Override
	public List<PeriodicalCategory> findByParentId(BigInteger parentId) {
		return this.periodicalCategoryRepository.findByParentId(parentId);
	}
	@Override
	public boolean exit(String categoryCode) {
		final Collection<SearchFilter> filters =  Sets.newHashSet();
		filters.add(new SearchFilter("categoryCode", Operator.EQ, categoryCode));
		Specification<PeriodicalCategory> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalCategory.class);
		long countSign = this.periodicalCategoryRepository.count(spec);
		return countSign <= 0;
	}
	@Override
	public List<PeriodicalCategory> findByPParentIdAndLevel(String ppCode, Integer level) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("categoryCode", Operator.LIKES, ppCode));
		filters.add(new SearchFilter("level", Operator.EQ, level));
		Specification<PeriodicalCategory> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalCategory.class);
		return this.periodicalCategoryRepository.findAll(spec);
	}

	@Override
	public List<PeriodicalCategory> findByCategoryCodeStartingWith(String categoryCode) {
		// TODO Auto-generated method stub
		return this.periodicalCategoryRepository.findByCategoryCodeStartingWith(categoryCode);
	}


}
