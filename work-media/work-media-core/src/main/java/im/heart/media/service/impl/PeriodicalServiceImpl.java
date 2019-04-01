package im.heart.media.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Sets;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.repository.PeriodicalImgRepository;
import im.heart.media.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import im.heart.media.entity.Periodical;
import im.heart.media.entity.Periodical.PeriodicalType;
import im.heart.media.repository.PeriodicalRepository;
import im.heart.core.service.ServiceException;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;

@Service(value = PeriodicalService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class PeriodicalServiceImpl   extends CommonServiceImpl<Periodical, BigInteger> implements PeriodicalService {
	@Autowired
	private PeriodicalRepository periodicalRepository;

	@Autowired
	private PeriodicalImgRepository periodicalImgRepository;

	@Override
	public List<Periodical> findAllById(Iterable<BigInteger>  ids ) {
		return this.periodicalRepository.findAllById(ids);
	}

	@Override
	public void deleteById(BigInteger id)  throws ServiceException{
		this.periodicalRepository.deleteById(id);
		this.periodicalImgRepository.deleteByPeriodicalId(id);
	}
	@Override
	public List<Periodical> findByStatusAndType(Status status, PeriodicalType type) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("status", Operator.EQ, status));
		filters.add(new SearchFilter("periodicalType", Operator.EQ, type.value+""));
		Specification<Periodical> spec = DynamicSpecifications.bySearchFilter(filters, Periodical.class);
		return this.periodicalRepository.findAll(spec);
	}
	@Override
	public void updateHitsById(BigInteger id) {
		this.periodicalRepository.updateHitsById(id);
	}
	@Async
	@Override
	public void addUpdateHitsTask(BigInteger id) {
		this.updateHitsById(id);
	}

	@Override
	public void updateRateTimesById(BigInteger id) {
		this.periodicalRepository.updateRateTimesById(id);
	}
	@Async
	@Override
	public void addUpdateRateTimesTask(BigInteger id) {
		this.updateRateTimesById(id);
	}


	@Override
	public Page<Periodical> findInitPeriodicalByType(PeriodicalType type,Pageable pageable) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("status", Operator.EQ, CommonConst.FlowStatus.initial));
		filters.add(new SearchFilter("periodicalType", Operator.EQ, type.value+""));
		Specification<Periodical> spec = DynamicSpecifications.bySearchFilter(filters, Periodical.class);
		return this.periodicalRepository.findAll(spec,pageable);
	}
	@Override
	public  void updateStatusByPeriodicalId(BigInteger periodicalId,Status status){
		this.periodicalRepository.updateStatusByPeriodicalId(periodicalId,status);
	};
}
