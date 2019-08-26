package im.heart.usercore.service.impl;

import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.core.utils.StringUtilsEx;
import im.heart.usercore.entity.FrameResource;
import im.heart.usercore.repository.FrameResourceRepository;
import im.heart.usercore.service.FrameResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author: gg
 * 资源信息表
 */
@Service(value = FrameResourceService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameResourceServiceImpl  extends CommonServiceImpl<FrameResource, BigInteger> implements FrameResourceService {
	protected static final Logger logger = LoggerFactory.getLogger(FrameResourceServiceImpl.class);
	@Autowired
	private FrameResourceRepository frameResourceRepository;
	

	@Override
	public FrameResource findByResourceCode(String resourceCode) {
		return this.frameResourceRepository.findByResourceCode(resourceCode);
	}

	@Override
	public FrameResource save(FrameResource entity) {
		if(entity.getResourceId()==null){
			String resourceCode=entity.getResourceCode();
			if(StringUtilsEx.isBlank(resourceCode)||this.existsResourceCode(resourceCode)){
				logger.warn("resourceCode：{} 已存在，或为空。",resourceCode);
				throw new ConstraintViolationException("resourceCode.isExit",null);
			};
		}
		return this.frameResourceRepository.save(entity);
	}
	@Override
	public boolean existsResourceCode(String resourceCode) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("resourceCode", Operator.EQ, resourceCode));
		Specification<FrameResource> spec = DynamicSpecifications.bySearchFilter(filters, FrameResource.class);
		long count = this.frameResourceRepository.count(spec);
		if(count>0){
			return true;
		}
		return false;
	}
}
