package im.heart.shop.service.impl;

import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.shop.entity.Cart;
import im.heart.shop.repository.CartRepository;
import im.heart.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = CartService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class CartServiceImpl extends CommonServiceImpl<Cart, BigInteger> implements CartService {

	@Autowired
	private CartRepository cartRepository;


}
