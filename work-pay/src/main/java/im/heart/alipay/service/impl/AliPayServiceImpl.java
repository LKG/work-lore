package im.heart.alipay.service.impl;

import com.alipay.api.domain.Product;
import im.heart.alipay.service.AliPayService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {

    /**
     * 阿里支付预下单
     * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
     * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
     * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。
     *
     * @param product
     * @return String
     * @Date 2017年7月27日
     */
    @Override
    public String pay(Product product) {
        return null;
    }

    /**
     * 阿里支付退款
     *
     * @param product
     * @return String
     * @Date 2017年7月27日
     */
    @Override
    public String refund(Product product) {
        return null;
    }

    /**
     * @param product
     * @return String
     */
    @Override
    public String closeOrder(Product product) {
        return null;
    }

    /**
     * 下载对账单
     *
     * @param billDate
     * @param billType
     * @return String
     */
    @Override
    public String downloadBillUrl(String billDate, String billType) {
        return null;
    }

    /**
     * 手机H5支付、腾讯相关软件下不支持、使用UC等浏览器打开
     * 方法一：
     * 对于页面跳转类API，SDK不会也无法像系统调用类API一样自动请求支付宝并获得结果，而是在接受request请求对象后，
     * 为开发者生成前台页面请求需要的完整form表单的html（包含自动提交脚本），商户直接将这个表单的String输出到http response中即可。
     * 方法二：
     * 如果是远程调用返回消费放一个form表单 然后调用方刷新到页面自动提交即可
     * 备注：人民币单位为分
     * attach 附件参数 使用json格式传递 用于回调区分
     *
     * @param product
     */
    @Override
    public String payByMobile(Product product) {
        return null;
    }

    /**
     * 网站支付
     *
     * @param product
     * @return String
     */
    @Override
    public String payByPc(Product product) {
        return null;
    }

    /**
     * APP支付
     *
     * @param product
     * @return String
     */
    @Override
    public String payByApp(Product product) {
        return null;
    }
}
