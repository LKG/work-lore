package im.heart.conf;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
public class WxPayConfig{
    @Value("${hostIp}")
    private String hostIp;
    @Value("${weixin.certPath}")
    private String certPath;

    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.mchId}")
    private String mchId;
    @Value("${weixin.key}")
    private String key;
    @Value("${weixin.signType}")
    private String signType;
    @Value("${weixin.deviceInfo}")
    private String deviceInfo;
    @Value("${weixin.frontUrl}")
    private String frontUrl;
    @Value("${weixin.backUrl}")
    private String backUrl;
    @Value("${weixin.api.unifiedorder}")
    private String unifiedorderApi;

    @Value("${weixin.sceneInfo.h5Info.type}")
    private String sceneInfo_h5Info_type;
    @Value("${weixin.sceneInfo.h5Info.wapUrl}")
    private String sceneInfo_h5Info_wapUrl;
    @Value("${weixin.sceneInfo.h5Info.wapName}")
    private String sceneInfo_h5Info_wapName;
}
