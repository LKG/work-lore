package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.entity.Order;
import im.heart.shop.entity.OrderPay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface OrderPayService extends CommonService<OrderPay, Long> {
	public static final String BEAN_NAME = "orderPayService";
}
