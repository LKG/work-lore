package im.heart.cms.web;

import im.heart.cms.entity.Notice;
import im.heart.cms.service.NoticeService;
import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc 公告
 */
@Controller
public class ApiNoticeController extends AbstractController {
	protected static final String apiVer = "/api/v1/notice";
	@Autowired
	private NoticeService noticeService;
	protected static final String VIEW_LIST="admin/frameNotice/list";
	protected static final String VIEW_CREATE="admin/frameNotice/create";
	protected static final String VIEW_UPDATE="admin/frameNotice/update";
	protected static final String VIEW_DETAILS="admin/frameNotice/details";
	protected static final String VIEW_SUCCESS = "admin/frameNotice/success";
	
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
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		Specification<Notice> spec=DynamicSpecifications.bySearchFilter(request, Notice.class);
		PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order, Notice.class);
		Page<Notice> pag = this.noticeService.findAll(spec, pageRequest);
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
			ModelMap model) throws Exception {
		Notice po = this.noticeService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_DETAILS);
	}
	/**
	 * @功能说明：统计记录数
	 * @param jsoncallback
	 * @param request
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(value = apiVer+"/count",method = RequestMethod.GET)
	protected ModelAndView count(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			HttpServletRequest request,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<Notice> spec=DynamicSpecifications.bySearchFilter(request, Notice.class);
		Long count = this.noticeService.count(spec);
		super.success(model,count);
		return new ModelAndView(RESULT_PAGE);
	}
	@RequestMapping(value=apiVer+"/create", method=RequestMethod.GET)
	protected ModelAndView preCreate(@ModelAttribute(CommonConst.RequestResult.RESULT) Notice notice, ModelMap model) {
		super.success(model, notice);
		return new ModelAndView(VIEW_CREATE);
	}
	@RequestMapping(value=apiVer+"/create", method=RequestMethod.POST)
	public ModelAndView  create(@Valid Notice notice) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22");
		return new ModelAndView(VIEW_CREATE);
	}
}
