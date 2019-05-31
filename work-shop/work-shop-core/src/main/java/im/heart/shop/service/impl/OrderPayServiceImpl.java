package im.heart.shop.service.impl;

import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.shop.entity.OrderPay;
import im.heart.shop.service.OrderPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = OrderPayService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor=Exception.class)
public class OrderPayServiceImpl extends CommonServiceImpl<OrderPay, Long> implements OrderPayService {

}
