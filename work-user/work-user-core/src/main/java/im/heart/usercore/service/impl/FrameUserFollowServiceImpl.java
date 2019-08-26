package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameUserFollow;
import im.heart.usercore.repository.FrameUserFollowRepository;
import im.heart.usercore.service.FrameUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gg
 * @Desc : 用户关注信息表 Service
 */
@Service(value = FrameUserFollowService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameUserFollowServiceImpl extends CommonServiceImpl<FrameUserFollow, BigInteger> implements FrameUserFollowService {
	@Autowired
	private FrameUserFollowRepository frameUserFollowRepository;


	@Override
	public List<FrameUserFollow> saveAll(Iterable<FrameUserFollow> entities) {
		return this.frameUserFollowRepository.saveAll(entities);
	}

	@Override
	public List<FrameUserFollow> findByUserId(BigInteger userId) {
		return this.findByUserIdAndType(userId,null);
	}

	@Override
	public List<FrameUserFollow> findByUserIdAndType(BigInteger userId, String relateType) {
		final Collection<SearchFilter> filters =   Sets.newHashSet();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("type", SearchFilter.Operator.EQ, relateType));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findAll(spec);
	}
	@Override
	public Optional<FrameUserFollow> findByUserIdAndRelateIdAndType(BigInteger userId, BigInteger relateId, String relateType) {
		final Collection<SearchFilter> filters =   Sets.newHashSet();
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
		filters.add(new SearchFilter("relateId", SearchFilter.Operator.EQ, relateId));
		filters.add(new SearchFilter("type", SearchFilter.Operator.EQ, relateType));
		Specification<FrameUserFollow> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		return this.frameUserFollowRepository.findOne(spec);
	}


}
