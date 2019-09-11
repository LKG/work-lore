package im.heart.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.dto.OrderItemDto;
import im.heart.shop.entity.Order;
import im.heart.shop.entity.OrderItem;
import im.heart.shop.repository.OrderItemRepository;
import im.heart.shop.repository.OrderRepository;
import im.heart.shop.service.OrderService;
import im.heart.shop.utils.OrderHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service(value = OrderService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor=Exception.class)
public class OrderServiceImpl extends CommonServiceImpl<Order, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PeriodicalService periodicalService;
	@Autowired
	private OrderItemRepository orderItemRepository;

    /**
     * 查询所有未支付订单
     * @param spec
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> findAllUnpaid(Specification<Order> spec, Pageable pageable){
        return this.orderRepository.findAll(spec,pageable);
    };

    /**
     * 查询所有未确认订单
     * @param spec
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> findAllUnConfirm(Specification<Order> spec, Pageable pageable){
        return this.orderRepository.findAll(spec,pageable);
    };


	@Override
	public Order  create(OrderDto orderDto) {
        List<OrderItemDto> items=orderDto.getItems();
        Map<BigInteger,OrderItemDto> itemDtos= Maps.newHashMap();

        for(OrderItemDto item:items){
            itemDtos.put(item.getProdId(),item);
        }
        Order order=new Order();
        BeanUtils.copyProperties(orderDto,order);
        order.setOrderId(Long.parseLong(OrderHelper.generateOrderNum()));
        order.setItems(null);
        this.orderRepository.save(order);
        Long orderId=order.getOrderId();
        List<Periodical> list= this.periodicalService.findAllById(itemDtos.keySet());
        List<OrderItem> orderItems= Lists.newArrayList();
        for(Periodical prod:list){
            OrderItem orderItem=new OrderItem();
            orderItem.setProdId(prod.getId().longValue());
            orderItem.setOrderId(orderId);
            orderItem.setProdQuantity(itemDtos.get(prod.getId()).getProdQuantity());
            orderItem.setProdUrl(prod.getCoverImgUrl());
            orderItem.setProdName(prod.getPeriodicalName());
            orderItem.setProdFinalPrice(prod.getFinalPrice());
            orderItem.setProdOriginPrice(prod.getOriginPrice());
            orderItem.setSellerId(prod.getUserId());
            orderItems.add(orderItem);
        }
        this.orderItemRepository.saveAll(orderItems);
		return order;
	}

}
