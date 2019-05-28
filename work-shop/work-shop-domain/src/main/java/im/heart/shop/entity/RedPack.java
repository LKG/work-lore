package im.heart.shop.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RedPack implements Serializable {
    /**
     * 根据属性生成的验证
     */
    private String sign;
    /**
     * 订单号
     * 商户订单号（每个订单号必须唯一）
     * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。
     * 接口根据商户订单号支持重入，如出现超时可再调用。
     */
    private String mch_billno;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 微信appid
     */
    private String wxappid;
    /**
     * 商户名称
     */
    private String send_name;
    /**
     * // 用户openid
     */
    private String re_openid;
    /**
     * 付款金额
     */
    private String total_amount;
    /**
     * 红包接收人数  现金红包只能是  1
     */
    private String total_num;
    /**
     * 红包祝福语
     */
    private String wishing;
    /**
     * 调用接口机器的IP
     */
    private String client_ip;
    /**
     * 活动名称
     */
    private String act_name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 随机字符串
     */
    private String nonce_str;
}
