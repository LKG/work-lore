package im.heart.cms.service;

import im.heart.cms.entity.FriendLink;
import im.heart.core.service.CommonService;

import java.math.BigInteger;

/**
 * 
 * FriendLink操作接口
 * @作者 LKG
 */
public interface   FriendLinkService extends CommonService<FriendLink, BigInteger>{
	
	public static final String BEAN_NAME = "friendLinkService";
	
}
