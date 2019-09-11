package im.heart.media.service;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.CommonService;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import im.heart.media.entity.PeriodicalImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author gg
 * PeriodicalImg对外Service 接口
 */
public interface PeriodicalImgService extends CommonService<PeriodicalImg, BigInteger>{
	public static final String BEAN_NAME = "periodicalImgService";

	/**
	 * PeriodicalImg是否存在
	 * @param periodicalCode
	 * @param pageNum
	 * @param cityId
	 * @return
	 */
	public boolean exit(String periodicalCode, Integer pageNum, String cityId);
	/**
	 * 
	 * 查看全部
	 * @param cityId
	 * @param periodicalCode
	 * @return
	 */
	public List<PeriodicalImg> findByCityIdAndPeriodicalCode(String cityId, String periodicalCode);

	/**
	 * 根据期刊Id 更新状态
	 * @param periodicalId
	 * @param status
	 */
	public  void updateStatusByPeriodicalId(BigInteger periodicalId, Status status);

	/**
	 * 条件查询
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Page<PeriodicalImg> findSearchFilters(Collection<SearchFilter> filters, Pageable pageable);
	
}
