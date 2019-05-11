package im.heart.shop.repository;

import im.heart.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>,JpaSpecificationExecutor<OrderItem> {

}
