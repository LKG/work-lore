package im.heart.media.service;

import im.heart.media.entity.PeriodicalCategory;
import im.heart.core.service.CommonService;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author gg
 * 材料分类表对外Service 接口
 */
public interface PeriodicalCategoryService extends CommonService<PeriodicalCategory, BigInteger>{
	public static final String BEAN_NAME = "periodicalCategoryService";

	/**
	 *
	 * 批量保存
	 * @param entities
	 * @return
	 */
	public List<PeriodicalCategory>  saveAll(Iterable<PeriodicalCategory> entities);
	/**
	 *
	 *
	 * @param entitie
	 * @return
	 */
	public PeriodicalCategory save(PeriodicalCategory entitie);

	public  List<PeriodicalCategory>  findByParentId(BigInteger parentId);

	/**
	 * 获取分类信息
	 * @param ppCode 顶节点code
	 * @return
	 */
	public  List<PeriodicalCategory>  findByPParentIdAndLevel(String ppCode, Integer level);

	public List<PeriodicalCategory> findByCategoryCodeStartingWith(String categoryCode);

	public boolean exit(String categoryCode);
}
