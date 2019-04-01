package im.heart.front.web;


import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
public class TopicController extends AbstractController {
	protected static final String apiVer = "/index";


	/**
	 *  QQ群推广信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/topic",apiVer+"/topic"}  ,method = RequestMethod.GET)
	public ModelAndView groups(HttpServletRequest request, HttpServletResponse response,
							   @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
							   @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
							   @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE*20+"") Integer size,
							   @RequestParam(value = "sort", required = false,defaultValue = "qqTotal") String sort,
							   @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.ORDER_DESC) String order,
							   @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
							   ModelMap model) {
		super.success(model);
		return new ModelAndView("front/topic");
	}


}
