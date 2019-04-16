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

	/**
	 *  添加更新点击量异步线程
	 * @param id
	 */
	public void addUpdateHitsTask(BigInteger id);

	/**
	 *  更新点击量
	 * @param id
	 */
	public void updateHitsById(BigInteger id);

	/**
	 * 更新状态
	 * @param periodicalId
	 * @param status
	 */
	public  void updateStatusByPeriodicalId(BigInteger periodicalId, Status status);

	/**
	 * 更新评论次数
	 * @param id
	 */
	public void updateRateTimesById(BigInteger id);
	/**
	 * 添加更新评论次数异步线程
	 * @param id
	 */
	public  void addUpdateRateTimesTask(BigInteger id);

	/**
	 * 更新下载次数异步线程
	 * @param id
	 */
	public void updateDownTimesById(BigInteger id);

	/**
	 * 添加更新下载次数异步线程
	 * @param id
	 */
	public void addUpdateDownTimesTask(BigInteger id);
}
