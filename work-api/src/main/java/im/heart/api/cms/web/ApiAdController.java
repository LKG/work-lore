package im.heart.api.cms.web;

import im.heart.cms.entity.Ad;
import im.heart.cms.service.AdService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
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
import java.util.Collection;

/**
 *
 * @author gg
 * @desc  广告接口
 */
@Controller
public class ApiAdController extends AbstractController {
	protected static final String apiVer = "/api/v1/ad";
	@Autowired
	private AdService adService;
	protected static final String VIEW_LIST="cms/ad_list";
	protected static final String VIEW_DETAILS="cms/ad_details";
	
	/**
	 * @功能说明：分页查询
	 * @param jsoncallback
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=apiVer+"s" ,method = RequestMethod.GET)
	protected ModelAndView lists(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false ,defaultValue = "adSort") String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
		filters.add(new SearchFilter("adState", SearchFilter.Operator.EQ,Ad.AdState.ACTIVE));
		Specification<Ad> spec=DynamicSpecifications.bySearchFilter(filters, Ad.class);
		PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order, Ad.class);
		Page<Ad> pag = this.adService.findAll(spec, pageRequest);
		super.success(model, pag);
		return new ModelAndView(VIEW_LIST);
	}
	/**
	 * @功能说明：根据Id查询信息
	 * @param jsoncallback
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=apiVer+"/{id}" ,method = RequestMethod.GET)
	protected ModelAndView findById(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable BigInteger id,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		Ad po = this.adService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_DETAILS);
	}
}
