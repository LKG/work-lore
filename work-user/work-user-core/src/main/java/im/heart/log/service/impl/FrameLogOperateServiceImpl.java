package im.heart.log.service.impl;

import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.log.entity.FrameLogOperate;
import im.heart.log.repository.FrameLogOperateRepository;
import im.heart.log.service.FrameLogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = FrameLogOperateService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameLogOperateServiceImpl extends CommonServiceImpl<FrameLogOperate, BigInteger> implements FrameLogOperateService {

	@Autowired
	private FrameLogOperateRepository frameLogOperateRepository;
	
	@Async
	@Override
	public void optLog(FrameLogOperate entity) {
		this.frameLogOperateRepository.save(entity);
	}
	
	
}
