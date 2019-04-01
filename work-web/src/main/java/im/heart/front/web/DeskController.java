package im.heart.front.web;

import im.heart.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeskController extends AbstractController {
	protected static final String apiVer = "/desk";
	@RequestMapping(value={"/userinfo/"+apiVer,apiVer,apiVer+"/"})
	public ModelAndView desk(HttpServletRequest request, HttpServletResponse response,
                              ModelMap model) {
		super.success(model);
		return new ModelAndView("desk");
	}
}
