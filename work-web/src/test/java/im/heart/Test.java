package im.heart;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import im.heart.core.plugins.ip.IpInfo;
import im.heart.core.plugins.ip.TaoBaoIp;
import im.heart.core.utils.OkHttpClientUtils;

import java.io.IOException;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        try {
            IpInfo ipInfo = TaoBaoIp.getTaoBaoIp("122.224.68.106");
            System.out.println(JSON.toJSONString(ipInfo));
            Map<String, Object> params= Maps.newHashMap();
            params.put("","");
            String a = OkHttpClientUtils.fetchEntityString("http://127.0.0.1:7080/api/v1", params);
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
