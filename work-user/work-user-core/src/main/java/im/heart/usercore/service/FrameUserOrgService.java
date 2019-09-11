package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameUserOrg;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @Desc : 用户机构关联接口
 */
public interface   FrameUserOrgService extends CommonService<FrameUserOrg, BigInteger> {
	
	public static final String BEAN_NAME = "frameUserOrgService";


	public void setDefaultOrgById(BigInteger relateId);

	public void setDefaultOrg(BigInteger userId, BigInteger relateId);

	public boolean existsUserOrg(BigInteger userId);
	
	public List<FrameUserOrg> findByUserId(BigInteger userId);
	
	public List<FrameUserOrg> findByOrgId(BigInteger orgId);
}
