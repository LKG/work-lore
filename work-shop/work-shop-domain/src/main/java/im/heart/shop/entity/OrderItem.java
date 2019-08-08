package im.heart.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * 商品订单项表
 * @作者 LKG
 */
@Entity
@Table(name = "shop_order_item")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "shopOrderItemSequenceGenerator", sequenceName = "shop_order_item_sequence")
public class OrderItem implements AbstractEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ITEM_ID", nullable = false, unique = true, updatable = false)
	private Long itemId;
	/**
	 * 订单号
	 */
	@Column(length = 32, name = "ORDER_ID", nullable = false, updatable = false)
	private Long orderId;
	/**
	 * 商品货号
	 */
	@Column(length = 32, name = "PROD_ID", nullable = false, updatable = false)
	private Long prodId;
	/**
	 * 商品名称
	 */
	@Column(length = 256, name = "PROD_NAME", nullable = false, updatable = false)
	private String prodName;
	/**
	 * 商品价格
	 */
	@Column(name = "PROD_ORIGIN_PRICE", nullable = false, updatable = false)
	private BigDecimal prodOriginPrice;
	/**
	 * 商品数量
	 */
	@Column(name = "PROD_QUANTITY", nullable = false, updatable = false)
	private Integer prodQuantity;

	/**
	 *  商品价格
	 */
	@Column(name = "PROD_FINAL_PRICE", nullable = false, updatable = false)
	private BigDecimal prodFinalPrice;

	@Column(length = 32, name = "SELLER_ID", nullable = false)
	private BigInteger sellerId;

	@Length(max = 128)
	@Column(length = 128, name = "SELLER_NAME", nullable = false)
	private String sellerName;
	/**
	 * 商品图片地址
	 */
	@Column(name = "PROD_URL", nullable = false, updatable = false)
	private String prodUrl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	/**
	 * // 发货日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "SHIPPED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedTime;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
	}
	@Version
	@Column( name = "VERSION")
	private Integer  version;
}
