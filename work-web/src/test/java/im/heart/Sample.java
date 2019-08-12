package im.heart;


import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16984737";
    public static final String API_KEY = "MvxEAgOS94tBzGKw3N56EYaH";
    public static final String SECRET_KEY = "WWO3y0nGTbSPsHRvhfyiX1o4b4Kp861z";

    public static void main(String[] args) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String text = "百度是一家高科技公司";
        JSONObject res = client.lexer(text, null);
        System.out.println(res.toString(2));

    }
}
