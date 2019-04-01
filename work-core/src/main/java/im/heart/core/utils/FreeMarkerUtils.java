package im.heart.core.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author gg
 * FreeMarkers 帮助工具类
 */
public class FreeMarkerUtils {

	private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);

	static {
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDateFormat("yyyy-MM-dd");
		configuration.setNumberFormat("0.######");
		configuration.setBooleanFormat("true,false");
		configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		configuration.setTimeFormat("HH:mm:ss");
		configuration.setWhitespaceStripping(true);
		configuration.setURLEscapingCharset("UTF-8");
		configuration.setLocale(Locale.CHINESE);
	}

	public static String renderString(String templateString,
			Map<String, Object> model) {
		try {
			Template t = new Template("name", new StringReader(templateString),configuration);
			return renderTemplate(t, model);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void processTemplate(Template template, Object model,OutputStream out) {
		Writer w =null;
		try {
			w = new OutputStreamWriter(out, "UTF-8");
			template.process(model, w);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			IOUtils.closeQuietly(w);
			IOUtils.closeQuietly(out);
		}
	}
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Configuration buildConfig(String directory)
			throws IOException {
		Resource path = new DefaultResourceLoader().getResource(directory);
		configuration.setDirectoryForTemplateLoading(path.getFile());
		return configuration;
	}
	public static Configuration buildClassLoaderConfig(String directory)
			throws IOException {
		configuration.setClassLoaderForTemplateLoading(ClassUtils.getDefaultClassLoader(),directory);
		return configuration;
	}
}
