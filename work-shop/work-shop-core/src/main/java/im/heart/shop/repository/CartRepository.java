package im.heart.shop.repository;

import im.heart.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends QuerydslPredicateExecutor<Cart>, JpaRepository<Cart, Long>,JpaSpecificationExecutor<Cart> {

}
