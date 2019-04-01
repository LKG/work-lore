package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.PeriodicalCategory;
import im.heart.media.service.PeriodicalCategoryService;
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
@RequestMapping("/api")
public class PeriodicalCategoryController extends AbstractController {
	protected static final String apiVer = "/periodical/category";
	@Autowired
	private PeriodicalCategoryService periodicalCategoryService;

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
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,PeriodicalCategory.class);
		Page<PeriodicalCategory> pag = this.periodicalCategoryService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findByKey(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = RequestResult.ACCESS_TOKEN , required = false) String token,
			 @PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		PeriodicalCategory po = this.periodicalCategoryService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
