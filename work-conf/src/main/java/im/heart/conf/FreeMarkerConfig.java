package im.heart.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 *
 * @author gg
 * @desc FreeMarker 配置类
 */
@Configuration
public class FreeMarkerConfig  implements InitializingBean {
    @Autowired
    private freemarker.template.Configuration configuration;
    @Value("${info.app-version''}")
    private String appVersion = "";
    @Value("${info.app-host''}")
    private String appHost = "";
    @Autowired
    private FreeMarkerViewResolver resolver;
    @Override
    public void afterPropertiesSet() throws Exception {
        configuration.setSharedVariable("ver", appVersion);
        configuration.setSharedVariable("appHost", appHost);
        // 加上这句后，可以在页面上用${context.contextPath}获取contextPath
        resolver.setRequestContextAttribute("context");
    }
}
