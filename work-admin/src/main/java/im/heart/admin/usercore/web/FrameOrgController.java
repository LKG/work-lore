package im.heart.admin.usercore.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.log.OptLog;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.PeriodicalConfig;
import im.heart.usercore.entity.FrameOrg;
import im.heart.usercore.service.FrameOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;


/**
 * 
 * @author lkg
 * @desc 机构表查看控制器
 */
@Controller
@RequestMapping("/admin")
public class FrameOrgController extends AbstractController {
	
	protected static final String apiVer = "/org";
	protected static final String VIEW_LIST="admin/usercore/org_list";
	protected static final String VIEW_TREE="admin/usercore/org_tree";
	protected static final String VIEW_DETAILS="admin/usercore/org_details";
	protected static final String VIEW_CREATE="admin/usercore/org_create";
	@Autowired
	private FrameOrgService frameOrgService;
	@InitBinder  
    public void initBinder(DataBinder binder) {  
     
    }  
	@RequiresPermissions("org:view")
	@RequestMapping(value = apiVer+"/tree")
	public ModelAndView treeView(ModelMap model){
		super.success(model);
		return new ModelAndView(VIEW_TREE);
	}
	@RequestMapping(value = apiVer + "/checkCode")
	protected ModelAndView checkCode(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if (StringUtilsEx.isBlank(orgCode)) {
			super.fail(model);
			return new ModelAndView(RESULT_PAGE);
		}
		boolean isExit = this.frameOrgService.existsOrgCode(orgCode);
		if (isExit) {
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
	 * @param jsoncallback
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param model
	 * @return
	 */
	@RequiresPermissions("org:view")
	@RequestMapping(value = apiVer+"s" )
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false ,defaultValue="code") String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<FrameOrg> spec= DynamicSpecifications.bySearchFilter(request, FrameOrg.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, FrameOrg.class);
		Page<FrameOrg> pag = this.frameOrgService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}
	/**
	 * 根据Id 查询记录
	 * @param jsoncallback
	 * @param id
	 * @param token
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("org:view")
	@RequestMapping(value = apiVer+"/{id}")
	protected ModelAndView findById(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable BigInteger id,
			@RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request,
			ModelMap model) {
		Optional<FrameOrg> optional = this.frameOrgService.findById(id);
		if(optional.isPresent()){
			FrameOrg po=optional.get();
			if(!po.isRoot()){
				Optional<FrameOrg> optionalParent = this.frameOrgService.findById(po.getParentId());
				if(optionalParent.isPresent()){
					po.setParentName(optionalParent.get().getName());
				}
			}
			super.success(model, po);
		}

		return new ModelAndView(VIEW_DETAILS);
	}
	
	/**
	 * 修改记录
	 * @param frameOrg
	 * @param result
	 * @param format
	 * @param request
	 * @param model
	 * @return
	 */
	@OptLog(detail="机构新增",type="org")
	@RequiresPermissions("org:create")
	@RequestMapping(value = apiVer+"/save",method = RequestMethod.POST)
	protected ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(RequestResult.RESULT) FrameOrg frameOrg,
			BindingResult result, 
			@RequestParam(value = "format", required = false) String format,
			HttpServletRequest request,
			ModelMap model) {
		this.frameOrgService.save(frameOrg);
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	
	@RequiresPermissions("org:create")
	@RequestMapping(value={apiVer+"/{parentId}/add"})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable() BigInteger parentId,
			ModelMap model){
		FrameOrg po=new FrameOrg();
		if(parentId!=null&&!BigInteger.ZERO.equals(parentId)){
			Optional<FrameOrg> optional = this.frameOrgService.findById(parentId);
			if(optional.isPresent()){
				FrameOrg parentOrg=optional.get();
				po.setLevel(parentOrg.getLevel()+1);
				po.setParentId(parentOrg.getId());
				po.setType(parentOrg.getType());
				po.setParentName(parentOrg.getName());
			}
		}
		super.success(model, po);
		return new ModelAndView(VIEW_CREATE);
	}
	@RequiresPermissions("org:delete")
	@RequestMapping(value = apiVer+"/{ids}/delete",method = RequestMethod.POST)
	protected ModelAndView batchDelete(
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			this.frameOrgService.deleteById(id);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	
}
