package im.heart.shop.entity;

import im.heart.core.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 订单操作记录
 */
//@Entity
//@Table(name = "shop_order_log")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "shopOrderSequenceGenerator", sequenceName = "shop_order_sequence")
public class OrderLog implements AbstractEntity<Long> {
     /**
      * 类型
      */
     public enum Type {

          /** 订单创建 */
          create,

          /** 订单修改 */
          modify,

          /** 订单确认 */
          confirm,

          /** 订单支付 */
          payment,

          /** 订单退款 */
          refunds,

          /** 订单发货 */
          shipping,

          /** 订单退货 */
          returns,

          /** 订单完成 */
          complete,

          /** 订单取消 */
          cancel,

          /** erp confirm接口 */
          erpComfirm,

          /** 其它 */
          other
     };

     /** 类型 */
     private Type type;

     /** 操作员 */
     private String operator;

     /** 内容 */
     private String content;

     /** 订单 */
     private Order order;

}