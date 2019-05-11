package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.entity.Order;

import java.math.BigInteger;
import java.util.List;


public interface OrderService extends CommonService<Order, Long> {
	public static final String BEAN_NAME = "orderService";

	public Order create(OrderDto orderDto);

	public List<Order> queryUnpaid();

	public List<Order> findUnconfirmOrder();


}
