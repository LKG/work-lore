package im.heart.shop.repository;

import im.heart.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CartRepository extends JpaRepository<Cart, BigInteger>,JpaSpecificationExecutor<Cart> {

}
