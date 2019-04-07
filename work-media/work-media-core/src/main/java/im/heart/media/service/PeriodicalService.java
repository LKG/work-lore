package im.heart.media.service;

import im.heart.core.enums.Status;
import im.heart.media.entity.Periodical.PeriodicalType;
import im.heart.media.entity.Periodical;
import im.heart.core.service.CommonService;
import java.math.BigInteger;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

/**
 * 
 * @author gg
 * 材料价格表对外Service 接口
 */
public interface PeriodicalService extends CommonService<Periodical, BigInteger>{
	public static final String BEAN_NAME = "periodicalService";

	
	public List<Periodical>  findByStatusAndType(Status status, PeriodicalType type);

	public List<Periodical>  findAllById(Iterable<BigInteger> ids);
	
	public Page<Periodical> findInitPeriodicalByType(PeriodicalType type, Pageable pageable);
	public void addUpdateHitsTask(BigInteger id);

	public void updateHitsById(BigInteger id);
	public  void updateStatusByPeriodicalId(BigInteger periodicalId, Status status);

	public void updateRateTimesById(BigInteger id);
	public  void addUpdateRateTimesTask(BigInteger id);

	public void updateDownTimesById(BigInteger id);
	public void addUpdateDownTimesTask(BigInteger id);
}
