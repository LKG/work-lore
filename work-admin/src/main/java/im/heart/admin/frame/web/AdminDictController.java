package im.heart.admin.frame.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.frame.entity.FrameDict;
import im.heart.frame.service.FrameDictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc : 数据字典
 */
@Controller
@RequestMapping("/admin")
public class AdminDictController extends AbstractController {

    protected static final String apiVer = "/dict";
    protected static final String VIEW_LIST="admin/frame/dict_list";
    protected static final String VIEW_CREATE="admin/frame/dict_create";
    protected static final String VIEW_DETAILS="admin/frame/dict_details";

    @Autowired
    private FrameDictService frameDictService;

    /**
     *
     * @Desc：查询所有
     * @param request
     * @param response
     * @param jsoncallback
     * @param page
     * @param size
     * @param sort
     * @param order
     * @param token
     * @param model
     * @return
     */
    @RequiresPermissions("dict:view")
    @RequestMapping(apiVer+"s")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false ,defaultValue = "createTime") String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        Specification<FrameDict> spec= DynamicSpecifications.bySearchFilter(request, FrameDict.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, FrameDict.class);
        Page<FrameDict> pag = this.frameDictService.findAll(spec, pageRequest);
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }

    @RequestMapping(value = apiVer + "/checkCode")
    protected ModelAndView checkCode(
            @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = "dictCode", required = false) String dictCode,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws Exception {
        if (StringUtils.isBlank(dictCode)) {
            super.fail(model, false);
            return new ModelAndView(RESULT_PAGE);
        }
        boolean exists = this.frameDictService.exists(dictCode);
        if (exists) {
            super.fail(model,false);
            return new ModelAndView(RESULT_PAGE);
        }
        super.success(model, true);
        return new ModelAndView(RESULT_PAGE);
    }
    @RequiresPermissions("dict:create")
    @RequestMapping(value = apiVer+"/add")
    public ModelAndView createFrom(HttpServletRequest request, HttpServletResponse response,
           @ModelAttribute(RequestResult.RESULT) FrameDict frameDict,
           @RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
           ModelMap model) {
        super.success(model, frameDict);
        return new ModelAndView(VIEW_CREATE);
    }
    @RequiresPermissions("dict:create")
    @RequestMapping(value = apiVer+"/save",method = RequestMethod.POST)
    protected ModelAndView saveOrUpdate(
            @Valid @ModelAttribute(RequestResult.RESULT) FrameDict frameDict,
            BindingResult result,
            @RequestParam(value = "format", required = false) String format,
            HttpServletRequest request,
            ModelMap model) {
        this.frameDictService.save(frameDict);
        super.success(model);
        return new ModelAndView(VIEW_SUCCESS);
    }
    @RequiresPermissions("dict:view")
    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
            @PathVariable BigInteger id,
            HttpServletRequest request,
            ModelMap model) {
        FrameDict po = this.frameDictService.findById(id);
        super.success(model, po);
        return new ModelAndView(VIEW_DETAILS);
    }

    @RequiresPermissions("dict:delete")
    @RequestMapping(value = apiVer+"/{ids}/delete",method = RequestMethod.POST)
    protected ModelAndView batchDelete(
            @RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
            @PathVariable BigInteger[] ids,
            HttpServletRequest request,
            ModelMap model) {
        for(BigInteger id:ids){
            this.frameDictService.deleteById(id);
        }
        super.success(model);
        return new ModelAndView(VIEW_SUCCESS);
    }
}