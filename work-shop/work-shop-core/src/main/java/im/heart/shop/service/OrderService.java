package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;
import java.util.List;


public interface OrderService extends CommonService<Order, Long> {
	public static final String BEAN_NAME = "orderService";

	/**
	 * 创建订单
	 * @param orderDto
	 * @return
	 */
	public Order create(OrderDto orderDto);
	public Page<Order> findAllUnpaid(Specification<Order> spec, Pageable pageable);
	public Page<Order> findAllUnConfirm(Specification<Order> spec, Pageable pageable);
}
