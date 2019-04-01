package im.heart.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author gg
 * @desc DruidConfig 配置
 */
@Configuration
public class DruidConfig {
	protected static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);
//	@Bean
//	public ServletRegistrationBean statViewServlet(){
//		//创建servlet注册实体
//		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/admin/druid/*");
//		//设置ip白名单
//		servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//		//设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
//		servletRegistrationBean.addInitParameter("deny","192.168.0.19");
//		//设置控制台管理用户
//		servletRegistrationBean.addInitParameter("loginUsername","druid");
//		servletRegistrationBean.addInitParameter("loginPassword","123456");
//		//是否可以重置数据
//		servletRegistrationBean.addInitParameter("resetEnable","false");
//		return servletRegistrationBean;
//	}
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new WebStatFilter());
//		filterRegistrationBean.addUrlPatterns("/*");
//		filterRegistrationBean.addInitParameter("exclusions","*.js,*.woff2,*.gif,*.jpg,*.png,*.css,*.ico,/admin/druid/*");
//		return filterRegistrationBean;
//	}
}
