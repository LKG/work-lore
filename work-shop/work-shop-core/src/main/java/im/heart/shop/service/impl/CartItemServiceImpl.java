package im.heart.shop.service.impl;

import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.shop.entity.CartItem;
import im.heart.shop.repository.CartItemRepository;
import im.heart.shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = CartItemService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class CartItemServiceImpl extends CommonServiceImpl<CartItem, BigInteger> implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;


}
