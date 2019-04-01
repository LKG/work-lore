package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameOrg;
import im.heart.usercore.repository.FrameOrgRepository;
import im.heart.usercore.service.FrameOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service(value = FrameOrgService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class FrameOrgServiceImpl extends CommonServiceImpl<FrameOrg, BigInteger> implements FrameOrgService {

	@Autowired
	private FrameOrgRepository frameOrgRepository;


	@Override
	public List<FrameOrg> saveAll(Iterable<FrameOrg> entities) {
		return this.frameOrgRepository.saveAll(entities);
	}

	@Override
	public Optional<FrameOrg>  findByName(String orgName) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("orgName", Operator.LIKES, orgName));
		Specification<FrameOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrg.class);
		return this.frameOrgRepository.findOne(spec);
	}
	@Override
	public Page<FrameOrg> findBySearchFilters(Collection<SearchFilter> filters, PageRequest pageRequest) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<FrameOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrg.class);
		return this.frameOrgRepository.findAll(spec,pageRequest);
	}
	@Override
	public List<FrameOrg> findOrgsByName(String orgName) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("orgName", Operator.LIKES, orgName));
		Specification<FrameOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrg.class);
		List<FrameOrg> list = this.frameOrgRepository.findAll(spec);
		return list;
	}
	@Override
	public Page<FrameOrg> findBySpecification(Specification<FrameOrg> spec , PageRequest pageRequest) {
		return this.frameOrgRepository.findAll(spec,pageRequest);
	}
	@Override
	public boolean existsOrgCode(String orgCode) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("orgCode", Operator.EQ, orgCode));
		Specification<FrameOrg> spec = DynamicSpecifications.bySearchFilter(filters, FrameOrg.class);
		long count = this.frameOrgRepository.count(spec);
		return count>0;
	}
}
