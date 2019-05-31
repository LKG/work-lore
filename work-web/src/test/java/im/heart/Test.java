package im.heart;


import im.heart.core.utils.OkHttpClientUtils;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            String a = OkHttpClientUtils.fetchEntityString("http://127.0.0.1:7080/api/v1", null);
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
