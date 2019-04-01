package im.heart.common.web;

import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.exception.IncorrectCredentialsException;
import im.heart.usercore.service.FrameUserOrgService;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.vo.FrameUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
	protected static final String apiVer = "/userinfo";
	@Autowired
	private FrameUserService frameUserService;
	@Autowired
	private FrameUserOrgService frameUserOrgService;
	
	@RequestMapping(value={apiVer,apiVer+"/index",apiVer+"/"})
	public ModelAndView userinfo(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model) {
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			FrameUser user = this.frameUserService.findById(vo.getUserId());
			FrameUserVO userVo = new FrameUserVO(user);
			super.success(model,userVo);
		}
		return new ModelAndView("userinfo/index");
	}

	@RequestMapping(apiVer+"/showImg")
	public ModelAndView showImg(HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) {
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			super.success(model);
			return new ModelAndView("userinfo/user-showimg");
		}
		super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
		return new ModelAndView(RESULT_PAGE);
	}
	@RequestMapping(apiVer+"/safe")
	public ModelAndView safe(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model) {
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			super.success(model);
			return new ModelAndView("userinfo/user-safe");
		}
		super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
		return new ModelAndView(RESULT_PAGE);
	}


	@RequestMapping(value = apiVer+"/setdefault/{relateId}",method = RequestMethod.POST)
	protected ModelAndView setdefault(
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger relateId,
			HttpServletRequest request,
			ModelMap model) {
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			this.frameUserOrgService.setDefaultOrg(vo.getUserId(), relateId);
			super.success(model);
			return new ModelAndView(VIEW_SUCCESS);
		}
		super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 * 
	 * @Desc： 修改密码 页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value=apiVer+"/changePwd",method = RequestMethod.GET)
	public ModelAndView changePasswordPage(HttpServletRequest request, HttpServletResponse response,
                                           ModelMap model) {
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			super.success(model);
			return new ModelAndView("userinfo/user-changePwd");
		}
		super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
		return new ModelAndView(RESULT_PAGE);

	}
	
	
	@RequestMapping(value=apiVer+"/changePwd" ,method = RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value = "oldPassword", required = false ) String  oldPassword,
                                       @RequestParam(value = "passWord", required = false ) String passWord,
                                       @RequestParam(value = "retryPassWord", required = false ) String retryPassWord,
                                       ModelMap model) throws IncorrectCredentialsException {
		if(passWord==null||!passWord.equals(retryPassWord)){
			super.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		FrameUserVO vo= SecurityUtilsHelper.getCurrentUser();
		if(vo!=null){
			try {
				this.frameUserService.changePassword(vo.getUserId(), oldPassword, passWord);
				super.success(model);
				return new ModelAndView("userinfo/user-changePwd");
			} catch (IncorrectCredentialsException e) {
				super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_INCORRECT));
				return new ModelAndView(RESULT_PAGE);
			}
		}
		super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED));
		return new ModelAndView(RESULT_PAGE);
	}
	
}
