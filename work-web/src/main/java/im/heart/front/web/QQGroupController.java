package im.heart.front.web;


import im.heart.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
							   ModelMap model) {
		return new ModelAndView("front/group");
	}


}
