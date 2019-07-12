package im.heart.admin.media.web;

import im.heart.cms.entity.AdPosition;
import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.media.entity.PeriodicalConfig;
import im.heart.media.service.PeriodicalConfigService;
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
public class  AdminPeriodicalConfigController extends AbstractController {
	protected static final String apiVer = "/periodical/config";
	protected static final String VIEW_LIST="admin/periodical/config/list";
	protected static final String VIEW_CREATE="admin/periodical/config/create";
	protected static final String VIEW_DETAILS="admin/periodical/config/details";
	@Autowired
	private PeriodicalConfigService periodicalConfigService;

	@RequestMapping(apiVer)
	public ModelAndView home(ModelMap model) {
		super.success(model);
		return new ModelAndView(VIEW_LIST,model);
	}

	@RequestMapping(apiVer+"/add")
	public ModelAndView create(ModelMap model) {
		super.success(model);
		return new ModelAndView(VIEW_CREATE,model);
	}
	@RequestMapping(value = apiVer + "/success", method = RequestMethod.GET)
	protected ModelAndView success(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer + "/checkName", method = RequestMethod.GET)
	protected ModelAndView checkUserName(
			@RequestParam(value = "jsoncallback", required = false) String jsoncallback,
			@RequestParam(value = "cityId", required = false) String cityId,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if (StringUtilsEx.isBlank(cityId)|| StringUtilsEx.isBlank(year)|| StringUtilsEx.isBlank(type)) {
			super.fail(model);
			model.put(RequestResult.RESULT, false);
			return new ModelAndView(RESULT_PAGE);
		}
		
		super.success(model);
		model.put(RequestResult.RESULT, true);
		return new ModelAndView(RESULT_PAGE);
	}
	@RequestMapping(value = apiVer+"/save",method = RequestMethod.POST)
	protected ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(RequestResult.RESULT) PeriodicalConfig periodicalConfig,
			BindingResult result,
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			HttpServletRequest request,
			ModelMap model) {
		
		if(periodicalConfig.getId()==null){
			String cityId = periodicalConfig.getCityId();
			String type = periodicalConfig.getType();
			Integer year = periodicalConfig.getYear();
			boolean exits=this.periodicalConfigService.exits(cityId,year,type);
			if(exits){
				ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_EXIT);
				super.fail(model,responseError);
				return new ModelAndView(VIEW_DETAILS);
			}
		}
		PeriodicalConfig po = this.periodicalConfigService.save(periodicalConfig);
		super.success(model,po);
		return new ModelAndView(VIEW_SUCCESS);
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
			@RequestParam(value = "sort", required = false,defaultValue = "createTime") String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		
		Specification<PeriodicalConfig> spec= DynamicSpecifications.bySearchFilter(request, PeriodicalConfig.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,PeriodicalConfig.class);
		Page<PeriodicalConfig> pag = this.periodicalConfigService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findById(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		Optional<PeriodicalConfig> optional = this.periodicalConfigService.findById(id);
		if(optional.isPresent()){
			super.success(model, optional.get());
		}
		return new ModelAndView(VIEW_DETAILS);
	}
	@RequestMapping(value = apiVer+"/{ids}/disabled",method = RequestMethod.POST)
	protected ModelAndView deleted(
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			this.periodicalConfigService.deleteById(id);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
