package im.heart.admin.usercore.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.PeriodicalPackage;
import im.heart.usercore.entity.FramePermission;
import im.heart.usercore.entity.FrameResource;
import im.heart.usercore.service.FramePermissionService;
import im.heart.usercore.service.FrameResourceService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class FrameResourceController extends AbstractController {
	protected static final String apiVer = "/resource";
	protected static final String VIEW_LIST="admin/usercore/resource_list";
	protected static final String VIEW_CREATE="admin/usercore/resource_create";
	protected static final String VIEW_DETAILS="admin/usercore/resource_details";
	protected static final String VIEW_TREE="admin/usercore/resource_tree";
	@Autowired
	private FrameResourceService frameResourceService;
	
	@Autowired
	private FramePermissionService framePermissionService;
	
	@RequiresPermissions("func:view")
	@RequestMapping(value = apiVer+"/tree")
	public ModelAndView treeView(ModelMap model){
		super.success(model);
		return new ModelAndView(VIEW_TREE);
	}
	@RequiresPermissions("func:create")
	@RequestMapping(value={apiVer+"/{parentId}/add"})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable() BigInteger parentId,
			ModelMap model){
		FrameResource po=new FrameResource();
		if(parentId!=null&&parentId.intValue()!=0){
			Optional<FrameResource> optional = this.frameResourceService.findById(parentId);
			if(optional.isPresent()){
				po.setParentId(parentId);
				po.setParentName(optional.get().getResourceName());
			}
		}
		super.success(model, po);
		return new ModelAndView(VIEW_CREATE);
	}
	@RequiresPermissions("func:create")
	@RequestMapping(value = apiVer + "/save", method = RequestMethod.POST)
	public ModelAndView registerAjaxUser(HttpServletRequest request,
			@Valid @ModelAttribute(RequestResult.RESULT) FrameResource frameResource,
			BindingResult result, 
			@RequestParam(value = "format", required = false) String format,
			RedirectAttributes redirectAttributes,ModelMap model) {
		String permissionIds = frameResource.getPermissionIds();
		if(StringUtilsEx.isNotBlank(permissionIds)){
			String[] ids = StringUtilsEx.split(permissionIds,",");
			Set<BigInteger> permissions= Sets.newHashSet();
			for(String permissionId:ids){
				permissions.add(new BigInteger(permissionId));
			}
			List<FramePermission> pers = this.framePermissionService.findAllById(permissions);
			if(pers!=null){
				frameResource.setPermissionStr(JSON.toJSONString(pers));
			}
		}
		FrameResource entity = this.frameResourceService.save(frameResource);
		redirectAttributes.addFlashAttribute(RequestResult.RESULT, entity);
		super.success(model,entity);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer + "/checkCode")
	protected ModelAndView checkResourceCode(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "resourceCode", required = false) String resourceCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if (StringUtilsEx.isBlank(resourceCode)) {
			super.fail(model);
			return new ModelAndView(RESULT_PAGE);
		}
		boolean exists = this.frameResourceService.existsResourceCode(resourceCode);
		if (exists) {
			super.fail(model);
			return new ModelAndView(RESULT_PAGE);
		}
		super.success(model);
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 * 
	 * 查询所有
	 * @param request
	 * @param response
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param model
	 * @return
	 */
	@RequiresPermissions("func:view")
	@RequestMapping(apiVer+"s")
	public ModelAndView lists(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false, defaultValue="resourceSort" ) String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<FrameResource> spec= DynamicSpecifications.bySearchFilter(request, FrameResource.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, FrameResource.class);
		Page<FrameResource> pag = this.frameResourceService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}
	@RequiresPermissions("func:view")
	@RequestMapping(value = apiVer+"/{id}")
	protected ModelAndView findById(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		Optional<FrameResource> optional = this.frameResourceService.findById(id);
		if(optional.isPresent()){
			super.success(model, optional.get());
		}
		return new ModelAndView(VIEW_DETAILS);
	}
	@RequiresPermissions("func:delete")
	@RequestMapping(value = apiVer+"/{ids}/delete",method = RequestMethod.POST)
	protected ModelAndView batchDelete(
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			this.frameResourceService.deleteById(id);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
