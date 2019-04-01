package im.heart.core.service.impl;

import im.heart.core.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author gg
 * @desc  共用服务实现类
 * @param <T>
 * @param <ID>
 */
public class CommonServiceImpl<T, ID extends Serializable> implements 	CommonService<T, ID> {
	@Autowired
	private JpaSpecificationExecutor<T> jpaSpecificationExecutor;
	@Autowired
	private JpaRepository<T, ID> jpaRepository;

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return this.jpaSpecificationExecutor.findAll(spec,pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return this.jpaSpecificationExecutor.findAll(spec);
	}

	@Override
	public Long count(Specification<T> spec) {
		return this.jpaSpecificationExecutor.count(spec);
	}

	@Override
	public T findById(ID id) {
		return this.jpaRepository.findById(id).get();
	}
	@Override
	public boolean existsById(ID id) {
		return this.jpaRepository.existsById(id);
	}

	@Override
	public T  save(T entity) {
		return this.jpaRepository.save(entity);
	}

	@Override
	public void deleteById(ID id) {
		this.jpaRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		this.jpaRepository.deleteAll(entities);
	}

}
