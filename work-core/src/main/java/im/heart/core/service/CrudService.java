package im.heart.core.service;

import java.util.List;
import java.util.Optional;

/**
 * @author gg
 * @desc  共用服务Crud 接口
 * @param <T>
 * @param <ID>
 */
public interface CrudService<T, ID>  extends BaseService<T, ID> {

    /**
     * 判断是否存在
     * @param id
     * @return
     */
    public boolean existsById(ID id);

	/**
	 * 根据Id 查询
	 * @param id
	 * @return
	 */
	public T findById(ID id);

	/**
	 *  根据id 删除数据
	 * @param id
	 */
	public void deleteById(ID id);
	/**
	 *
	 *批量保存
	 * @param entities
	 * @return
	 */
	public List<T> saveAll(Iterable<T> entities);
    /**
     * 保存
     * @param entity
     * @return
     */
    public T  save(T entity);

	/**
	 *  批量删除
	 * @param entities
	 */
	public void deleteAll(Iterable<? extends T> entities);
}
