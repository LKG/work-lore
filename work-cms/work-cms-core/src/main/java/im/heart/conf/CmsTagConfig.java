package im.heart.conf;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import im.heart.cms.tags.CmsTags;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;

/**
 *
 * @author gg
 * @desc  CmsTagConfig 标签配置
 */
@Component
public class CmsTagConfig implements InitializingBean {
	@Autowired
	private Configuration configuration;

	@Autowired
	private FreeMarkerViewResolver resolver;
	@Autowired
	private CmsTags cmsTags;
	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		configuration.setSharedVariable("cms", cmsTags);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		// 加上这句后，可以在页面上用${context.contextPath}获取contextPath
		resolver.setRequestContextAttribute("context");
	}
}
