package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.core.utils.StringUtilsEx;
import im.heart.usercore.entity.FrameRole;
import im.heart.usercore.entity.FrameRoleResource;
import im.heart.usercore.repository.FrameRoleRepository;
import im.heart.usercore.service.FramePermissionService;
import im.heart.usercore.service.FrameRoleResourceService;
import im.heart.usercore.service.FrameRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author: gg
 * 角色信息表
 */
@Service(value = FrameRoleService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameRoleServiceImpl extends CommonServiceImpl<FrameRole, BigInteger> implements FrameRoleService {
	protected static final Logger logger = LoggerFactory.getLogger(FrameRoleServiceImpl.class);
	@Autowired
	private FrameRoleRepository frameRoleRepository;

	@Autowired
	private FrameRoleResourceService frameRoleResourceService;

	@Autowired
	private FramePermissionService framePermissionService;

	@Override
	public List<FrameRole> findAll(Sort sort) {
		return this.frameRoleRepository.findAll(sort);
	}
	@Override
	public Set<String> findRolePermissionsByCode(Iterable<String> roleCodes) {
		List<FrameRoleResource> roleResources=this.frameRoleResourceService.findByRoleCodes(roleCodes);
		Map<BigInteger,String> framePermissions = this.framePermissionService.findPermissionMap();
		return buildPermissions(roleResources,framePermissions);
	}

	Set<String> buildPermissions(List<FrameRoleResource> roleResources,Map<BigInteger,String> framePermissions){
		Set<String> permissions=new HashSet<String>();
		for(FrameRoleResource roleResource: roleResources){
			Set<BigInteger> ids = roleResource.getPermissions();
			String resourceCode=roleResource.getResourceCode();
			if(ids!=null&&!ids.isEmpty()){
				for(BigInteger id:ids){
					if(framePermissions.containsKey(id)){
						String permissionCode=framePermissions.get(id);
						permissions.add(resourceCode+":"+permissionCode);
					}
				}
			}
		}
		return permissions;
	}


	@Override
	public Set<String> findRolePermissionsById(Iterable<BigInteger> roleIds) {
		List<FrameRoleResource> roleResources=this.frameRoleResourceService.findByRoleIds(roleIds);
		Map<BigInteger,String> framePermissions = this.framePermissionService.findPermissionMap();
		return buildPermissions(roleResources,framePermissions);
	}
	@Override
	public Set<String> findRoleResourceCodes(Iterable<String> roleCodes) {
		Set<String> resources= Sets.newHashSet();
		List<FrameRoleResource> roleResources=this.frameRoleResourceService.findByRoleCodes(roleCodes);
		for(FrameRoleResource roleResource: roleResources){
			resources.add(roleResource.getResourceCode());
		}
		return resources;
	}
	@Override
	public Set<BigInteger> findRoleResourceIds(Iterable<String> roleCodes) {
		Set<BigInteger> resourceIds= Sets.newHashSet();
		List<FrameRoleResource> roleResources=this.frameRoleResourceService.findByRoleCodes(roleCodes);
		for(FrameRoleResource roleResource: roleResources){
			resourceIds.add(roleResource.getResourceId());
		}
		return resourceIds;
	}

	@Override
	public FrameRole save(FrameRole entity) {
		if(entity.getRoleId()==null){
			String roleCode=entity.getRoleCode();
			if(StringUtilsEx.isBlank(roleCode)||this.existsRoleCode(roleCode)){
				logger.warn("roleCode：{} 已存在，或为空。",roleCode);
				throw new ConstraintViolationException("resourceCode.isExit",null);
			};
		}
		return this.frameRoleRepository.save(entity);
	}

	@Override
	public FrameRole findByRoleCode(String roleCode) {
		return this.frameRoleRepository.findByRoleCode(roleCode);
	}
	@Override
	public boolean existsRoleCode(String roleCode) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("roleCode", Operator.EQ, roleCode));
		Specification<FrameRole> spec = DynamicSpecifications.bySearchFilter(filters, FrameRole.class);
		long count = this.frameRoleRepository.count(spec);
		return count>0;
	}

}
