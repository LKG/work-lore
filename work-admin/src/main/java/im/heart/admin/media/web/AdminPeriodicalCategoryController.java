package im.heart.admin.media.web;

import im.heart.cms.entity.AdPosition;
import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.service.ServiceException;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.PeriodicalCategory;
import im.heart.media.service.PeriodicalCategoryService;
import org.apache.commons.lang3.StringUtils;
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
public class  AdminPeriodicalCategoryController extends AbstractController {
	protected static final String apiVer = "/periodical/category";
	protected static final String VIEW_LIST="admin/periodical/category/list";
	protected static final String VIEW_TABLE="admin/periodical/category/table";
	protected static final String VIEW_DETAILS="admin/periodical/category/details";
	protected static final String VIEW_CREATE="admin/periodical/category/create";
	@RequestMapping(apiVer)
	public ModelAndView home(ModelMap model) {
		super.success(model);
		return new ModelAndView(VIEW_LIST,model);
	}
	@Autowired
	private PeriodicalCategoryService periodicalCategoryService;
	
	@RequestMapping(value = apiVer + "/checkCode", method = RequestMethod.GET)
	protected ModelAndView checkUserName(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "categoryCode", required = false) String categoryCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if (StringUtils.isBlank(categoryCode)) {
			super.fail(model);
			model.put(RequestResult.RESULT, false);
			return new ModelAndView(RESULT_PAGE);
		}
		boolean exit = this.periodicalCategoryService.exit(categoryCode);
		if (exit) {
			super.fail(model);
			model.put(RequestResult.RESULT, false);
			return new ModelAndView(RESULT_PAGE);
		}
		super.success(model);
		model.put(RequestResult.RESULT, true);
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 * 
	 * @功能说明：查询所有
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
	@RequestMapping(apiVer+"s")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<PeriodicalCategory> spec= DynamicSpecifications.bySearchFilter(request, PeriodicalCategory.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, PeriodicalCategory.class);
		Page<PeriodicalCategory> pag = this.periodicalCategoryService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_TABLE);
	}
	@RequestMapping(value = apiVer + "/subGeneral")
	public ModelAndView registerAjaxUser(HttpServletRequest request,
			@Valid @ModelAttribute(RequestResult.RESULT) PeriodicalCategory periodicalCategory,
			BindingResult result, 
			@RequestParam(value = "format", required = false) String format,
			ModelMap model) throws ServiceException {
		PeriodicalCategory entity = this.periodicalCategoryService.save(periodicalCategory);
		super.success(model,entity);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findByKey(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			 @PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		Optional<PeriodicalCategory> optional = this.periodicalCategoryService.findById(id);
		if(optional.isPresent()){
			super.success(model, optional.get());
		}
		return new ModelAndView(VIEW_DETAILS);
	}
	@RequestMapping(value={apiVer+"/{parentId}/add"})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                            @PathVariable() BigInteger parentId,
                            ModelMap model, PeriodicalCategory po){
		if(parentId!=null&&parentId.intValue()!=0){
			Optional<PeriodicalCategory> optional = this.periodicalCategoryService.findById(parentId);
			if(optional.isPresent()){
				PeriodicalCategory parentCategory =optional.get();
				po.setLevel(parentCategory.getLevel()+1);
				po.setParentId(parentCategory.getCategoryId());
				po.setParentName(parentCategory.getCategoryName());
			}
		}
		super.success(model, po);
		return new ModelAndView(VIEW_CREATE);
	}
}
