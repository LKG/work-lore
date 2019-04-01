package im.heart.conf;

import com.google.common.collect.Lists;
import im.heart.security.credentials.RetryLimitCredentialsMatcher;
import im.heart.security.filter.FrameAuthenticationFilter;
import im.heart.security.filter.FrameLogoutFilter;
import im.heart.security.filter.KickOutSessionControlFilter;
import im.heart.security.filter.ShiroFilterFactory;
import im.heart.security.realm.FrameUserRealm;
import im.heart.security.session.ShiroSessionListener;
import im.heart.security.session.ShiroSessionManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.Map;

@Configuration
@PropertySource(value = "classpath:/application-shiro.yml")
public class ShiroWebFilterConfig extends ShiroWebFilterConfiguration {
	protected static final Logger logger = LoggerFactory.getLogger(ShiroWebFilterConfig.class);
	@Bean
	@Override
	protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
		return super.shiroFilterFactoryBean();
	}
	/**
	 *
	 * @return
	 */
	@Bean(name = "kickout")
	public KickOutSessionControlFilter kickoutSessionControlFilter() {
		KickOutSessionControlFilter kickoutSessionControlFilter = new KickOutSessionControlFilter();
		return kickoutSessionControlFilter;
	}
	@Bean(name = "filterShiroFilterRegistrationBean")
	@Override
	protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		Map<String, Filter> filters=shiroFilterFactoryBean().getFilters();
		/*<!-- 添加自定义过滤链 -->*/
		filters.put("authc",new FrameAuthenticationFilter());
		/*<!-- 用户注销控制过滤链 -->*/
		filters.put("logout",new FrameLogoutFilter());
		/*<!-- 添加ssl过滤链 -->*/
		filters.put("ssl",new SslFilter());
		/*<!-- 控制并发登录人数 -->*/
		filters.put("kickout",kickoutSessionControlFilter());
		filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
		filterRegistrationBean.setOrder(1);

		return filterRegistrationBean;
	}
}
