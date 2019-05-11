/*
 * Project Name: ecp-ecom
 * File Name: CartItem.java
 * Class Name: CartItem
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.heart.shop.entity;

import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Entity - 购物车项
 * 
 * @author Hengtiansoft Team
 * @version 1.0_beta
 */
@Entity
@Table(name = "shop_cart_item")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "shopCartItemSequenceGenerator", sequenceName = "shop_cart_item_sequence")
public class CartItem   implements AbstractEntity<Long> {
	/**
	 * //购物车子项ID
	 */
	@Id
	@Column(length = 32, name = "ITEM_ID", nullable = false, unique = true, updatable = false)
	private Long 	itemId;
	/**
	 * //购物车Id
	 */
	@Column(length = 32, name = "CART_ID", nullable = false, unique = true, updatable = false)
	private Long 	cartId;
	/** 最大数量 */
	public static final Integer MAX_QUANTITY = 1000000;

	private Boolean isHave = true;

	/** 数量 */
	private Integer quantity;

	/** 临时商品价格 */
	@Transient
	private BigDecimal tempPrice;

	/** 临时赠送积分 */
	@Transient
	private Long tempPoint;

	/** 拆零费用 */
	private BigDecimal separableAccount;


//	/**
//	 * 获取商品
//	 *
//	 * @return 商品
//	 */
//	@JsonProperty
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false, updatable = false)
//	public Product getProduct() {
//		return product;
//	}
//
//	/**
//	 * 设置商品
//	 *
//	 * @param product
//	 *            商品
//	 */
//	public void setProduct(Product product) {
//		this.product = product;
//	}

//	/**
//	 * 获取购物车
//	 *
//	 * @return 购物车
//	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false)
//	public Cart getCart() {
//		return cart;
//	}
//
//	/**
//	 * 设置购物车
//	 *
//	 * @param cart
//	 *            购物车
//	 */
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

//	/**
//	 * 获取临时商品价格
//	 *
//	 * @return 临时商品价格
//	 */
//	@Transient
//	public BigDecimal getTempPrice() {
//		if (tempPrice == null) {
//			return getSubtotal();
//		}
//		return tempPrice;
//	}


//	/**
//	 * 获取临时赠送积分
//	 *
//	 * @return 临时赠送积分
//	 */
//	@Transient
//	public Long getTempPoint() {
//		if (tempPoint == null) {
//			return getPoint();
//		}
//		return tempPoint;
//	}

	/**
	 * 获取赠送积分
	 * 
	 * @return 赠送积分
	 */
//	@Transient
//	public long getPoint() {
//		if ((getProduct() != null) && (getQuantity() != null)) {
//			return getQuantity();
//		} else {
//			return 0L;
//		}
//	}

//	/**
//	 * 获取商品重量
//	 *
//	 * @return 商品重量
//	 */
//	@Transient
//	public int getWeight() {
//		if ((getProduct() != null) && (getProduct().getWeight() != null) && (getQuantity() != null)) {
//			return getProduct().getWeight() * getQuantity();
//		} else {
//			return 0;
//		}
//	}

//	/**
//	 * 获取商品单价
//	 *
//	 * @return 价格
//	 */
//	@JsonProperty
//	@Transient
//	public BigDecimal getPrice() {
//		if (getContractItem() != null) {
//			Setting setting = SettingUtils.get();
//			// 移除会员价逻辑
//			// TODO
//			// 判断登陆身份
//			Member member = getCart().getMember();
//			if (!member.getIsUnion()) {
//				// 会员价
//				return setting.setScale(getContractItem().getSalePrice());
//			} else {
//				// 联采价
//				return setting.setScale(getContractItem().getLianCaiPrice());
//			}
//		} else {
//			return BigDecimal.ZERO;
//		}
		/*
		 * if (getProduct() != null && getProduct().getPrice() != null) {
		 * Setting setting = SettingUtils.get(); if (getCart() != null &&
		 * getCart().getMember() != null &&
		 * getCart().getMember().getMemberRank() != null) { MemberRank
		 * memberRank = getCart().getMember().getMemberRank(); Map<MemberRank,
		 * BigDecimal> memberPrice = getProduct().getMemberPrice(); if
		 * (memberPrice != null && !memberPrice.isEmpty()) { if
		 * (memberPrice.containsKey(memberRank)) { return
		 * setting.setScale(memberPrice.get(memberRank)); } } if
		 * (memberRank.getScale() != null) { return
		 * setting.setScale(getProduct().getPrice().multiply(new
		 * BigDecimal(memberRank.getScale()))); } } return
		 * setting.setScale(getProduct().getPrice()); } else { return
		 * BigDecimal.ZERO; }
		 */
//	}

//	/**
//	 * 获取小计
//	 *
//	 * @return 小计
//	 */
//	@JsonProperty
//	@Transient
//	public BigDecimal getSubtotal() {
//		if (getQuantity() != null) {
//			if (getSeparableAccount() != null) {
//				// 拆零费用加入小计
//				return getPrice().multiply(new BigDecimal(getQuantity())).add(getSeparableAccount());
//			}
//			return getPrice().multiply(new BigDecimal(getQuantity()));
//		} else {
//			return BigDecimal.ZERO;
//		}
//	}

//	/**
//	 * 获取是否库存不足
//	 *
//	 * @return 是否库存不足
//	 */
//	@Transient
//	public boolean getIsLowStock() {
//		if ((getQuantity() != null) && (getProduct() != null) && (getProduct().getStock() != null)
//				&& (getQuantity() > getProduct().getAvailableStock())) {
//			return true;
//		} else {
//			return false;
//		}
//	}

//	/**
//	 * 增加商品数量
//	 *
//	 * @param quantity
//	 *            数量
//	 */
//	@Transient
//	public void add(int quantity) {
//		if (quantity > 0) {
//			if (getQuantity() != null) {
//				setQuantity(getQuantity() + quantity);
//			} else {
//				setQuantity(quantity);
//			}
//		}
//	}

//	@JsonProperty
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false)
//	public ContractProductItem getContractItem() {
//		return contractItem;
//	}
//
//	public void setContractItem(ContractProductItem contractItem) {
//		this.contractItem = contractItem;
//	}

//	@JsonProperty
////	@Transient
////	/**
////	 * 获取促销
////	 * @return
////	 */
////	public Promotion getRealPromotion() {
////		ContractProductItem item = getContractItem();
////		//Promotion platformPromotion = item.getPlatformPromotion();
////		Promotion purchasePromotion = item.getPurchasePromotion();
//////		if ((platformPromotion != null) && (platformPromotion.getStatus() == FlowStatus.VERIFY_PASS)
//////				&& platformPromotion.isValid(getQuantity(), getOriginalPrice(), getCart().getMember())) {
//////			return platformPromotion;
//////		}
////		if ((purchasePromotion != null) && (purchasePromotion.getStatus() == FlowStatus.VERIFY_PASS)
////				&& purchasePromotion.isValid(getQuantity(), getOriginalPrice(), getCart().getMember())) {
////			return purchasePromotion;
////		}
////		return null;
////	}

//	@Transient
//	public BigDecimal getOriginalPrice() {
//		return getPrice().multiply(new BigDecimal(getQuantity()));
//	}

//	@JsonProperty
//	@Transient
//	/**
//	 * 获取促销优惠金额
//	 * @return
//	 */
//	public BigDecimal getPromotionPrice() {
//		BigDecimal promotionPrice = BigDecimal.ZERO;
//		Promotion promotion = getRealPromotion();
//		if ((promotion != null) && promotion.isValid(getQuantity(), getOriginalPrice(), getCart().getMember())) {
//			promotionPrice = promotion.calculate(getQuantity(), getOriginalPrice());
//			return getOriginalPrice().subtract(SettingUtils.get().setScale(promotionPrice));
//		}
//		return promotionPrice;
//	}

//	@JsonProperty
//	@Transient
//	/**
//	 * 获取促销优惠数量
//	 * @return
//	 */
//	public int getPromotionNumber() {
//		int promotionNumber = 0;
//		Promotion promotion = getRealPromotion();
//		if ((promotion != null) && promotion.isValid(getQuantity(), getOriginalPrice(), getCart().getMember())) {
//			promotionNumber = promotion.calculateNum(getQuantity(), getOriginalPrice());
//		}
//		return promotionNumber;
//	}

//	@JsonProperty
//	@Transient
//	/**.
//	 * 判断是否上架
//	 */
//	public boolean getIsMarketable() {
//		ContractProductItem ci = getContractItem();
//		return ci.getIsMarketable() && new Date().after(ci.getProductStartDate())
//				&& new Date().before(ci.getProductExpireDate());
//
//	}

	// @JsonProperty
	// @Transient
	// /**
	// * 获取促销赠品
	// * @return
	// */
	// public List<GiftItem> getPromotionGift() {
	// List<GiftItem> items = new ArrayList<GiftItem>();
	// Promotion promotion = getRealPromotion();
	// if (promotion != null && promotion.isValid(getQuantity(),
	// getOriginalPrice(), getCart().getMember())) {
	// items = promotion.calculateGift(getQuantity(), getOriginalPrice());
	// }
	// return items;
	// }

}
