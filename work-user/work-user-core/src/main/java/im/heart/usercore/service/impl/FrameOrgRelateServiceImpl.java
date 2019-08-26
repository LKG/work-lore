package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameOrgRelate;
import im.heart.usercore.repository.FrameOrgRelateRepository;
import im.heart.usercore.service.FrameOrgRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author: gg
 * 机构关联信息表
 */
@Service(value = FrameOrgRelateService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameOrgRelateServiceImpl extends CommonServiceImpl<FrameOrgRelate, BigInteger> implements FrameOrgRelateService {
	@Autowired
	private FrameOrgRelateRepository frameOrgRelateRepository;
	@Override
	public List<FrameOrgRelate> saveAll(Iterable<FrameOrgRelate> entities) {
		return this.frameOrgRelateRepository.saveAll(entities);
	}
	@Override
	public List<FrameOrgRelate> findByOrgId(BigInteger orgId) {
		final Collection<SearchFilter> filters =  Sets.newHashSet();
		filters.add(new SearchFilter("orgId", SearchFilter.Operator.EQ, orgId));
		Specification<FrameOrgRelate> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrgRelate.class);
		return this.frameOrgRelateRepository.findAll(spec);
	}

	@Override
	public List<FrameOrgRelate> findByOrgIdAndType(BigInteger orgId, String relateType) {
		final Collection<SearchFilter> filters =  Sets.newHashSet();
		filters.add(new SearchFilter("orgId", SearchFilter.Operator.EQ, orgId));
		filters.add(new SearchFilter("relateType", SearchFilter.Operator.EQ, relateType));
		Specification<FrameOrgRelate> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrgRelate.class);
		return this.frameOrgRelateRepository.findAll(spec);
	}
}
