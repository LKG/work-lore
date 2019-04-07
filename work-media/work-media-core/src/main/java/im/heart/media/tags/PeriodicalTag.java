package im.heart.media.tags;

import com.google.common.collect.Lists;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import im.heart.common.context.ContextManager;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.tags.BaseDirective;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import im.heart.media.vo.PeriodicalVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class PeriodicalTag extends BaseDirective {
	protected static final Logger logger = LoggerFactory.getLogger(PeriodicalTag.class);


	@Override
	public void render(Environment env, Map<?, ?> params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		PeriodicalService periodicalService=	ContextManager.getBean(PeriodicalService.class);
		final Collection<SearchFilter> filters=new HashSet<SearchFilter>();
		int page =  Integer.valueOf(getParam(params,"page","1"));
		int size =  Integer.valueOf(getParam(params,"size","10"));
		String sort =  getParam(params,"sort","");
		String order =  getParam(params,"order");
		String categoryCode =  getParam(params,"categoryCode");
		filters.add(new SearchFilter("checkStatus", SearchFilter.Operator.EQ, Status.enabled));
		filters.add(new SearchFilter("categoryCode", SearchFilter.Operator.LIKES,categoryCode));
		Specification<Periodical> spec= DynamicSpecifications.bySearchFilter(filters, Periodical.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,Periodical.class);
		Page<Periodical> pag = periodicalService.findAll(spec, pageRequest);
		if(pag!=null&&pag.hasContent()){
			List<PeriodicalVO> vos = Lists.newArrayList();
			for(Periodical po:pag.getContent()){
				vos.add(new PeriodicalVO(po));
			}
			Page<PeriodicalVO> docVos  =new PageImpl<PeriodicalVO>(vos,pageRequest,pag.getTotalElements());
		setVariable("docs",docVos,env);
		super.renderBody(env,body);
		}
	}
	@Override
	protected void verifyParameters(Map<?, ?> params) throws TemplateModelException {
		  // 检查是否传递参数，此指令禁止传参！
        if (!params.isEmpty()) {
            throw new TemplateModelException("This directive doesn't allow parameters.");
        }
	}

}
