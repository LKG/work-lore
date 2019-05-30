package im.heart.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
import java.util.List;

/**
 * 
 * @作者： LKG
 * @功能说明：商品订单实体类
 */
@Entity
@Table(name = "shop_order")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "shopOrderSequenceGenerator", sequenceName = "shop_order_sequence")
public class Order implements AbstractEntity<Long> {

	/**
	 * 订单状态（未处理、已处理、已完成、已作废）
	 */
	public enum OrderStatus {
		unprocessed, processed, completed, invalid
	}

	/**
	 * 付款状态（未支付、部分支付、已支付、部分退款、全额退款）
	 */
	public enum PaymentStatus {
		unpaid, partPayment, paid, partRefund, refunded
	}

	/**
	 * 订单渠道
	 */
	public enum Channel {
		phone, web
	}
	/**
	 * 配送状态（未发货、部分发货、已发货、部分退货、已退货）
	 */
	public enum ShippingStatus {
		unshipped, partShipped, shipped, partReshiped, reshiped
	}

	/**
	 *  订单号
	 */
	@Id
	@Column(length = 32, name = "ORDER_ID", nullable = false, unique = true, updatable = false)
	private Long orderId;

	/**
	 *  订单状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS" , nullable = false)
	private OrderStatus orderStatus;
	/**
	 *  支付状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENT_STATUS" , nullable = false)
	private PaymentStatus paymentStatus;

	/**
	 * 发货状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "SHIPPING_STATUS" , nullable = false)
	private ShippingStatus shippingStatus;

	/**
	 * 优惠券id
	 */
/*	@Column(length = 32, name = "COUPON_ID")
	private BigInteger couponId;*/
	/**
	 * 订单归属用户Id
	 */
	@Column(length = 32, name = "USER_ID", nullable = false)
	private BigInteger userId;
	/**
	 * 订单归属用户名称
	 */
	@Length(max = 128)
	@Column(length = 128, name = "BUYER_NAME", nullable = false)
	private String buyerName;

	/**
	 *  订单归属用户电话号码
	 */
	@Length(max = 64)
	@Column(length = 64, name = "BUYER_MOBILE", nullable = false)
	private String buyerMobile;

/*	@Length(max = 32)
	@Column(length = 32, name = "DELIVERY_TYPE")
	private String deliveryType;// 配送方式名称*/

	/**
	 * 支付方式名称
	 */
	@Length(max = 32)
	@Column(length = 32, name = "PAYMENT_TYPE")
	private String paymentType;

	/**
	 * 商品总价格
	 */
	@Column(precision = 15, scale = 5, name = "PROD_TOTAL_PRICE", nullable = false)
	private BigDecimal prodTotalPrice;

	/**
	 * 订单总额
	 */
	@Column(precision = 15, scale = 5, name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;
	@Column(precision = 15, scale = 5, name = "COUPON_PRICE")
	private BigDecimal couponPrice;

/*	@Column(precision = 15, scale = 5, name = "DELIVERY_FEE", columnDefinition="0.00" )
	private BigDecimal deliveryFee;// 配送费用*/

	/**
	 * 支付费用
	 */
	@Column(precision = 15, scale = 5, name = "PAYMENT_FEE" , columnDefinition="0.00")
	private BigDecimal paymentFee;
	/**
	 *  已付金额
	 */
	@Column(precision = 15, scale = 5, name = "PAID_AMOUNT", columnDefinition="0.00")
	private BigDecimal paidAmount;
/**
 *  商品重量
 */
//	@Column(precision = 15, scale = 5, name = "PROD_WEIGHT" , columnDefinition="0.00")
//	private BigDecimal prodWeight;

	/**
	 *  商品总数
	 */
	@Column(name = "PROD_TOTAL_QUANTITY", nullable = false , columnDefinition="1")
	private Integer prodTotalQuantity;

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

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "EXPIRY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "LOCK_EXPIRY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lockExpireTime;
	@Length(max = 512)
	@Column(length = 512, name = "REMARK")
	private String remark;
	//###################发票相关###############################
	/**
	 * 是否需要发票
	 */
	@Column(name = "INVOICE_FLAG" , nullable = false)
	private Boolean invoiceFlag;

	/**
	 * 订单明细
	 */
	@OneToMany(mappedBy="orderId",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JSONField(serialzeFeatures={SerializerFeature.DisableCircularReferenceDetect})
	private List<OrderItem>  items;

	@Transient
	public boolean isExpired() {
		return (expiryTime!= null) && new Date().after(expiryTime);
	}

	/**
	 * 判断是否已锁定
	 * @param userId
	 * @return 是否已锁定
	 */
	@Transient
	public boolean isLocked(BigInteger userId) {
		return (lockExpireTime!= null&& new Date().before(lockExpireTime));
	}
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
