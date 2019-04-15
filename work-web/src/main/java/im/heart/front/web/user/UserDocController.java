package im.heart.front.web.user;

import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import im.heart.media.vo.PeriodicalVO;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.service.FrameUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Controller
public class UserDocController extends AbstractController {
    protected static final String apiVer = "/user/doc";
    protected static final String VIEW_LIST="userinfo/doc_list";
    protected static final String VIEW_DETAILS="userinfo/doc_details";

    @Autowired
    private PeriodicalService materialPeriodicalService;

    @Autowired
    private FrameUserFollowService frameUserFollowService;

    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        Periodical po = this.materialPeriodicalService.findById(id);
        PeriodicalVO vo=new PeriodicalVO(po);
        super.success(model, vo);
        return new ModelAndView(VIEW_DETAILS);
    }
    @RequestMapping(apiVer+"s")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        BigInteger userId= SecurityUtilsHelper.getCurrentUser().getUserId();
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ,userId));
        Specification<Periodical> spec= DynamicSpecifications.bySearchFilter(filters, Periodical.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,Periodical.class);
        Page<Periodical> pag = this.materialPeriodicalService.findAll(spec, pageRequest);
        if(pag!=null&&pag.hasContent()){
            List<PeriodicalVO> vos = Lists.newArrayList();
            for(Periodical po:pag.getContent()){
                vos.add(new PeriodicalVO(po));
            }
            Page<PeriodicalVO> docVos  =new PageImpl<PeriodicalVO>(vos,pageRequest,pag.getTotalElements());
            super.success(model,docVos);
            return new ModelAndView(VIEW_LIST);
        }
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }
}
