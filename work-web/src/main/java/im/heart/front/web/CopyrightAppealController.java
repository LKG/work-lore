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
 * @author gg
 * @desc 版权申诉
 */
@Controller
public class CopyrightAppealController extends AbstractController {
	protected static final String apiVer = "/index";

	@RequestMapping(value={"/copyrightAppeal",apiVer+"/copyrightAppeal"},method = RequestMethod.GET)
	public ModelAndView copyrightAppeal(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		super.success(model);
		return new ModelAndView("front/copyrightAppeal");
	}

}
