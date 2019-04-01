package im.heart.cms.service.impl;

import im.heart.cms.entity.Ad;
import im.heart.cms.service.AdService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = AdService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class AdServiceImpl extends CommonServiceImpl<Ad, BigInteger> implements AdService {
	

}
