package im.heart.shop.entity;

import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * 
 * 
 * @作者： LKG
 * 购物车实体类
 */
@Entity
@Table(name = "shop_cart")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "shopCartSequenceGenerator", sequenceName = "shop_cart_sequence")
public class Cart  implements AbstractEntity<Long> {
	@Id
	@Column(length = 32, name = "CART_ID", nullable = false, unique = true, updatable = false)
	private Long 	cartId;
	private BigInteger userId;
	private String userName;
	/**
	 * productId
	 */
	private Long productId;
	/**
	 * skuId
	 */
	private Long productSkuId;
	/**
	 * 是否逻辑删除 N:否 Y:是
	 */
	private Boolean isDeleted;
	/**
	 * 优惠券编号列表
	 */
	private String 	couponIds;
	private String 	itemIds;
}

