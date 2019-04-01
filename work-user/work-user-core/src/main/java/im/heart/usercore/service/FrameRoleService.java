package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameRole;
import org.springframework.data.domain.Sort;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author gg
 * @Desc : 角色接口
 */
public interface   FrameRoleService extends CommonService<FrameRole, BigInteger> {
	
	public static final String BEAN_NAME = "frameRoleService";
	
	
	public List<FrameRole> findAll(Sort sort);
	/**
	 * 
	 *
	 * @param roleCode
	 * @return
	 */
	public FrameRole findByRoleCode(String roleCode);
	
	/**
	 * 
	 * 根据角色Code获取角色权限 标示
	 * @param roleCodes
	 * @return
	 */
	public Set<String> findRolePermissionsByCode(Iterable<String> roleCodes);

	/**
	 *
	 * 根据角色ID获取角色权限 标示
	 * @param roleIds
	 * @return
	 */
	public Set<String> findRolePermissionsById(Iterable<BigInteger> roleIds);
	/**
	 * 
	 * 获取资源Code
	 * @param roleCodes
	 * @return
	 */
	public Set<String> findRoleResourceCodes(Iterable<String> roleCodes);
	/**
	 * 
	 * 获取资源Id
	 * @param roleCodes
	 * @return
	 */
	public Set<BigInteger> findRoleResourceIds(Iterable<String> roleCodes);
	
	public FrameRole save(FrameRole frameRole);
	public boolean existsRoleCode(String roleCode);
}
