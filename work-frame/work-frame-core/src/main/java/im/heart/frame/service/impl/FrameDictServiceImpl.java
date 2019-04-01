package im.heart.frame.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.ServiceException;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.frame.entity.FrameDict;
import im.heart.frame.entity.FrameDict.DictType;
import im.heart.frame.entity.FrameDictItem;
import im.heart.frame.repository.FrameDictItemRepository;
import im.heart.frame.repository.FrameDictRepository;
import im.heart.frame.service.FrameDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author gg
 * @desc 数据字典操作接口
 */
@Service(value = FrameDictService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameDictServiceImpl extends CommonServiceImpl<FrameDict, BigInteger> implements FrameDictService {
	protected static final Logger logger = LoggerFactory.getLogger(FrameDictServiceImpl.class);
	@Autowired
	private FrameDictRepository frameDictRepository;
	
	@Autowired
	private FrameDictItemRepository frameDictItemRepository;
	
	@Override
	public boolean exists(String dictCode) {
		final Collection<SearchFilter> filters =  Sets.newHashSet();
		filters.add(new SearchFilter("dictCode", Operator.EQ, dictCode));
		Specification<FrameDict> spec = DynamicSpecifications.bySearchFilter(filters, FrameDict.class);
		long count = this.frameDictRepository.count(spec);
		return count>0;
	}


	@Override
	public Optional<FrameDictItem>  findItemByCode(String dictCode, String itemCode) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("dictCode", Operator.EQ, dictCode));
		filters.add(new SearchFilter("itemCode", Operator.EQ, itemCode));
		Specification<FrameDictItem> spec = DynamicSpecifications.bySearchFilter(filters, FrameDictItem.class);
		return this.frameDictItemRepository.findOne(spec);
	}

	@Override
	public List<FrameDictItem> findItemsByCode(String dictCode, String itemCode) {
		final Collection<SearchFilter> filters =  Sets.newHashSet();
		filters.add(new SearchFilter("dictCode", Operator.EQ, dictCode));
		filters.add(new SearchFilter("itemCode", Operator.LIKES, itemCode));
		Specification<FrameDictItem> spec = DynamicSpecifications.bySearchFilter(filters, FrameDictItem.class);
		return this.frameDictItemRepository.findAll(spec);
	}
	
	 
	@Override
	public Page<FrameDictItem>  findItemsByCode(String dictCode, Pageable pageable) {
		final Collection<SearchFilter> filters = Sets.newHashSet();
		filters.add(new SearchFilter("dictCode", Operator.EQ, dictCode));
		Specification<FrameDictItem> spec = DynamicSpecifications.bySearchFilter(filters, FrameDictItem.class);
		return this.frameDictItemRepository.findAll(spec,pageable);
	}


	@Override
	public FrameDict save(FrameDict frameDict) {
		if(DictType.multiple.equals(frameDict.getDictType())){
			frameDict.setDictValue(frameDict.getDictCode());
		}
		if(frameDict.getId()==null){
			boolean exists = this.exists(frameDict.getDictCode());
			if(exists){
				logger.warn("dictCode：{} 已存在，或为空。",frameDict.getDictCode());
				throw new ConstraintViolationException("dictCode.isExit",null);
			}
		}
		return this.frameDictRepository.save(frameDict);
	}

	@Override
	public void save(FrameDict frameDict, Iterable<FrameDictItem> dictItems)   throws ServiceException {
		this.save(frameDict);
		for(FrameDictItem dictItem:dictItems){
			String dictCode = frameDict.getDictCode();
			dictItem.setDictCode(dictCode);
		}
		this.frameDictItemRepository.saveAll(dictItems);
	}

}
