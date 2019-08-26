package im.heart.usercore.service.impl;

import com.google.common.collect.Maps;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FramePermission;
import im.heart.usercore.repository.FramePermissionRepository;
import im.heart.usercore.service.FramePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gg
 * @desc 权限表操作接口
 */
@Service(value = FramePermissionService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
@CacheConfig(cacheNames = "permissions-cache")
public class FramePermissionServiceImpl extends CommonServiceImpl<FramePermission, BigInteger> implements FramePermissionService {

	@Autowired
	private FramePermissionRepository framePermissionRepository;

	@Cacheable()
    @Override
	public List<FramePermission> findAll(Specification<FramePermission> spec) {
		return this.framePermissionRepository.findAll(spec);
	}
	@Cacheable()
	@Override
	public List<FramePermission> findAllById(Iterable<BigInteger> ids) {
		return this.framePermissionRepository.findAllById(ids);
	}

	@Cacheable()
	@Override
	public List<FramePermission> findAll() {
		return this.framePermissionRepository.findAll();
	}

	@Override
	public Map<BigInteger, String> findPermissionMap() {
		List<FramePermission> permissions=this.framePermissionRepository.findAll();
		Map<BigInteger, String> permissionMap= Maps.newHashMap();
		if(permissions!=null&&!permissions.isEmpty()){
			for(FramePermission permission:permissions){
				permissionMap.put(permission.getPermissionId(),permission.getPermissionCode());
			}
		}
		return permissionMap;
	}
}
