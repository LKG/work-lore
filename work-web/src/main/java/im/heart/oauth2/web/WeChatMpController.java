//package im.heart.oauth2.web;
//
//import com.alibaba.fastjson.JSON;
//import im.heart.common.utils.UserCacheUtils;
//import im.heart.core.enums.Status;
//import im.heart.core.web.ResponseError;
//import im.heart.core.web.enums.WebError;
//import im.heart.oauth2.WeChatAuthService;
//import im.heart.usercore.entity.FrameUser;
//import im.heart.usercore.entity.FrameUserConnect;
//import im.heart.usercore.enums.IdentityType;
//import lombok.extern.slf4j.Slf4j;
//import me.chanjar.weixin.common.api.WxConsts;
//import me.chanjar.weixin.common.error.WxError;
//import me.chanjar.weixin.common.error.WxErrorException;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
//import me.chanjar.weixin.mp.bean.result.WxMpUser;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.net.URLEncoder;
//import java.util.Optional;
//
//@Slf4j
//public class WeChatMpController  extends AbstractAuthController {
//    private Logger logger = LoggerFactory.getLogger(WeChatMpController.class);
//    @Autowired
//    private WeChatAuthService weChatAuthService;
//
//    @Autowired
//    private WxMpService wxMpService;
//
//
//    @Value("${wechat.mp.cllbackUri}")
//    private  String callbackUrl;
//
//    /**
//     *  获取授权Code
//     * @param state
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    @GetMapping("/authorize")
//    public ModelAndView authorize(@RequestParam(value = "state" ,required = false) String state) throws UnsupportedEncodingException {
//        String url="http://doc.itaobao.pub/wechat/userInfo";
//        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(state,"UTF-8"));
//        log.info("【微信网页授权】获取code,redirectUrl={}",redirectUrl);
//        return new ModelAndView(redirectToUrl(redirectUrl));
//    }
//
//    /**
//     *  获取根据code 获取access_token 并用户信息
//     * @param code
//     * @param returnUrl
//     * @return
//     */
//    @GetMapping("/userInfo")
//    public ModelAndView userInfo(@RequestParam("code") String code,
//                           @RequestParam("state") String returnUrl){
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
//        try {
//            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
//        }catch (WxErrorException e){
//            log.error("【微信网页授权】,wxMpOAuth2AccessToken:{},e:{}",JSON.toJSONString(wxMpOAuth2AccessToken), e);
//        }
//        String openId=wxMpOAuth2AccessToken.getOpenId();
//        Optional<FrameUserConnect> optional= this.userConnectService.findByOpenIdAndType(openId,IdentityType.wechat);
//        FrameUserConnect userConnect=null;
//        if(!optional.isPresent()){
//            userConnect=new FrameUserConnect();
//            BeanUtils.copyProperties(wxMpOAuth2AccessToken,userConnect);
//            userConnect.setIdentityType(IdentityType.wechat);
//            userConnect.setMessage(JSON.toJSONString(wxMpOAuth2AccessToken));
//            this.userConnectService.save(userConnect);
//            log.info("【微信网页授权】wxMpOAuth2AccessToken={}", JSON.toJSONString(wxMpOAuth2AccessToken));
//            return new ModelAndView(redirectToUrl(returnUrl+"?openId="+openId));
//        }
//        userConnect=optional.get();
//        userConnect.setExpiresIn(wxMpOAuth2AccessToken.getExpiresIn());
//        if(StringUtils.isNotBlank(wxMpOAuth2AccessToken.getUnionId())){
//            userConnect.setUnionId(wxMpOAuth2AccessToken.getUnionId());
//        }
//        userConnect.setAccessToken(wxMpOAuth2AccessToken.getAccessToken());
//        userConnect.setMessage(JSON.toJSONString(wxMpOAuth2AccessToken));
//        userConnect.setRefreshToken(wxMpOAuth2AccessToken.getRefreshToken());
//        this.userConnectService.save(userConnect);
//        log.info("【微信网页授权】获取openid={},returnUrl={},wxMpOAuth2AccessToken={}",openId,returnUrl,JSON.toJSONString(wxMpOAuth2AccessToken));
//        return new ModelAndView(redirectToUrl(returnUrl+"?openid="+openId));
//
//    }
//
//    /**
//     *  微信网页登陆
//     * @param returnUrl
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    @GetMapping("/qrAuthorize")
//    public ModelAndView qrAuthorize(@RequestParam(value = "returnUrl" ,required = false) String returnUrl) throws UnsupportedEncodingException {
//        String url="doc.itaobao.pub/wechat/userInfo";
//        String redirectUrl=wxMpService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN,URLEncoder.encode(returnUrl,"UTF-8"));
//        return new ModelAndView(redirectToUrl(redirectUrl));
//    }
//    @GetMapping("/wechat/bind")
//    public ModelAndView bindPage(@RequestParam(value = "openId" ,defaultValue="") String openId,
//                                 HttpServletRequest request, HttpServletResponse response,
//                                 ModelMap model) {
//
//        BigInteger userId=getBindUserId(openId,IdentityType.wechat);
//        if(userId==null|| BigInteger.ZERO.equals(userId)){
//            //用户未绑定，返回到绑定页面
//            success(model,"openId",openId);
//            return  new ModelAndView("user_bind");
//        }
//        FrameUser user=this.userService.findById(userId);
//        if(user==null){
//            //用户不存在或者用户状态不可用
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_UNKNOWN));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        if(!Status.enabled.equals(user.getStatus())){
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_DISABLED));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        WebUtils.setSessionAttribute(request,"openId",openId);
//        WebUtils.setSessionAttribute(request,"userId",user.getUserId());
//        success(model,"openId",openId);
//        return new ModelAndView(redirectToUrl("/?openId="+openId));
//    }
//    /**
//     *  获取绑定用户Id
//     * @param openId
//     * @param identityType
//     * @return
//     */
//    public BigInteger getBindUserId(String openId,IdentityType identityType){
//        Optional<FrameUserConnect> optional=this.userConnectService.findByOpenIdAndType(openId,identityType);
//        if(optional.isPresent()){
//            BigInteger userId=optional.get().getUserId();
//            return userId;
//        }
//        return BigInteger.ZERO;
//    }
//    @PostMapping("/wechat/bindPhone")
//    @ResponseBody
//    public ModelAndView bindPhone(
//            @RequestParam(value = "openId" ,defaultValue="") String openId,
//            @RequestParam(value = "userPhone" ,defaultValue="") String userPhone,
//            @RequestParam(value = "phoneCode" ,defaultValue="") String phoneCode,
//            @RequestParam(value = "identityType" ,defaultValue="wechat") String identityType,
//            HttpServletRequest request, HttpServletResponse response,
//            ModelMap model) throws WxErrorException{
//        log.info("开始绑定手机号：{}。。{} 。。",userPhone,phoneCode);
//        //校验验证码
//        Boolean isResponseCorrect = UserCacheUtils.checkMobileCode(userPhone, phoneCode);
//        if(!isResponseCorrect){
//            super.fail(model,new ResponseError(WebError.REQUEST_AUTH_VERIFY));
//            return  new ModelAndView(RESULT_PAGE);
//        };
//        FrameUser user=this.userService.findByUserPhone(userPhone);
//        if(user==null){
//            user=new FrameUser();
//            user.setStatus(Status.enabled);
//            user.setRemark("weichat auto register");
//            user.setUserPhone(userPhone);
//            user.setUserName(userPhone);
//            user.setPassWord("888888");
//            user= this.userService.save(user);
//            log.info("weichat auto register userId:{}"+user.getUserId());
//        }
//        if(!Status.enabled.equals(user.getStatus())){
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_DISABLED));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        try {
//            bindUser(user,openId,IdentityType.wechat);
//            success(model);
//            WebUtils.setSessionAttribute(request,"openId",openId);
//            WebUtils.setSessionAttribute(request,"userId",user.getUserId());
//            log.info("账号：{}绑定 openId {} 成功",userPhone,openId);
//            return  new ModelAndView(VIEW_SUCCESS);
//        }catch (WxErrorException ex){
//            logger.error("WxErrorException:"+ex.getStackTrace()[0].getMethodName(), ex);
//            super.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
//        }
//        return  new ModelAndView(RESULT_PAGE);
//    }
//
//    private  void bindUser(FrameUser user, String openId, IdentityType identityType) throws WxErrorException{
//        Optional<FrameUserConnect> optional=this.userConnectService.findByOpenIdAndType(openId,identityType);
//        if(!optional.isPresent()){
//            //openId 异常直接抛出
//            throw new WxErrorException(WxError.builder().errorMsg("未获取到openId"+openId+" 存储信息").build());
//        }
//        FrameUserConnect userConnect= optional.get();
//        BigInteger userId=userConnect.getUserId();
//        if(userId==null||BigInteger.ZERO.equals(userId)){
//            WxMpUser wxUser=this.wxMpService.getUserService().userInfo(openId);
//            if(wxUser==null){
//                log.info(JSON.toJSONString(wxUser));
//                throw new WxErrorException(WxError.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).errorMsg("未获取到wxUser"+openId+" 存储信息").build());
//            }
//            if(!wxUser.getSubscribe()){
//                log.info(JSON.toJSONString(wxUser));
//                throw new WxErrorException(WxError.builder().errorCode(HttpStatus.FORBIDDEN.value()).errorMsg("未关注公众号").build());
//            }
//            String  nickName=wxUser.getNickname();
//            int sex= wxUser.getSex();
//            String headImgUrl=wxUser.getHeadImgUrl();
//            String unionId=wxUser.getUnionId();
//            user.setNickName(nickName);
//            user.setSex(sex);
//            user.setHeadImgUrl(headImgUrl);
//            //更新用户信息
//            this.userService.save(user);
//            userConnect.setUserId(user.getUserId());
//            if(StringUtils.isNotBlank(unionId)){
//                userConnect.setUnionId(unionId);
//            }
//            this.userConnectService.save(userConnect);
//        }
//    }
//
//    @PostMapping("/wechat/bindUser")
//    public ModelAndView bindUser(
//            @RequestParam(value = "openId" ,defaultValue="") String openId,
//            @RequestParam(value = "userName" ,defaultValue="") String userName,
//            @RequestParam(value = "passWord" ,defaultValue="") String passWord,
//            HttpServletRequest request, HttpServletResponse response,
//            ModelMap model){
//        log.info("开始绑定账号：{}。。。。",userName);
//        FrameUser user=this.userService.findFrameUser(userName);
//        if(user==null){
//            //用户不存在
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_UNKNOWN));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        if(!Status.enabled.equals(user.getStatus())){
//            //用户状态不可用
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_DISABLED));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        boolean isMatch =this.passwordService.passwordsMatch(user,passWord);
//        if (!isMatch) {
//            //密码错误
//            super.fail(model,new ResponseError(WebError.AUTH_CREDENTIALS_INCORRECT));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        try {
//            bindUser(user,openId,IdentityType.wechat);
//            log.info("账号：{}绑定 openId {} 成功",userName,openId);
//            success(model);
//            //设置session
//            WebUtils.setSessionAttribute(request,"openId",openId);
//            WebUtils.setSessionAttribute(request,"userId",user.getUserId());
//            return  new ModelAndView(RESULT_PAGE);
//        }catch (WxErrorException ex){
//            logger.error("WxErrorException:"+ex.getStackTrace()[0].getMethodName(), ex);
//            if(ex.getError().getErrorCode()== HttpStatus.FORBIDDEN.value()){
//                WebError webError= WebError.ACCESS_DENIED;
//                webError.setDescription(webError.getDescription()+"请先关注公众号");
//                super.fail(model,new ResponseError(webError));
//                return  new ModelAndView(RESULT_PAGE);
//            }
//            super.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
//        }
//        return  new ModelAndView(RESULT_PAGE);
//    }
//
//    /**
//     * 获取用户信息
//     * @param code
//     * @param returnUrl
//     * @return
//     */
//    @GetMapping("/qrUserInfo")
//    public ModelAndView qrUserInfo(@RequestParam("code") String code,
//                             @RequestParam("state") String returnUrl,
//                             ModelMap model){
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
//        try{
//            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
//        }catch (WxErrorException e){
//            log.error("【微信网页授权】,wxMpOAuth2AccessToken:{},e:{}",JSON.toJSONString(wxMpOAuth2AccessToken), e);
//            super.fail(model,new ResponseError(WebError.AUTH_ACCOUNT_UNKNOWN));
//            return  new ModelAndView(RESULT_PAGE);
//        }
//        String openId=wxMpOAuth2AccessToken.getOpenId();
//        return new ModelAndView(redirectToUrl(returnUrl+"?openid="+openId));
//    }
//}