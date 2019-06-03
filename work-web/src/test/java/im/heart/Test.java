package im.heart;


import com.google.common.collect.Maps;
import im.heart.core.utils.OkHttpClientUtils;

import java.io.IOException;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        try {
            Map<String, Object> params= Maps.newHashMap();
            params.put("","");
            String a = OkHttpClientUtils.fetchEntityString("http://127.0.0.1:7080/api/v1", params);
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
