package im.heart.frame.service.impl;

import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.frame.entity.FrameArea;
import im.heart.frame.repository.FrameAreaRepository;
import im.heart.frame.service.FrameAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author gg
 * @desc 省份地区表操作接口
 */
@Service(value = FrameAreaService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
@CacheConfig(cacheNames = FrameAreaServiceImpl.CACHE_NAMES)
public class FrameAreaServiceImpl extends CommonServiceImpl<FrameArea, String> implements FrameAreaService {

	public static final String CACHE_NAMES = "areas-cache";
	@Autowired
	private FrameAreaRepository frameAreaRepository;

	@Override
	public List<FrameArea> save(Iterable<FrameArea> entities) {
		return this.frameAreaRepository.saveAll(entities);
	}
	@Cacheable(value=CACHE_NAMES, key="#id")
	@Override
	public FrameArea findById(String id) {
		return this.frameAreaRepository.findById(id).get();
	}
    /**
     *  保存
     * @param entity
     * @return
     */
    @Override
	@CacheEvict(value=CACHE_NAMES, allEntries=true)
	public FrameArea save(FrameArea entity) {
		return this.frameAreaRepository.save(entity);
	}

	@Override
	@Cacheable(value=CACHE_NAMES, key="#areaName")
	public List<FrameArea> findAreasByName(String areaName) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("name", Operator.LIKES, areaName));
		Specification<FrameArea> spec = DynamicSpecifications.bySearchFilter(filters, FrameArea.class);
		List<FrameArea> list = this.frameAreaRepository.findAll(spec);
		return list;
	}
	@Cacheable(value=CACHE_NAMES)
	@Override
	public Page<FrameArea> findBySearchFilters(Collection<SearchFilter> filters, PageRequest pageRequest) {
		if(filters==null||filters.isEmpty()){
			return null;
		}
		Specification<FrameArea> spec = DynamicSpecifications.bySearchFilter(filters, FrameArea.class);
		return this.frameAreaRepository.findAll(spec,pageRequest);
	}
	@Cacheable(value=CACHE_NAMES)
	@Override
	public Page<FrameArea> findBySpecification(Specification<FrameArea> spec , PageRequest pageRequest) {
		return this.frameAreaRepository.findAll(spec,pageRequest);
	}
	@CachePut(value=CACHE_NAMES, key="#id")
	@Override
	public void deleteById(String id) {
		this.frameAreaRepository.deleteById(id);
	}
}
