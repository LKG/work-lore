package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameOrgRelate;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @Desc : 机构关联表，设置机构非直属关联关系   Service
 */
public interface   FrameOrgRelateService extends CommonService<FrameOrgRelate, BigInteger> {
	
	public static final String BEAN_NAME = "frameOrgRelateService";
	/**
	 * 
	 * 批量保存
	 * @param entities
	 * @return
	 */
	public List<FrameOrgRelate>  saveAll(Iterable<FrameOrgRelate> entities);
	/**
	 *  根据机构号查询关联信息
	 * @param orgId
	 * @return
	 */
	public List<FrameOrgRelate> findByOrgId(BigInteger orgId);
	
	public List<FrameOrgRelate> findByOrgIdAndType(BigInteger orgId, String relateType);

}
