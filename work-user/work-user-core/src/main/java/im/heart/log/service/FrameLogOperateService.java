package im.heart.log.service;

import im.heart.core.service.CommonService;
import im.heart.log.entity.FrameLogOperate;

import java.math.BigInteger;

public interface FrameLogOperateService extends CommonService<FrameLogOperate, BigInteger> {
	public static final String BEAN_NAME = "frameLogOperateService";
	public void optlog(FrameLogOperate frameLogOperate);
}
