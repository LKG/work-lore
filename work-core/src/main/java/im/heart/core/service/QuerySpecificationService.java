package im.heart.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 
 * @author gg
 * @desc 分页查询接口
 * @param <T>
 * @param <ID>
 */
public interface QuerySpecificationService<T,ID> extends BaseService<T, ID> {

	/**
	 * 分页按条件查询
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Specification<T> spec, Pageable pageable);

	/**
	 * 不分页按条件查询
	 * @param spec
	 * @return
	 */
	public List<T> findAll(Specification<T> spec);

	/**
	 * 按条件统计
	 * @param spec
	 * @return
	 */
	public Long count(Specification<T> spec) ;
}
