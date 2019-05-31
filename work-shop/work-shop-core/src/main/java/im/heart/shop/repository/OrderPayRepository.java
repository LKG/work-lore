package im.heart.shop.repository;

import im.heart.shop.entity.OrderPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPayRepository  extends QuerydslPredicateExecutor<OrderPay>, JpaRepository<OrderPay, Long>,JpaSpecificationExecutor<OrderPay> {

}
