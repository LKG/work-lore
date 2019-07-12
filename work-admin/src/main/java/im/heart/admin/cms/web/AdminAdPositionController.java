package im.heart.admin.cms.web;

import im.heart.cms.entity.AdPosition;
import im.heart.cms.service.AdPositionService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.log.OptLog;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameOrg;
import im.heart.usercore.vo.FrameUserVO;
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
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminAdPositionController extends AbstractController {
	protected static final String apiVer = "/adPosition";
	protected static final String VIEW_LIST="admin/cms/ad_position_list";
	protected static final String VIEW_DETAILS="admin/cms/ad_position_details";
	protected static final String VIEW_CREATE="admin/cms/ad_position_create";
	@Autowired
	private AdPositionService adPositionService;
	
	@RequestMapping(value = apiVer+"/{id}")
	protected ModelAndView findById(
			@RequestParam(value = "jsoncallback", required = false) String jsoncallback,
			@PathVariable BigInteger id,
			@RequestParam(value = "access_token", required = false) String token,
			HttpServletRequest request,
			ModelMap model) {
		Optional<AdPosition> optional = this.adPositionService.findById(id);
		if(optional.isPresent()){
			super.success(model, optional.get());
		}
		return new ModelAndView(VIEW_DETAILS);
	}
	@RequestMapping(apiVer+"s")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value =  CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<AdPosition> spec= DynamicSpecifications.bySearchFilter(request, AdPosition.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, AdPosition.class);
		Page<AdPosition> pag = this.adPositionService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}

	@OptLog(detail="文章新增",type="adm")
	@RequiresPermissions("adPosition:create")
	@RequestMapping(value = apiVer+"/save",method = RequestMethod.POST)
	protected ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(CommonConst.RequestResult.RESULT) AdPosition adPosition,
			BindingResult result,
			@RequestParam(value = "format", required = false) String format,
			HttpServletRequest request,
			ModelMap model) {
		FrameUserVO userVo = SecurityUtilsHelper.getCurrentUser();
		if (userVo == null) {
			super.fail(model, new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
			return new ModelAndView(RESULT_PAGE);
		}
		this.adPositionService.save(adPosition);
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}

	@RequiresPermissions("adPosition:create")
	@RequestMapping(value={apiVer+"/add"})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model){
		super.success(model);
		return new ModelAndView(VIEW_CREATE);
	}
}
