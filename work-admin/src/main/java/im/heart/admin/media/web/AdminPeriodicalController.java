package im.heart.admin.media.web;

import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

@Controller
@RequestMapping("/admin")
public class AdminPeriodicalController extends AbstractController {
	protected static final String apiVer = "/periodical";
	protected static final String VIEW_LIST="admin/periodical/list";
	protected static final String VIEW_DETAILS="admin/periodical/details";
	@Autowired
	private PeriodicalService periodicalService;

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
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false,defaultValue = "createTime") String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<Periodical> spec= DynamicSpecifications.bySearchFilter(request, Periodical.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Periodical.class);
		Page<Periodical> pag = this.periodicalService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findByKey(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		Periodical po = this.periodicalService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_DETAILS);
	}
	@RequestMapping(value = apiVer+"/{ids}/disabled",method = RequestMethod.POST)
	protected ModelAndView deleted(
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			this.periodicalService.deleteById(id);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{ids}/publish",method = RequestMethod.POST)
	protected ModelAndView publish(
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger periodicalId:ids){
			this.periodicalService.updateStatusByPeriodicalId(periodicalId, Status.enabled);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{ids}/pull",method = RequestMethod.POST)
	protected ModelAndView disabled(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger periodicalId:ids){
			this.periodicalService.updateStatusByPeriodicalId(periodicalId, Status.disabled);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
