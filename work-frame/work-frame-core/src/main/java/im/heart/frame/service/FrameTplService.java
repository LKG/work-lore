package im.heart.frame.service;

import im.heart.core.service.CommonService;
import im.heart.frame.entity.FrameTpl;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc 模板对外Service 接口
 */
public interface FrameTplService extends CommonService<FrameTpl, BigInteger> {
	public static final String BEAN_NAME = "frameTplService";
	public FrameTpl save(FrameTpl frameTpl);
	
	/**
	 * @Desc：判断是否定义模板
	 * @param tplCode
	 * @return
	 */
	public boolean exists(String tplCode);
}
