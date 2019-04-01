package im.heart.media.service.impl;

import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.entity.PeriodicalLog;
import im.heart.media.repository.PeriodicalLogRepository;
import im.heart.media.service.PeriodicalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
/**
 *
 * @author gg
 * PeriodicalLogService对外Service 接口实现类
 */
@Service(value = PeriodicalLogService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class PeriodicalLogServiceImpl extends CommonServiceImpl<PeriodicalLog, BigInteger> implements PeriodicalLogService {

	@Autowired
	private PeriodicalLogRepository periodicalLogRepository;
	@Async
	@Override
	public void addSaveTask(PeriodicalLog periodicalLog){
		this.periodicalLogRepository.save(periodicalLog);
	}
	@Override
	public List<PeriodicalLog> saveAll(Iterable<PeriodicalLog> entities) {
		return this.periodicalLogRepository.saveAll(entities);
	}


	@Override
	public Page<PeriodicalLog> findSearchFilters(
			Collection<SearchFilter> filters, Pageable pageable) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<PeriodicalLog> spec = DynamicSpecifications.bySearchFilter(filters, PeriodicalLog.class);
		return this.periodicalLogRepository.findAll(spec,pageable);

	}
}
