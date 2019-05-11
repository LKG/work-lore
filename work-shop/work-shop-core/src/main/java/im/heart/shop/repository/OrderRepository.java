package im.heart.shop.repository;

import im.heart.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order> {

}
