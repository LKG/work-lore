package im.heart.front.web;


import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.frame.entity.QQGroup;
import im.heart.frame.service.QQGroupService;
import im.heart.media.entity.PeriodicalImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 *
 */
@Controller
public class QQGroupController extends AbstractController {
	protected static final String apiVer = "/index";

	/**
	 *  QQ群推广信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/group",apiVer+"/group"}  ,method = RequestMethod.GET)
	public ModelAndView groups(HttpServletRequest request, HttpServletResponse response,
							   @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
							   @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
							   @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE*20+"") Integer size,
							   @RequestParam(value = "sort", required = false,defaultValue = "qqTotal") String sort,
							   @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.ORDER_DESC) String order,
							   @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
							   ModelMap model) {
		return new ModelAndView("front/group");
	}


}
