package im.heart.core.plugins.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import im.heart.core.utils.FreeMarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * 
 * @author lkg
 * @desc :邮件发送配置管理项
 */
@Component()
public class EmailManager {
	protected static final Logger logger = LoggerFactory.getLogger(EmailManager.class);
	@Value("${spring.mail.from:''}")
	private String mailFrom="";
	@Value("${spring.mail.template-path:'classpath:/templates/email/'}")
	private String mailTemplatePath="classpath:/templates/email/";

	@Resource
	private JavaMailSender javaMailSender;
	/**
	 * 
	 * mergeEmailContent
	 * @param model
	 * @param tplFile
	 * @return
	 */
	public String mergeEmailContent(final Map<String, Object> model,
			final String tplFile) {
		String content = "";
		Template template = null;
		try {
			Configuration cfg = FreeMarkerUtils.buildClassLoaderConfig(this.mailTemplatePath);
			template = cfg.getTemplate(tplFile);
			content = FreeMarkerUtils.renderTemplate(template, model);
		} catch (Exception e) {
			logger.warn("获取配置信息异常开始尝试容错机制"+e.getStackTrace()[0].getMethodName());
			try {
				Configuration cfg = FreeMarkerUtils.buildConfig(this.mailTemplatePath);
				template = cfg.getTemplate(tplFile);
				content = FreeMarkerUtils.renderTemplate(template, model);
			} catch (Exception e1) {
				logger.error(e.getStackTrace()[0].getMethodName(), e);
			}
		}
		return content;
	}




	/**
	 * 
	 * 发送邮件
	 * @param mimeMessagePreparator
	 */
	protected void sendJavaMail(MimeMessagePreparator mimeMessagePreparator) {
		try {
			logger.info("Begin to send mail...");
			// 发送邮件
			this.javaMailSender.send(mimeMessagePreparator);
			logger.info("End to send mail!");
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
	}
  
	private MimeMessagePreparator buildMimeMessage(final String[] mailTo,final String subject ,final String content ,final String[] files){
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				try {
					MimeMessageHelper message = new MimeMessageHelper(
							mimeMessage, true, "UTF-8");
					//// 设置接收方的email地址
					message.setTo(mailTo);
					// 设置邮件主题
					message.setSubject(subject);
					message.setFrom(mailFrom);
					// 设置发送方地址
					message.setText(content, true);
					if(files!=null){
						// 添加附件
						for (String s : files){
							// 读取附件
							FileSystemResource	file = new FileSystemResource(new File(s));
							// 向email中添加附件
							message.addAttachment(s, file);
						}	
					}

				} catch (Exception e) {
					logger.error(e.getStackTrace()[0].getMethodName(), e);
				}
			}
		};
		return mimeMessagePreparator;
	}
	/**
	 * 
	 * 发送邮件
	 * @param model 传入对象信息
	 * @param subject    邮件标题
	 * @param tplFile    邮件模板路径
	 * @param mailTo  邮件接收方
	 * @param files  附件
	 * 
	 * @throws Exception
	 */
	public void sendEmail(final Map<String, Object> model,
			final String subject, final String tplFile, final String[] mailTo,
			final String[] files) throws Exception {
		String content = mergeEmailContent(model, tplFile);
		MimeMessagePreparator mimeMessagePreparator=this.buildMimeMessage(mailTo,subject,content,files);
		this.sendJavaMail(mimeMessagePreparator);
	}

	/**
	 * 
	 * 发送邮件
	 * @param subject  邮件标题
	 * @param content  邮件内容
	 * @param mailTo    邮件接收方
	 * @param files   附件
	 * @throws Exception
	 */
	public void sendEmail(final String subject, final String content,
			final String[] mailTo, final String[] files) throws Exception {
		MimeMessagePreparator mimeMessagePreparator =this.buildMimeMessage(mailTo,subject,content,files);
		this.sendJavaMail(mimeMessagePreparator);
	}
}