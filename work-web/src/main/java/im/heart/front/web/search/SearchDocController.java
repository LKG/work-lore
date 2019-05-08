package im.heart.front.web.search;

import im.heart.cms.entity.Article;
import im.heart.cms.service.ArticleService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.PeriodicalContent;
import im.heart.media.service.PeriodicalContentService;
import im.heart.media.service.PeriodicalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 *
 * @author gg
 * @desc 文档搜索
 */
@Controller
public class SearchDocController extends AbstractController {
    protected static final String apiVer = "/index";

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private PeriodicalContentService periodicalContentService;

    @RequestMapping(value={"/pq",apiVer+"/pq"}  ,method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                             @RequestParam(value = "q", required = false) String q,
                             @RequestParam(value = "qt", required = false,defaultValue = "1") Integer qt,
                             @RequestParam(value = "sort", required = false,defaultValue = "isTop,pushTime") String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.ORDER_DESC) String order,
                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        if(StringUtils.isBlank(q)){
            super.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
            return  new ModelAndView(RESULT_PAGE);
        }
        if(size> CommonConst.Page.MAX_SIZE){
            size= CommonConst.Page.MAX_SIZE;
        }
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("isPub", SearchFilter.Operator.EQ,Boolean.TRUE));
        filters.add(new SearchFilter("isDeleted", SearchFilter.Operator.EQ,Boolean.FALSE));
        SearchType searchType=SearchType.findByIntValue(qt);
        switch (searchType){
            case topical:
                //主题
                break;
            case keyword:
                //关键词
                filters.add(new SearchFilter("seoKeywords", SearchFilter.Operator.LIKE,q));
                break;
            case title:
                //篇名
                filters.add(new SearchFilter("periodicalName", SearchFilter.Operator.LIKE,q));
                break;
            case fulltext:
                //全文
            case author:
                //作者
                filters.add(new SearchFilter("author", SearchFilter.Operator.LIKE,q));
                break;
            case organ:
                //单位
                break;
            case summary:
                //摘要
                filters.add(new SearchFilter("summary", SearchFilter.Operator.LIKE,q));
                break;
            case cited:
                //被引文献
                break;
            case clc:
                //中图分类号
                break;
            case source:
                //文献来源
                filters.add(new SearchFilter("source", SearchFilter.Operator.LIKE,q));
                break;
            default:
        }
        Specification<Periodical> spec= DynamicSpecifications.bySearchFilter(filters, Periodical.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Periodical.class);
        Page<Periodical> pag = this.periodicalService.findAll(spec,pageRequest);
        model.put("q",q);
        model.put("qu","pq");
        model.put("qt",qt);
        super.success(model,pag);
        return new ModelAndView("front/search/search_doc_list");
    }
}
