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
import im.heart.frame.service.QQGroupService;
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

@Controller
public class SearchController  extends AbstractController {
    protected static final String apiVer = "/index";

    enum SearchType{
        topical(0,"topical","主题"),
        keyword(1,"keyword","关键词"),
        title(2,"title","篇名"),
        fulltext(3,"fulltext","全文"),
        author(4,"author","作者"),
        organ(5,"organ","单位"),
        summary(7,"summary","摘要"),
        cited(8,"cited","被引文献"),
        source(9,"source","文献来源"),
        ;
        public String code;
        public int value;
        public final String info;
        SearchType(int value, String code, String info) {
            this.code = code;
            this.value = value;
            this.info = info;
        }
    }
    @Autowired
    QQGroupService qqGroupService;

    @Autowired
    ArticleService articleService;
    @RequestMapping(value={"/q",apiVer+"/q"}  ,method = RequestMethod.GET)
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
        switch (qt){
            case 0:
                break;
            case 1:
                filters.add(new SearchFilter("seoKeywords", SearchFilter.Operator.LIKE,q));
                break;
            case 2:
                filters.add(new SearchFilter("title", SearchFilter.Operator.LIKE,q));
                break;
            case 3:
                filters.add(new SearchFilter("content", SearchFilter.Operator.LIKE,q));
                break;
            case 4:
                filters.add(new SearchFilter("author", SearchFilter.Operator.LIKE,q));
                break;
            case 5:
                break;
            case 6:
                filters.add(new SearchFilter("author", SearchFilter.Operator.LIKE,q));
                break;
            case 7:
                filters.add(new SearchFilter("summary", SearchFilter.Operator.LIKE,q));
                break;
            case 8:
                break;
            case 9:
                filters.add(new SearchFilter("source", SearchFilter.Operator.LIKE,q));
                break;
            default:
        }
        Specification<Article> spec= DynamicSpecifications.bySearchFilter(filters, Article.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,Article.class);
        Page<Article> pag = this.articleService.findAll(spec, pageRequest);
        model.put("q",q);
        model.put("qt",qt);
        super.success(model,pag);
        return new ModelAndView("front/search/search_list");
    }
}
