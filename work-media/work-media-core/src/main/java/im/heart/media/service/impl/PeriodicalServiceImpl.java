package im.heart.media.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Sets;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.entity.PeriodicalContent;
import im.heart.media.entity.QPeriodical;
import im.heart.media.repository.PeriodicalContentRepository;
import im.heart.media.repository.PeriodicalImgRepository;
import im.heart.media.repository.PeriodicalLogRepository;
import im.heart.media.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	private PeriodicalContentRepository periodicalContentRepository;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	@Autowired
	private PeriodicalImgRepository periodicalImgRepository;
	@Autowired
	private PeriodicalLogRepository periodicalLogRepository;



	@Override
	public List<Periodical> findAllById(Iterable<BigInteger>  ids ) {
		return this.periodicalRepository.findAllById(ids);
	}

	@Override
	public List<Periodical> findAll(Predicate predicate, long limit){
		QPeriodical qPeriodical= QPeriodical.periodical;
		JPAQuery<Periodical> jpaQuery = this.jpaQueryFactory.select(qPeriodical)
				.from(qPeriodical).where(predicate)
				.limit(limit);
		return jpaQuery.fetch();
	}
	@Override
	public List<Periodical> findAll(Predicate predicate,long limit, OrderSpecifier<?>... orders){
		QPeriodical qPeriodical= QPeriodical.periodical;
		JPAQuery<Periodical> jpaQuery = this.jpaQueryFactory.select(qPeriodical)
				.from(qPeriodical).where(predicate).orderBy(orders)
				.limit(limit);
		return jpaQuery.fetch();
	}
	@Override
	public void deleteById(BigInteger id)  throws ServiceException{
		this.periodicalRepository.deleteById(id);
		this.periodicalLogRepository.deleteByPeriodicalId(id);
		this.periodicalContentRepository.deleteByPeriodicalId(id);
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
	public void updateDownTimesById(BigInteger id) {
		this.periodicalRepository.updateRateTimesById(id);
	}
	@Async
	@Override
	public void addUpdateDownTimesTask(BigInteger id) {
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
