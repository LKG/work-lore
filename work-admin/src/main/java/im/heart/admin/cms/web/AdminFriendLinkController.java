package im.heart.admin.cms.web;

import im.heart.cms.entity.FriendLink;
import im.heart.cms.service.FriendLinkService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
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

@Controller
@RequestMapping("/admin")
public class AdminFriendLinkController extends AbstractController {
	protected static final String apiVer = "/friendlink";
	protected static final String VIEW_LIST="admin/cms/friendlink_list";
	protected static final String VIEW_DETAILS="admin/cms/friendlink_details";
	protected static final String VIEW_CREATE="admin/cms/friendlink_create";
	@Autowired
	private FriendLinkService friendLinkService;
	
	
	@RequiresPermissions("friendlink:view")
	@RequestMapping(value = apiVer+"/{id}")
	protected ModelAndView findById(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable BigInteger id,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request,
			ModelMap model) {
		FriendLink po = this.friendLinkService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_DETAILS);
	}
	
	@RequiresPermissions("friendlink:create")
	@RequestMapping(value={apiVer+"/add"})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model){
		super.success(model);
		return new ModelAndView(VIEW_CREATE);
	}
	@RequiresPermissions("friendlink:view")
	@RequestMapping(apiVer+"s")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<FriendLink> spec= DynamicSpecifications.bySearchFilter(request, FriendLink.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, FriendLink.class);
		Page<FriendLink> pag = this.friendLinkService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}
	
	/**
	 * @Desc：修改记录
	 * @param friendLink
	 * @param result
	 * @param format
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("area:create")
	@RequestMapping(value = apiVer+"/save",method = RequestMethod.POST)
	protected ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(CommonConst.RequestResult.RESULT) FriendLink friendLink,
			BindingResult result, 
			@RequestParam(value = "format", required = false) String format,
			HttpServletRequest request,
			ModelMap model) {
		this.friendLinkService.save(friendLink);
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
