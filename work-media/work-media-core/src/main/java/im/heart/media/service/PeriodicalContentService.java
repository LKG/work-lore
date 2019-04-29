package im.heart.media.service;

import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.CommonService;
import im.heart.media.entity.PeriodicalContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author gg
 * PeriodicalContent对外Service 接口
 */
public interface PeriodicalContentService extends CommonService<PeriodicalContent, BigInteger>{
	public static final String BEAN_NAME = "periodicalContentService";

	/**
	 * 添加异步线程
	 * @param periodicalContent
	 */
	public void addSaveTask(PeriodicalContent periodicalContent) ;
	/**
	 *
	 *批量保存
	 * @param entities
	 * @return
	 */
	public List<PeriodicalContent>  saveAll(Iterable<PeriodicalContent> entities);

	/**
	 * 
	 * 内容
	 * @param periodicalId
	 * @return
	 */
	public List<PeriodicalContent> findByPeriodicalId(BigInteger periodicalId);

	/**
	 * 条件查询
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Page<PeriodicalContent> findSearchFilters(Collection<SearchFilter> filters, Pageable pageable);
	
}
