package im.heart.cms.service.impl;

import im.heart.cms.entity.AdPosition;
import im.heart.cms.service.AdPositionService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = AdPositionService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class AdPositionServiceImpl extends CommonServiceImpl<AdPosition, BigInteger> implements AdPositionService {
	

}
