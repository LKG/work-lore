package im.heart.conf;

import im.heart.security.credentials.RetryLimitCredentialsMatcher;
import im.heart.security.realm.FrameUserRealm;
import im.heart.security.session.ShiroSessionListener;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/application-shiro.yml")
public class ShiroWebConfig extends ShiroWebAutoConfiguration{
	protected static final Logger logger = LoggerFactory.getLogger(ShiroWebConfig.class);


	/**
	 * shiro如何获取用户信息来做登录或权限控制
	 * @return
	 */
	@Bean(name="frameUserRealm")
	public FrameUserRealm frameUserRealm() {
		FrameUserRealm frameUserRealm=	new FrameUserRealm();
		frameUserRealm.setCredentialsMatcher(credentialsMatcher());
		return frameUserRealm;
	}
	@Bean
	@Override
	protected Authorizer authorizer() {
		return super.authorizer();
	}
	/**
	 *
	 * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 * @return
	 */
	@Bean()
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	/**
	 * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
	 *      * 这里只做鉴权，不做权限控制，因为权限用注解来做。
	 * 网络请求的权限过滤, 拦截外部请求
	 */
	@Bean
	@Override
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
		chain.addPathDefinition("/static/**", "anon");
		chain.addPathDefinition("/favicon.ico", "anon");
		chain.addPathDefinition("**.ico", "anon");
		chain.addPathDefinition("/oauth2/**", "anon");
		chain.addPathDefinition("/3rd/**", "anon");
		chain.addPathDefinition("/css/**", "anon");
		chain.addPathDefinition("/js/**", "anon");
		chain.addPathDefinition("/imgs/**", "anon");
		chain.addPathDefinition("/images/**", "anon");
		chain.addPathDefinition("/app/js/**", "anon");
		chain.addPathDefinition("/app/css/**", "anon");
		chain.addPathDefinition("/app/imgs/**", "anon");
		chain.addPathDefinition("/modules/**", "anon");
		chain.addPathDefinition("/login-in**", "anon");
		chain.addPathDefinition("/validate/**", "anon");
		chain.addPathDefinition("/regist**", "anon");
		chain.addPathDefinition("/regist/**", "anon");
		chain.addPathDefinition("/findPwd/**", "anon");
		chain.addPathDefinition("/api/**", "anon");
		chain.addPathDefinition("/index/**", "anon");
		chain.addPathDefinition("/docs**", "anon");
		chain.addPathDefinition("/doc/**", "anon");
		chain.addPathDefinition("/article**", "anon");
		chain.addPathDefinition("/article/**", "anon");
		chain.addPathDefinition("/logout*", "logout");
		chain.addPathDefinition("/", "anon");
		chain.addPathDefinition("/uploads/**", "anon");
		chain.addPathDefinition("/admin/druid/**", "perms[druid:monitor]");
		chain.addPathDefinition("/admin/monitor/**", "perms[monitor:monitor]");
		chain.addPathDefinition("/authenticated", "authc");
		chain.addPathDefinition("/**", "authc");
		return chain;
	}


	@Bean(name = "credentialsMatcher")
	public RetryLimitCredentialsMatcher credentialsMatcher() {
		RetryLimitCredentialsMatcher retryLimitCredentialsMatcher=new RetryLimitCredentialsMatcher();
		return retryLimitCredentialsMatcher;
	}
	/**
	 * 自定义sessionListener
	 * @return
	 */
	@Bean(name="sessionListener")
	public SessionListener shiroSessionListener(){
		ShiroSessionListener shiroSessionListener=new ShiroSessionListener();
		return shiroSessionListener;
	}
}
