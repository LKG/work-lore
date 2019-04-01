package im.heart.media.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.entity.PeriodicalImg;
import im.heart.media.repository.PeriodicalImgRepository;
import im.heart.media.service.PeriodicalImgService;
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
 * PeriodicalImg对外Service 接口实现类
 */
@Service(value = PeriodicalImgService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class PeriodicalImgServiceImpl  extends CommonServiceImpl<PeriodicalImg, BigInteger> implements PeriodicalImgService {

	@Autowired
	private PeriodicalImgRepository periodicalImgRepository;

	@Override
	public List<PeriodicalImg> saveAll(Iterable<PeriodicalImg> entities) {
		return this.periodicalImgRepository.saveAll(entities);
	}

	@Override
	public boolean exit(String periodicalCode, Integer pageNum, String cityId) {
		final Collection<SearchFilter> filters  = Sets.newHashSet();
		filters.add(new SearchFilter("pageNum", Operator.EQ, pageNum));
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("periodicalCode", Operator.EQ, periodicalCode));
		Specification<PeriodicalImg> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalImg.class);
		long countSign = this.periodicalImgRepository.count(spec);
		return countSign <= 0;
	}

	@Override
	public List<PeriodicalImg> findByCityIdAndPeriodicalCode(
			String cityId, String periodicalCode) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("cityId", Operator.EQ, cityId));
		filters.add(new SearchFilter("periodicalCode", Operator.EQ, periodicalCode));
		Specification<PeriodicalImg> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalImg.class);
		return this.periodicalImgRepository.findAll(spec);
	}

	@Override
	public void updateStatusByPeriodicalId(BigInteger periodicalId,
			Status status) {
		this.periodicalImgRepository.updateStatusByPeriodicalId(periodicalId,status);
	}

	@Override
	public Page<PeriodicalImg> findSearchFilters(
			Collection<SearchFilter> filters, Pageable pageable) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<PeriodicalImg> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalImg.class);
		return this.periodicalImgRepository.findAll(spec,pageable);

	}
}
