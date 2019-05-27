package im.heart.usercore.service;


import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameUserConnect;
import im.heart.usercore.enums.IdentityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author gg
 * @desc : FrameUserConnect
 */
public interface FrameUserConnectService extends CommonService<FrameUserConnect, BigInteger> {
	
	public static final String BEAN_NAME = "frameUserConnectService";

	/**
	 * 查询用户绑定记录
	 * @param openId
	 * @param identityType
	 * @return
	 */
	public Optional<FrameUserConnect> findByOpenIdAndType(String openId, IdentityType identityType);
	/**
	 * 查询用户管理账户
	 * @param userId
	 * @param identityType
	 * @return
	 */
	public Page<FrameUserConnect> findAllByUserIdAndType(BigInteger userId, IdentityType identityType, Pageable pageable);

	/**
	 * 查询用户管理账户
	 * @param userId
	 * @param identityType
	 * @return
	 */
	public List<FrameUserConnect> findAllByUserIdAndType(BigInteger userId, IdentityType identityType);
}
