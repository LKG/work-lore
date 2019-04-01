package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.entity.CartItem;

import java.math.BigInteger;


public interface CartItemService extends CommonService<CartItem, BigInteger> {
	public static final String BEAN_NAME = "cartItemService";
}
