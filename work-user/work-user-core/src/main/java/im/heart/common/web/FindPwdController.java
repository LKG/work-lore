package im.heart.common.web;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import im.heart.common.CacheUtils;
import im.heart.common.EmailTplEnum;
import im.heart.common.SmsTplEnum;
import im.heart.common.utils.UserCacheUtils;
import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.captcha.ImageCaptchaExService;
import im.heart.core.plugins.email.SendEmailService;
import im.heart.core.plugins.sms.SmsSendService;
import im.heart.core.utils.BaseUtils;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.core.web.utils.WebUtilsEx;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.vo.FrameUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author gg
 * @desc 用户找回密码控制器
 */
@Controller
public class FindPwdController extends AbstractController {
	protected static final String apiVer = "/findPwd";
	@Autowired
	private FrameUserService frameUserService;

	@Resource
	private SmsSendService smsSendService;
	@Autowired
	private SendEmailService sendEmailService;

	@Autowired
	private ImageCaptchaExService imageCaptchaService;

	/**
	 * 找回密码方式
	 */
	public  enum  FindPwdTypeEnum {
		email("email",2),
		phone("phone",1);
		/**
		 *  code
		 */
		public String code;
		/**
		 *  intVal
		 */
		public int intVal;
		FindPwdTypeEnum(String code, int intVal) {
			this.code = code;
			this.intVal = intVal;
		}

		public static FindPwdTypeEnum fromCode(String code){
			for(FindPwdTypeEnum refer : FindPwdTypeEnum.values()) {
				if (refer.code.equals(code)) {
					return refer;
				}
			}
			return null;
		}
	}

	/**
	 * 忘记密码页面
	 * @param request
	 * @param response
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value={apiVer,apiVer+"/",apiVer+"/index"},method = RequestMethod.GET)
	public ModelAndView findPwdIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "k", required = false) String key, ModelMap model) {
		model.put("k",key);
		this.success(model);
		return new ModelAndView("findpwd/index");
	}

	@RequestMapping(value=apiVer+"/findpwd",method = RequestMethod.GET)
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "k", required = false) String key,
                                ModelMap model) {
		if(StringUtilsEx.isNotBlank(key)){
			Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
			if(obj!=null&&obj instanceof FrameUser){
				FrameUser user =(FrameUser)obj;
				FrameUserVO userVo=new FrameUserVO(user);
				this.success(model, userVo);
				model.put("k", key);
				return new ModelAndView("findpwd/findPwd");
			}
		}
		ResponseError responseError=new ResponseError(WebError.INVALID_REQUEST);
		this.fail(model,responseError);
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 * 检测帐号信息是否存在、（邮箱，电话号码。帐号 ）step_1
	 * @param jsoncallback
	 * @param account
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = apiVer + "/checkUserAccount", method = RequestMethod.GET)
	protected ModelAndView checkUserPhone(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "account", required = false) String account,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if(StringUtilsEx.isNotBlank(account)&&account.length()  >= FrameUser.minAccountLength&& account.length() <= FrameUser.maxAccountLength){
			FrameUser user = this.frameUserService.findFrameUser(account);
			if (user != null) {
				this.success(model);
				return new ModelAndView(RESULT_PAGE);
			}
		}
		this.fail(model);
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 * 忘记密码页面  step_1 验证账号信息
	 * @param request
	 * @param response
	 * @param account
	 * @param key
	 * @param format
	 * @param validateCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value=apiVer+"/subGeneral")
	public ModelAndView subGeneral(HttpServletRequest request, HttpServletResponse response,
             @RequestParam(value = "account", required = false ) String account,
             @RequestParam(value = "k", required = false) String key,
			 @RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
			 @RequestParam(value = "validateCode", required = false ) String validateCode,
             ModelMap model) {
		if(StringUtilsEx.isNotBlank(account)&& StringUtilsEx.isNotBlank(validateCode)){
			String sessionId = request.getRequestedSessionId();
			if(!this.imageCaptchaService.validateResponseForID(sessionId, validateCode).booleanValue()){
				this.fail(model,new ResponseError(WebError.AUTH_CAPTCHA_INCORRECT));
				return new ModelAndView("findpwd/index");
			}
			FrameUser user=this.frameUserService.findFrameUser(account);
			if(user!=null){
				this.success(model,user);
				String uuid= StringUtilsEx.getUUID2();
				CacheUtils.generateCache(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix,uuid, user);
				return new ModelAndView(redirectToUrl(apiVer+"/findpwd."+format+"?k="+uuid));
			}
		}
		this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
		return new ModelAndView("findpwd/index");
	}


	/**
	 *
	 * 通过手机号码校验成功
	 * @param jsoncallback
	 * @param request
	 * @param response
	 * @param format
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = apiVer + "/checkSuccess")
	protected ModelAndView checkSuccess(@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
         HttpServletRequest request, HttpServletResponse response,
		 @RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
         @RequestParam(value = "k2", required = false) String key,
         ModelMap model){
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		model.put("k2", key);
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser){
			this.success(model);
			return new ModelAndView("findpwd/resetPwd");
		}
		this.fail(model,new ResponseError(WebError.INVALID_REQUEST));
		return new ModelAndView("findpwd/resetPwd");
	}
	/**
	 *
	 * 邮件发送成功
	 * @param jsoncallback
	 * @param request
	 * @param response
	 * @param format
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = apiVer + "/sendEmailSuccess")
	protected ModelAndView sendEmailSuccess(@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
		HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
		@RequestParam(value = "k", required = false) String key,
		ModelMap model){
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser){
			FrameUser user =(FrameUser)obj;
			String userEmail=user.getUserEmail();
			if(StringUtilsEx.isNotBlank(userEmail)){
				String emailServer = StringUtilsEx.substringAfterLast(userEmail, "@");
				this.success(model);
				model.put("emailServer", emailServer);
				return new ModelAndView("findpwd/sendEmailSuccess");
			}
		}
		this.fail(model,new ResponseError(WebError.INVALID_REQUEST));
		return new ModelAndView("findpwd/sendEmailSuccess");
	}
	/**
	 *
	 * @Desc 修改密码
	 * @param jsoncallback
	 * @param request
	 * @param response
	 * @param format
	 * @param key
	 * @param passWord
	 * @param retryPassWord
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = apiVer + "/resetPwd")
	protected ModelAndView resetPwd(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
            @RequestParam(value = "k2", required = false) String key,
            @RequestParam(value = "passWord", required = false) String passWord,
            @RequestParam(value = "retryPassWord", required = false) String retryPassWord,
            ModelMap model) throws Exception {
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		logger.info(JSON.toJSONString(obj));
		if(obj!=null&&obj instanceof FrameUser){
			FrameUser user =(FrameUser)obj;
			logger.info(WebUtilsEx.getParametersJson(request));
			this.frameUserService.resetPassword(user.getUserId(), retryPassWord);
			CacheUtils.evictCache(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
			return new ModelAndView(redirectToUrl(apiVer+"/resetPwdSuccess."+format+"?k="+key));
		}
		this.fail(model,new ResponseError(WebError.INVALID_REQUEST));
		return new ModelAndView(RESULT_PAGE);
	}
	@RequestMapping(value = apiVer + "/checkEmailCode")
	protected ModelAndView checkEmailCode(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
			@RequestParam(value = "emailCode", required = false) String emailCode,
			@RequestParam(value = "k", required = false) String key,
			ModelMap model) throws Exception {
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser) {
			FrameUser user = (FrameUser) obj;
			String userEmail=user.getUserEmail();
			Boolean isResponseCorrect = UserCacheUtils.checkEmailCode(userEmail, emailCode);
			CacheUtils.evictCache(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
			if(isResponseCorrect){
				String key2=generateCache(user);
				return new ModelAndView(redirectToUrl(apiVer+"/checkSuccess."+format+"?k2="+key2));
			}
		}
		this.fail(model,new ResponseError(WebError.INVALID_REQUEST));
		return new ModelAndView(RESULT_PAGE);
	}

	private String generateCache(Object val){
		String key= StringUtilsEx.getUUID2();
		CacheUtils.generateCache(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix,key, val);
		return  key;
	}

	/**
	 * 密码修改成功页面 step_4
	 * @param jsoncallback
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = apiVer + "/resetPwdSuccess")
	protected ModelAndView resetPwdSuccess(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws Exception {
		this.success(model);
		return new ModelAndView("findpwd/resetPwdSuccess");
	}

	@RequestMapping(value = apiVer + "/sendFindPwd")
	protected ModelAndView sendFindPwd(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "format", required = false,defaultValue = "jhtml") String format,
            @RequestParam(value = "k", required = false) String key,
            @RequestParam(value = "phoneCode", required = false) String phoneCode,
            @RequestParam(value = "type", required = false) String type,
            ModelMap model) throws Exception {
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser){
			FrameUser user =(FrameUser)obj;
			if(StringUtilsEx.isNotBlank(type)&&type.equals(FindPwdTypeEnum.email.intVal+"")){
				int emailCode = (int) ((Math.random() * 9 + 1) * 100000);
				Map<String, Object> modelTemp = Maps.newHashMap();
				String userEmail=user.getUserEmail();
				UserCacheUtils.generateEmailCodeCache(userEmail,emailCode);
				modelTemp.put("k", key);
				modelTemp.put("emailCode", emailCode);
				modelTemp.put("expiredTime", UserCacheUtils.CacheConfig.EMAIL_CODE.expiredTime/60);
				modelTemp.put(RequestResult.RESULT, user);
				EmailTplEnum tpl= EmailTplEnum.FIND_PWD;
				this.sendEmailService.sendEmail(modelTemp, tpl.description,
						tpl.templatePath,
						new String[] { userEmail },
						new String[] {});
				return new ModelAndView(redirectToUrl(apiVer+"/sendEmailSuccess."+format+"?k="+key));
			}
			String mobile=user.getUserPhone();
			Boolean isResponseCorrect = UserCacheUtils.checkMobileCode(mobile, phoneCode);
			if(isResponseCorrect){
				//移除key
				CacheUtils.evictCache(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
				String key2=generateCache(user);
				return new ModelAndView(redirectToUrl(apiVer+"/checkSuccess."+format+"?k2="+key2));
			}
		}
		this.fail(model,new ResponseError(WebError.AUTH_PHONE_CODE_INCORRECT));
		return new ModelAndView(RESULT_PAGE);
	}
	/**
	 *  发送短信验证码
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = apiVer + "/passMobileCode")
	public ModelAndView passMobileCode(HttpServletRequest request,
									   HttpServletResponse response,
									   @RequestParam(value = "k", required = false ) String key,
									   @RequestParam(value = "type", required = false ,defaultValue="1") String type, ModelMap model) {
		ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_MISSING);
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,responseError);
			return new ModelAndView(RESULT_PAGE);
		}
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser){
			FrameUser user =(FrameUser)obj;
			int mobileCode = (int)((Math.random()*9+1)*10000);
			Map<String,Object> modelTemp = Maps.newHashMap();
			modelTemp.put("mobileCode", mobileCode);
			modelTemp.put("product", "公文库");
			modelTemp.put("expiredTime", UserCacheUtils.CacheConfig.MOBILE_CODE.expiredTime/60);
			String mobile=user.getUserPhone();
			logger.info("mobileCode-host:[{}],mobile:[{}] mobileCode:[{}] type:[{}]", BaseUtils.getIpAddr(request),mobile,mobileCode,type);
			UserCacheUtils.generateMobileCache(mobile, mobileCode);
			Boolean isSuccess =this.smsSendService.sendSms(modelTemp, SmsTplEnum.FIND_PWD.templateId, new String[]{mobile});
			if(isSuccess){
				this.success(model);
			}else{
				this.fail(model,responseError);
			}
		}
		return new ModelAndView(RESULT_PAGE);
	}

    /**
     * 验证码校验接口
     * @param request
     * @param response
     * @param key
     * @param phoneCode
     * @param model
     * @return
     */
	@RequestMapping(value = apiVer + "/checkMobileCode")
	public ModelAndView checkMobileCode(HttpServletRequest request, HttpServletResponse response,
           @RequestParam(value = "k", required = false ) String key,
           @RequestParam(value = "phoneCode", required = false) String phoneCode,
           ModelMap model){
		if(StringUtilsEx.isBlank(key)){
			this.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
			return new ModelAndView(RESULT_PAGE);
		}
		logger.info("passcode-host:{}",request.getLocalAddr());
		Object obj= CacheUtils.getCacheObject(UserCacheUtils.CacheConfig.FIND_PWD.keyPrefix, key);
		if(obj!=null&&obj instanceof FrameUser){
			FrameUser user =(FrameUser)obj;
			String mobile=user.getUserPhone();
			Boolean isResponseCorrect = UserCacheUtils.checkMobileCode(mobile, phoneCode);
			if(isResponseCorrect){
				this.success(model);
				return new ModelAndView(RESULT_PAGE);
			}
		}
		this.fail(model);
		return new ModelAndView(RESULT_PAGE);
	}



}
