package im.heart.cms.service.impl;

import im.heart.cms.service.HtmlStaticService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

@Service(value = HtmlStaticService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class HtmlStaticServiceImpl implements HtmlStaticService, ServletContextAware {
	protected static final Logger logger = LoggerFactory.getLogger(HtmlStaticServiceImpl.class);
	@Resource
	private ServletContext servletContext;
	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Override
	public int build(String templatePath, String staticPath, Map<String, Object> model) {
		return build(templatePath, staticPath,model);
	}
	private int build(String basePath, String templatePath, String staticPath, Map<String, Object> model) {
		Assert.hasText(templatePath,"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
		Assert.hasText(staticPath,"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");

		OutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		try {
			freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(servletContext.getRealPath(File.separator) + ".." + basePath + staticPath);
			logger.info("The File " + staticFile.getAbsolutePath() + " is to start.");
			File staticDirectory = staticFile.getParentFile();
			if (!staticDirectory.exists()) {
				staticDirectory.mkdirs();
			}
			fileOutputStream = new FileOutputStream(staticFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			template.process(model, writer);
			writer.flush();
			logger.info("The File " + staticFile.getAbsolutePath() + " is generated.");
			return 1;
		} catch (Exception e) {
			logger.warn("[StaticServiceImpl]" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(outputStreamWriter);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return 0;
	}
	@Override
	public int build(String templatePath, String staticPath) {
		return build(templatePath, staticPath,null);
	}

	@Override
	public void buildHeaderAndFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Override
	public void buildFooter() {
		// TODO Auto-generated method stub
		
	}



}
