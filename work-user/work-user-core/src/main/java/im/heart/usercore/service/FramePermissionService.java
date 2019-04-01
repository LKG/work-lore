package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FramePermission;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gg
 * @Desc : FramePermission接口
 */
public interface   FramePermissionService extends CommonService<FramePermission, BigInteger> {
	
	public static final String BEAN_NAME = "framePermissionService";
	public List<FramePermission> findAllById(Iterable<BigInteger> ids);
	public List<FramePermission> findAll();

	public Map<BigInteger,String> findPermissionMap();
}
