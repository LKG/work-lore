package im.heart.shop.repository;

import im.heart.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends QuerydslPredicateExecutor<CartItem>, JpaRepository<CartItem, Long>,JpaSpecificationExecutor<CartItem> {

}
