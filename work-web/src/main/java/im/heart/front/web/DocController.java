package im.heart.front.web;

import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalImgService;
import im.heart.media.service.PeriodicalService;
import im.heart.media.vo.PeriodicalVO;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.entity.FrameUserFollow;
import im.heart.usercore.service.FrameUserFollowService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class DocController extends AbstractController {
    protected static final String apiVer = "/doc";
    protected static final String VIEW_LIST="front/doc/doc_list";
    protected static final String VIEW_DETAILS="front/doc/doc_details";

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private FrameUserFollowService frameUserFollowService;

    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        this.updateHitsById(id);
        Periodical po = this.periodicalService.findById(id);
        PeriodicalVO vo=new PeriodicalVO(po);
        FrameUser user= SecurityUtilsHelper.getCurrentUser();
        if(user!=null){
            Optional<FrameUserFollow> optional= this.frameUserFollowService.findByUserIdAndRelateIdAndType(user.getUserId(),po.getId(),po.getPeriodicalType());
            if(optional.isPresent()){
                vo.setIsCollect(Boolean.TRUE);
            }
            if(BigDecimal.ZERO.compareTo(po.getFinalPrice())==0||user.isExpiry()){
                vo.setAllowDown(Boolean.TRUE);
            }
        }
        super.success(model,vo );
        return new ModelAndView(VIEW_DETAILS);
    }
    @RequestMapping(apiVer+"s")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "16") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("checkStatus", SearchFilter.Operator.EQ, Status.enabled));
        Specification<Periodical> spec= DynamicSpecifications.bySearchFilter(filters, Periodical.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,Periodical.class);
        Page<Periodical> pag = this.periodicalService.findAll(spec, pageRequest);
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
    public void updateHitsById(BigInteger id){
        this.periodicalService.addUpdateHitsTask(id);
    }
    @RequestMapping(value = apiVer+"/{id}/praise")
    protected ModelAndView praise(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        this.periodicalService.addUpdateRateTimesTask(id);
        super.success(model);
        return new ModelAndView(VIEW_DETAILS);
    }
    @RequiresAuthentication
    @RequestMapping(value = apiVer+"/{id}/collect")
    protected ModelAndView collect(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        BigInteger userId= SecurityUtilsHelper.getCurrentUserId();
        Periodical materialPeriodical=this.periodicalService.findById(id);
        Optional<FrameUserFollow> optional= this.frameUserFollowService.findByUserIdAndRelateIdAndType(userId,id,materialPeriodical.getPeriodicalType());
        if(!optional.isPresent()){
            FrameUserFollow userFollow=new FrameUserFollow();
            userFollow.setRelateId(id);
            userFollow.setType(materialPeriodical.getPeriodicalType());
            userFollow.setUserId(userId);
            userFollow.setStatus(Status.enabled);
            userFollow.setItemTitle(materialPeriodical.getPeriodicalName());
            userFollow.setItemImgUrl(materialPeriodical.getCoverImgUrl());
            this.frameUserFollowService.save(userFollow);
        }
        super.success(model,true);
        return new ModelAndView(VIEW_DETAILS);
    }
}
