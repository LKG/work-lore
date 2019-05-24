package im.heart.media.tags;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import im.heart.common.context.ContextManager;
import im.heart.core.enums.Status;
import im.heart.core.tags.BaseDirective;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.QPeriodical;
import im.heart.media.service.PeriodicalService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class PeriodicalTag extends BaseDirective {
	protected static final Logger logger = LoggerFactory.getLogger(PeriodicalTag.class);

	@Override
	public void render(Environment env, Map<?, ?> params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		PeriodicalService periodicalService=	ContextManager.getBean(PeriodicalService.class);
		int size =  Integer.valueOf(getParam(params,"size","10"));
		String categoryCode=super.getParam(params, "categoryCode");
		QPeriodical qPeriodical= QPeriodical.periodical;
		Predicate predicate= qPeriodical.checkStatus.eq(Status.enabled);
		if(StringUtils.isNotBlank(categoryCode)){
			predicate= ExpressionUtils.and(predicate,qPeriodical.categoryCode.like(categoryCode+'%'));
		}
//		OrderSpecifier<Long> sortOrder = qPeriodical.downTimes.desc();
		List<Periodical> list = periodicalService.findAll(predicate,size);
		setVariable("docs",list,env);
		super.renderBody(env,body);
	}
	@Override
	protected void verifyParameters(Map<?, ?> params) throws TemplateModelException {
		  // 检查是否传递参数，此指令禁止传参！
	}

}
