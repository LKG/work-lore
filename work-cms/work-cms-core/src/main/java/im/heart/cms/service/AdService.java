package im.heart.cms.service;

import im.heart.cms.entity.Ad;
import im.heart.core.service.CommonService;

import java.math.BigInteger;

/**
 * 
 * 广告操作接口
 * @作者 LKG
 */
public interface   AdService extends CommonService<Ad, BigInteger>{
	
	public static final String BEAN_NAME = "adService";
}
