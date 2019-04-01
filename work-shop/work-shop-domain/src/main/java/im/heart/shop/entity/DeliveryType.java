package im.heart.shop.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import im.heart.core.entity.AbstractEntity;

/**
 *
 *
 * @功能说明：配送方式
 * @作者 LKG
 */
//@Entity
//@Table(name = "shop_delivery_type")
@DynamicUpdate(true)
@DynamicInsert(true)
@Data
public class DeliveryType implements AbstractEntity<String>{

//    /**
//     * 配送类型：先付款后发货、货到付款
//     */
//	public enum DeliveryMethod {
//		deliveryAgainstPayment, cashOnDelivery
//	};
//    /**
//     * 配送方式名称
//     */
//	private String name;
//    /**
//     *  配送类型
//     */
//	private DeliveryMethod deliveryMethod;
//    /**
//     * 首重
//     */
//	private Double firstWeight;
//    /**
//     *  续重量
//     */
//	private Double continueWeight;
//    /**
//     * 首重价格
//     */
//	private BigDecimal firstWeightPrice;
//    /**
//     * 续重价格
//     */
//	private BigDecimal continueWeightPrice;
//    /**
//     * 描述
//     */
//	private String desc;
//    /**
//     * 排序
//     */
//	private Integer sort;
//
//	@Length(max = 10)
//	@Column(length = 10, name = "STATUS", nullable = false)
//	private String status = "";

}
