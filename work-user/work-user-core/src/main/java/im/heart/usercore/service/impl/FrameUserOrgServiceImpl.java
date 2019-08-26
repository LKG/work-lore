package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameUserOrg;
import im.heart.usercore.repository.FrameUserOrgRepository;
import im.heart.usercore.repository.FrameUserRepository;
import im.heart.usercore.service.FrameUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
 * @Desc : 用户关联结构信息表 Service
 */
@Service(value = FrameUserOrgService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameUserOrgServiceImpl extends CommonServiceImpl<FrameUserOrg, BigInteger> implements FrameUserOrgService {

	@Autowired
	private FrameUserOrgRepository frameUserOrgRepository;
	@Autowired
	private FrameUserRepository frameUserRepository;
	@Override
	public List<FrameUserOrg> saveAll(Iterable<FrameUserOrg> entities) {
		return this.frameUserOrgRepository.saveAll(entities);
	}
	@Override
	public void deleteById(BigInteger id) {
		Optional<FrameUserOrg> optional= this.frameUserOrgRepository.findById(id);
		if(optional.isPresent()){
			FrameUserOrg usrOrg=optional.get();
			if(usrOrg.getIsDefault()){
				this.frameUserRepository.updateUserDefaultOrg(usrOrg.getUserId(), null);
			}
			this.frameUserOrgRepository.deleteById(id);
		}
	}
	@Override
	public List<FrameUserOrg> findByOrgId(BigInteger orgId) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("orgId", Operator.EQ, orgId));
		Specification<FrameUserOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserOrg.class);
		return this.frameUserOrgRepository.findAll(spec);
	}
	
	
	@Override
	public List<FrameUserOrg> findByUserId(BigInteger userId) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("userId", Operator.EQ, userId));
		Specification<FrameUserOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserOrg.class);
		 Sort sort= new Sort(Sort.Direction.DESC,"isDefault");
		return this.frameUserOrgRepository.findAll(spec, sort);
	}

	@Override
	public boolean existsUserOrg(BigInteger userId) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("userId", Operator.EQ, userId));
		Specification<FrameUserOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserOrg.class);
		long count = this.frameUserOrgRepository.count(spec);
		return count>0;
	}
	@Override
	public void setDefaultOrgById(BigInteger relateId) {
		Optional<FrameUserOrg> optional= this.frameUserOrgRepository.findById(relateId);
		if(optional.isPresent()){
			FrameUserOrg frameUserOrg=optional.get();
			this.frameUserOrgRepository.updateUserDefaultOrg(frameUserOrg.getUserId(),relateId);
			this.frameUserRepository.updateUserDefaultOrg(frameUserOrg.getUserId(), frameUserOrg.getRelateOrg().getId());
		}
	}
	@Override
	public void setDefaultOrg(BigInteger userId, BigInteger relateId) {
		Optional<FrameUserOrg> optional= this.frameUserOrgRepository.findById(relateId);
		if(optional.isPresent()){
			FrameUserOrg frameUserOrg=optional.get();
			this.frameUserOrgRepository.updateUserDefaultOrg(userId,relateId);
			this.frameUserRepository.updateUserDefaultOrg(userId, frameUserOrg.getRelateOrg().getId());
		}
	}
}
