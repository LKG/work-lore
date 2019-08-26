package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameUserRelate;
import im.heart.usercore.repository.FrameUserRelateRepository;
import im.heart.usercore.service.FrameUserRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author gg
 * @Desc : 用户关联信息表 Service
 */
@Service(value = FrameUserRelateService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameUserRelateServiceImpl extends CommonServiceImpl<FrameUserRelate, BigInteger> implements FrameUserRelateService{
	@Autowired
	private FrameUserRelateRepository frameUserRelateRepository;
	@Override
	public List<FrameUserRelate> saveAll(Iterable<FrameUserRelate> entities) {
		return this.frameUserRelateRepository.saveAll(entities);
	}

	@Override
	public List<FrameUserRelate> findByOrgId(BigInteger userId) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		Specification<FrameUserRelate> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserRelate.class);
		return this.frameUserRelateRepository.findAll(spec);
	}

	@Override
	public List<FrameUserRelate> findByOrgIdAndType(BigInteger userId, String relateType) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("relateType", SearchFilter.Operator.EQ, relateType));
		Specification<FrameUserRelate> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserRelate.class);
		return this.frameUserRelateRepository.findAll(spec);
	}
}
