package im.heart.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private BigInteger orderId;// 订单号
    private BigInteger prodId;// 商品货号
    private Integer prodQuantity;// 商品数量
    private String prodName;// 商品名称
    private BigDecimal prodOriginPrice;// 商品价格
    private BigDecimal prodFinalPrice;// 商品价格
    private String prodUrl;// 商品图片地址
}
