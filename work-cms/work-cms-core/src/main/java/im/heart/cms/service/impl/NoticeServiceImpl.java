package im.heart.cms.service.impl;

import im.heart.cms.entity.Notice;
import im.heart.cms.service.NoticeService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc 公告
 */
@Service(value = NoticeService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class NoticeServiceImpl extends CommonServiceImpl<Notice, BigInteger> implements NoticeService {

}
