package im.heart.frame.service;

import im.heart.core.service.CommonService;
import im.heart.frame.entity.QQGroup;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc QQGroup操作接口
 */
public interface QQGroupService extends CommonService<QQGroup, BigInteger> {
	public static final String BEAN_NAME = "qqGroupService";
}
