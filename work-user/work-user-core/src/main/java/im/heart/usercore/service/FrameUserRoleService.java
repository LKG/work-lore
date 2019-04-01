package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.core.service.ServiceException;
import im.heart.usercore.entity.FrameUserRole;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gg
 * @Desc : 用户角色接口
 */
public interface   FrameUserRoleService extends CommonService<FrameUserRole, BigInteger> {
	
	public static final String BEAN_NAME = "frameUserRoleService";
	
	
	/**
	 * 
	 * 批量保存
	 * @param entities
	 * @return
	 */
	public List<FrameUserRole>  saveAll(Iterable<FrameUserRole> entities);

	public void saveUserRole(BigInteger userId, String... roleCodes) throws ServiceException;
	
	public Map<String, BigInteger> findRoleCodeMapByUserId(BigInteger userId);
}
