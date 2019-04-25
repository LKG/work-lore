package im.heart.common;


import im.heart.core.web.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author gg
 * @desc 第三方登录
 */
@Controller
public class ThirdLoginController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(ThirdLoginController.class);
	protected static final String apiVer = "/3rd/login";
	
//	@Autowired
//	@Qualifier("qqLoginService")
//	private ThirdLoginService qqLoginService;
	
//	@Autowired
//	@Qualifier("sinaLoginService")
//	private ThirdLoginService sinaLoginService;

	@RequestMapping(apiVer+"/dingding")
	public ModelAndView dingdingLogin(HttpServletRequest request, HttpServletResponse response,
								ModelMap model) {
		String authorizationUrl =null;
//				this.qqLoginService.getAuthorizationUrl();
		super.success(model);
		return new ModelAndView(redirectToUrl(authorizationUrl));
	}
	@RequestMapping(apiVer+"/qq")
	public ModelAndView qqLogin(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String authorizationUrl =null;
//				this.qqLoginService.getAuthorizationUrl();
		super.success(model);
		return new ModelAndView(redirectToUrl(authorizationUrl));
	}
//	@RequestMapping(apiVer+"/sina")
//	public ModelAndView sinaLogin(HttpServletRequest request, HttpServletResponse response,
//			ModelMap model){
//		super.success(model);
//		String authorizationUrl = this.sinaLoginService.getAuthorizationUrl();
//		return new ModelAndView(redirectToUrl(authorizationUrl));
//	}
	
	@RequestMapping(apiVer+"/{loginType}/callback")
	public ModelAndView callback(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "code", required = true)  String code,String source,
			@PathVariable String loginType,
			ModelMap model) {
		System.out.println("loginType-"+loginType);
//		this.qqLoginService.getSingleId(code);
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	
}
