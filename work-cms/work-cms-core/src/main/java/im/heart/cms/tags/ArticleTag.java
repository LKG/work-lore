package im.heart.cms.tags;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import im.heart.cms.entity.Article;
import im.heart.cms.service.ArticleService;
import im.heart.cms.vo.ArticleVO;
import im.heart.common.context.ContextManager;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.tags.BaseDirective;
import im.heart.core.utils.StringUtilsEx;
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
public class ArticleTag extends BaseDirective {
	protected static final Logger logger = LoggerFactory.getLogger(ArticleTag.class);

	@Override
	public void render(Environment env, Map<?, ?> params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		ArticleService periodicalService=	ContextManager.getBean(ArticleService.class);
		final Collection<SearchFilter> filters= Sets.newHashSet();
		int page =  Integer.valueOf(getParam(params,"page","1"));
		int size =  Integer.valueOf(getParam(params,"size","10"));
		String sort =  getParam(params,"sort","");
		String order =  getParam(params,"order");
		String categoryCode=super.getParam(params, "categoryCode");
		if(StringUtilsEx.isNotBlank(categoryCode)){
			filters.add(new SearchFilter("categoryCode", SearchFilter.Operator.LIKES,categoryCode));
		}
		filters.add(new SearchFilter("checkStatus", SearchFilter.Operator.EQ, Status.enabled));
		Specification<Article> spec= DynamicSpecifications.bySearchFilter(filters, Article.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,Article.class);
		Page<Article> pag = periodicalService.findAll(spec, pageRequest);
		List<ArticleVO> vos = Lists.newArrayList();
		if(pag!=null&&pag.hasContent()){
			for(Article po:pag.getContent()){
				vos.add(new ArticleVO(po));
			}
		}
		Page<ArticleVO> docVos  =new PageImpl<ArticleVO>(vos,pageRequest,pag.getTotalElements());
		setVariable("docs",docVos,env);
		super.renderBody(env,body);
	}
	@Override
	protected void verifyParameters(Map<?, ?> params) throws TemplateModelException {
		  // 检查是否传递参数，此指令禁止传参！
	}

}
