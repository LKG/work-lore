
package im.heart.frame.tags;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import im.heart.common.context.ContextManager;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.tags.BaseDirective;
import im.heart.core.utils.StringUtilsEx;
import im.heart.frame.entity.FrameDictItem;
import im.heart.frame.entity.QQGroup;
import im.heart.frame.service.FrameDictItemService;
import im.heart.frame.service.QQGroupService;
import im.heart.frame.vo.FrameDictItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class QqGroupTag extends BaseDirective {
	protected static final Logger logger = LoggerFactory.getLogger(QqGroupTag.class);

	@Override
	public void render(Environment env, Map<?, ?> params, TemplateDirectiveBody body)
			throws IOException, TemplateException {

		String type=super.getParam(params, "type");
		List<FrameDictItemVO> itemVOs=Lists.newArrayList();
		QQGroupService qqGroupService = (QQGroupService) ContextManager.getBean(QQGroupService.BEAN_NAME);
		final Collection<SearchFilter> filters= Sets.newHashSet();
		int page =  Integer.valueOf(getParam(params,"page","1"));
		int size =  Integer.valueOf(getParam(params,"size","10"));
		String sort =  getParam(params,"sort","qqTotal");
		String order =  getParam(params,"order",CommonConst.Page.ORDER_DESC);
		if(StringUtilsEx.isNotBlank(type)){
			filters.add(new SearchFilter("qqType", SearchFilter.Operator.EQ,type));
		}
		filters.add(new SearchFilter("isPub", SearchFilter.Operator.EQ,Boolean.TRUE));
		Specification<QQGroup> spec= DynamicSpecifications.bySearchFilter(filters, QQGroup.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,QQGroup.class);
		Page<QQGroup> pag = qqGroupService.findAll(spec, pageRequest);
		setVariable(CommonConst.RequestResult.RESULT,pag,env);
		super.renderBody(env,body);
	}
	@Override
	protected void verifyParameters(Map<?, ?> params) throws TemplateModelException {
		 
       
	}

}
