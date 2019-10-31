package im.heart.conf;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
public class WxPayConfig{
    @Value("${pay.weixin.hostIp}")
    private String hostIp;
    @Value("${pay.weixin.certPath}")
    private String certPath;

    @Value("${pay.weixin.appId}")
    private String appId;
    @Value("${pay.weixin.mchId}")
    private String mchId;
    @Value("${pay.weixin.key}")
    private String key;
    @Value("${pay.weixin.signType}")
    private String signType;
    @Value("${pay.weixin.deviceInfo}")
    private String deviceInfo;
    @Value("${pay.weixin.frontUrl}")
    private String frontUrl;
    @Value("${pay.weixin.backUrl}")
    private String backUrl;
    @Value("${pay.weixin.api.unifiedorder}")
    private String unifiedorderApi;

    @Value("${pay.weixin.sceneInfo.h5Info.type}")
    private String sceneInfo_h5Info_type;
    @Value("${pay.weixin.sceneInfo.h5Info.wapUrl}")
    private String sceneInfo_h5Info_wapUrl;
    @Value("${pay.weixin.sceneInfo.h5Info.wapName}")
    private String sceneInfo_h5Info_wapName;
}
