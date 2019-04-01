package im.heart.shop.entity;/*
package im.heart.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

*/
/**
 * 
 * @作者： LKG
 * @功能说明：商品订单实体类
 *//*

@Entity
@Table(name = "shopping_order_ship")
@DynamicUpdate(true)
@Data
public class OrderShip implements AbstractEntity<String>{

	@Column(length = 32, name = "ORDER_ID", nullable = false, unique = true, updatable = false)
	private String orderId;// 订单号

    //########发货相关
	@Column(name = "SHIP_NAME" , nullable = false)
	private String shipName;// 收货人姓名

	@Column(name = "SHIP_AREA",columnDefinition="")
	private String shipArea;// 收货地区
	//TODO:
	private String shipAreaPath;// 收货地区路径

	@Column(name = "SHIP_ADDRESS" ,columnDefinition="")
	private String shipAddress;// 收货地址

	@Column(name = "SHIP_ZIPCODE" ,columnDefinition="")
	private String shipZipCode;// 收货邮编

	@Column(name = "SHIP_PHONE"  ,columnDefinition="")
	private String shipPhone;// 收货电话

	@Column(name = "SHIP_MOBILE")
	private String shipMobile;// 收货手机
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODI_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modiTime;

	
}
*/
