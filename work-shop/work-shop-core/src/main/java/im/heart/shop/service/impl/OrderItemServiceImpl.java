package im.heart.shop.service.impl;

import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.shop.entity.OrderItem;
import im.heart.shop.repository.OrderItemRepository;
import im.heart.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = OrderItemService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderItemServiceImpl extends CommonServiceImpl<OrderItem, Long> implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
}
