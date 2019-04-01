package im.heart.conf;

import freemarker.template.Configuration;
import im.heart.security.tags.ShiroTags;
import im.heart.usercore.tags.UserInfoTags;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 *
 * @author gg
 * @desc  shiro 标签配置
 */
@Component
public class ShiroTagConfig implements InitializingBean {
	@Autowired
	private Configuration configuration;

	@Autowired
	private FreeMarkerViewResolver resolver;
	@Autowired
	private UserInfoTags userInfoTags;
	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		configuration.setSharedVariable("shiro", new ShiroTags(this.configuration.getObjectWrapper()));
		configuration.setSharedVariable("user", userInfoTags);
		// 加上这句后，可以在页面上用${context.contextPath}获取contextPath
		resolver.setRequestContextAttribute("context");
	}
}
