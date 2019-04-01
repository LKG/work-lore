package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameUserRelate;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @Desc : 用户关系表    Service
 */
public interface   FrameUserRelateService extends CommonService<FrameUserRelate, BigInteger> {
	
	public static final String BEAN_NAME = "frameUserRelateService";
	/**
	 * 
	 * 批量保存
	 * @param entities
	 * @return
	 */
	public List<FrameUserRelate>  saveAll(Iterable<FrameUserRelate> entities);

	/**
	 *  根据机构号查询关联信息
	 * @param userId
	 * @return
	 */
	public List<FrameUserRelate> findByOrgId(BigInteger userId);
	
	public List<FrameUserRelate> findByOrgIdAndType(BigInteger userId, String relateType);
}
