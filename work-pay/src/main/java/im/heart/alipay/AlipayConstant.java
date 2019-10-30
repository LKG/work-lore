package im.heart.alipay;

import java.io.File;

public class AlipayConstant {
    /**
     * 渠道名称:支付宝
     */
    public final static String CHANNEL_NAME = "ALIPAY";
    /**
     *  支付宝移动支付
     */
    public final static String CONFIG_PATH = "alipay" + File.separator + "alipay";

    public  enum  TradeStatus{
        WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建,等待买家付款"),
        TRADE_CLOSED( "TRADE_CLOSED", "交易关闭"),
        TRADE_SUCCESS("TRADE_SUCCESS", "交易成功"),
        TRADE_FINISHED("TRADE_FINISHED", "交易成功且结束");
        public String code;
        public final String desc;
        TradeStatus( String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public static TradeStatus findByCode(String code) {
            for (TradeStatus status : TradeStatus.values()) {
                if (status.code.equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }
    public  enum  TradeType{
        MOBILE("ALIPAY_MOBILE", "支付宝移动支付"),
        PC( "ALIPAY_PC", "支付宝PC支付"),
        WAP("ALIPAY_WAP", "支付宝WAP支付"),
        QR("ALIPAY_QR", "支付宝当面付之扫码支付");
        public String code;
        public final String desc;
        TradeType( String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public static TradeType findByCode(String code) {
            for (TradeType status : TradeType.values()) {
                if (status.code.equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }

    public  enum  ReturnValue{
        SUCCESS("success", "返回标识-成功"),
        FAIL( "fail", "返回标识-失败");
        public String code;
        public final String desc;
        ReturnValue( String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public static ReturnValue ReturnValue(String code) {
            for (ReturnValue status : ReturnValue.values()) {
                if (status.code.equals(code)) {
                    return status;
                }
            }
            return ReturnValue.FAIL;
        }
    }
}
