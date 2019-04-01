package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.entity.OrderItem;

import java.math.BigInteger;


public interface OrderItemService extends CommonService<OrderItem, BigInteger> {
	public static final String BEAN_NAME = "orderItemService";
}
