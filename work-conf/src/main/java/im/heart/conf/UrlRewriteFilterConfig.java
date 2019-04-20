package im.heart.conf;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author gg
 * @desc UrlRewriteFilter 配置
 */
@Configuration
public class UrlRewriteFilterConfig  extends UrlRewriteFilter {
    public static final String DEFAULT_WEB_CONF_PATH = "classpath:/urlrewrite.xml";
    @Value(DEFAULT_WEB_CONF_PATH)
    private Resource resource;
    @Order(-2)
    @Bean
    public FilterRegistrationBean urlRewrite(){
        UrlRewriteFilter rewriteFilter=new UrlRewriteFilter();
        FilterRegistrationBean<UrlRewriteFilter> registration = new FilterRegistrationBean<UrlRewriteFilter>(rewriteFilter);
        registration.setUrlPatterns(Collections.singleton("/*"));
        Map<String, String> initParam= Maps.newHashMap();
        initParam.put("confPath",DEFAULT_WEB_CONF_PATH);
        initParam.put("infoLevel","INFO");
        registration.setInitParameters(initParam);
        return  registration;
    }
    @Override
    protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
        try {
            // Create a UrlRewrite Conf object with the injected resource
            Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(),
                    "@@traceability@@");
            checkConf(conf);
        } catch (IOException ex) {
            throw new ServletException("Unable to load URL rewrite configuration file from " + DEFAULT_WEB_CONF_PATH, ex);
        }
    }

}
