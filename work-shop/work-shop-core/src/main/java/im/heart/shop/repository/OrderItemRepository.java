package im.heart.shop.repository;

import im.heart.shop.entity.Order;
import im.heart.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderItemRepository extends QuerydslPredicateExecutor<OrderItem>,  JpaRepository<OrderItem, Long>,JpaSpecificationExecutor<OrderItem> {

}
