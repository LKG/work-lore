package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FrameResource;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : 资源控制接口
 */
public interface FrameResourceService extends CommonService<FrameResource, BigInteger> {

	public static final String BEAN_NAME = "frameResourceService";
	
	
	public FrameResource save(FrameResource entity);
	
	/**
	 * 
	 * 根据资源标识查找
	 * @param resourceCode
	 * @return
	 */
	public FrameResource findByResourceCode(String resourceCode);

	public boolean existsResourceCode(String resourceCode);
	
}
