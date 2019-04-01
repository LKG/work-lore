package im.heart.frame.service;

import im.heart.core.service.CommonService;
import im.heart.core.service.ServiceException;
import im.heart.frame.entity.FrameDict;
import im.heart.frame.entity.FrameDictItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author gg
 * @desc 字典对外Service 接口
 */
public interface FrameDictService extends CommonService<FrameDict, BigInteger> {
	public static final String BEAN_NAME = "frameDictService";

	/**
	 * 判断是否定义对应字典
	 * @param dictCode
	 * @return
	 */
	public boolean exists(String dictCode);

	/**
	 * 根据dictCode ，及itemCode 查询列表
	 * @param dictCode
	 * @param itemCode
	 * @return
	 */
	public List<FrameDictItem> findItemsByCode(String dictCode, String itemCode);

	/**
	 * 根据字典类型和字典代号查询数据
	 * @param dictCode
	 * @param itemCode
	 * @return
	 */
	public Optional<FrameDictItem>   findItemByCode(String dictCode, String itemCode);
	
	public Page<FrameDictItem> findItemsByCode(String dictCode, Pageable pageable);

	public void save(FrameDict frameDict, Iterable<FrameDictItem> dictItems)   throws ServiceException;
	
}
