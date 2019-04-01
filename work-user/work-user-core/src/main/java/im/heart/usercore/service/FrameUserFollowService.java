package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameUserFollow;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author gg
 * @Desc : 用户关注表 Service
 */
public interface FrameUserFollowService extends CommonService<FrameUserFollow, BigInteger> {

	public static final String BEAN_NAME = "frameUserFollowService";
	/**
	 *
	 * 批量保存
	 * @param entities
	 * @return
	 */
	public List<FrameUserFollow>  saveAll(Iterable<FrameUserFollow> entities);
	/**
	 *  根据机构号查询关联信息
	 * @param userId
	 * @return
	 */
	public List<FrameUserFollow> findByUserId(BigInteger userId);

	public List<FrameUserFollow> findByUserIdAndType(BigInteger userId, String relateType);

	public Optional<FrameUserFollow> findByUserIdAndRelateIdAndType(BigInteger userId, BigInteger relateId, String relateType);
}
