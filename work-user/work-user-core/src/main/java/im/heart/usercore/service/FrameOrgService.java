package im.heart.usercore.service;

import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameOrg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * 
 * @author gg
 * @Desc : 机构表对外Service 接口
 */
public interface FrameOrgService extends CommonService<FrameOrg, BigInteger> {
	public static final String BEAN_NAME = "frameOrgService";

	public Optional<FrameOrg> findByName(String orgName);
	
	public List<FrameOrg> findOrgsByName(String orgName);
	public boolean existsOrgCode(String orgCode);
	public Page<FrameOrg> findBySearchFilters(Collection<SearchFilter> filters, PageRequest pageRequest);
	public Page<FrameOrg> findBySpecification(Specification<FrameOrg> spec, PageRequest pageRequest);
	
}
