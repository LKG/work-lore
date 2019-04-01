package im.heart.cms.service.impl;

import im.heart.cms.entity.FriendLink;
import im.heart.cms.service.FriendLinkService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = FriendLinkService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FriendLinkServiceImpl extends CommonServiceImpl<FriendLink, BigInteger> implements FriendLinkService {

	
}
