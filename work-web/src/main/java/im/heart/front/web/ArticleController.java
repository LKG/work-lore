package im.heart.front.web;

import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.entity.Article;
import im.heart.cms.service.ArticleService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.usercore.service.FrameUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Controller
public class ArticleController extends AbstractController {
    protected static final String apiVer = "/article";
    protected static final String VIEW_LIST="front/article/article_list";
    protected static final String VIEW_DETAILS="front/article/article_details";
    @Autowired
    private ArticleService articleService;

    @Autowired
    private FrameUserFollowService frameUserFollowService;

    @GetMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        this.updateHitsById(id);
        Article po = this.articleService.findById(id);
        System.out.println(po.getCategoryId());
        System.out.println(po.getId());
        List<ArticleDTO> nearList=this.articleService.queryNearById(po.getId(),po.getCategoryId());
        model.put("lastArticle",nearList);
        super.success(model, po);
        return new ModelAndView(VIEW_DETAILS);
    }
    @GetMapping(apiVer+"s")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false,defaultValue = "") String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.ORDER_DESC) String order,
                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("isPub", SearchFilter.Operator.EQ,Boolean.TRUE));
        filters.add(new SearchFilter("isDeleted", SearchFilter.Operator.EQ,Boolean.FALSE));
        Specification<Article> spec= DynamicSpecifications.bySearchFilter(filters, Article.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Article.class);
        Page<Article> pag = this.articleService.findAll(spec, pageRequest);
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }
    public void updateHitsById(BigInteger id){
        this.articleService.addUpdateHitsTask(id);
    }
}
