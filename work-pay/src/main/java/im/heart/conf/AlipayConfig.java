package im.heart.conf;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import static com.alipay.api.AlipayConstants.CHARSET;

public class AlipayConfig {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{

    }
}
