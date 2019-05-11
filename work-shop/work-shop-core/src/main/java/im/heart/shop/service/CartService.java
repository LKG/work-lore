package im.heart.shop.service;

import im.heart.core.service.CommonService;
import im.heart.shop.entity.Cart;

import java.math.BigInteger;


public interface CartService extends CommonService<Cart, Long> {
	public static final String BEAN_NAME = "cartService";
}
