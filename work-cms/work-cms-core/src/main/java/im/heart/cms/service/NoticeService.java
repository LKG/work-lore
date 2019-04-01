package im.heart.cms.service;

import im.heart.cms.entity.Notice;
import im.heart.core.service.CommonService;

import java.math.BigInteger;


public  interface NoticeService extends CommonService<Notice, BigInteger>{
	public static final String BEAN_NAME = "noticeService";

}
