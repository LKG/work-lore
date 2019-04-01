package im.heart.admin;


import im.heart.core.web.AbstractController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends AbstractController {
	protected static final String apiVer = "/index";
	protected static final String VIEW="admin/index";
	@RequiresRoles(value = "admin",logical=Logical.OR)
	@RequestMapping(value={"","/",apiVer,apiVer+"/"})
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		super.success(model);
		return new ModelAndView(VIEW);
	}
}
