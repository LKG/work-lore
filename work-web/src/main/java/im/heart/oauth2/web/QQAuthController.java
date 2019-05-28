package im.heart.oauth2.web;

import im.heart.oauth2.AuthService;
import im.heart.oauth2.QQAuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@RequestMapping("/oauth2")
public class QQAuthController  extends AbstractAuthController {
    private Logger logger = LoggerFactory.getLogger(QQAuthController.class);
    @Autowired()
    @Qualifier(QQAuthServiceImpl.BEAN_NAME)
    private AuthService qqAuthService;
    /**
     * 访问登陆页面，然后会跳转到 QQ 的登陆页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/qqLoginPage",method = RequestMethod.GET)
    public ModelAndView qqLogin() throws Exception {
        final String secretState = "secret" + new Random().nextInt(999_999);
        String uri = this.qqAuthService.getAuthorizationUrl(secretState);
        return new ModelAndView(redirectToUrl(uri));
    }
//    /**
//     * qq授权后会回调此方法，并将code传过来
//     * @param code
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/qq")
//    public ModelAndView getQQCode(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //根据code获取token
//        String accessToken = this.qqAuthService.getAccessToken(code);
//        // 保存 accessToken 到 cookie，过期时间为 30 天，便于以后使用
//        Cookie cookie = new Cookie("accessToken", accessToken);
//        cookie.setMaxAge(60 * 24 * 30);
//        response.addCookie(cookie);
//        //本网站是将用户的唯一标识存在用户表中，大家也可以加一张表，存储用户和QQ的对应信息。
//        //根据openId判断用户是否已经绑定过
//        String openId = qqAuthService.getOpenId(accessToken);
//        FrameUser user = new FrameUser();
//        String uri="";
//        if (user == null) {
//            //如果用户不存在，则跳转到绑定页面
//            uri=request.getContextPath() + "/student/html/index.min.html#/bind?type=";
//        } else {
//            //如果用户已存在，则直接登录
//            uri=request.getContextPath() + "/student/html/index.min.html#/app/home?open_id=" + openId;
//        }
//        return new ModelAndView(redirectToUrl(uri));
//    }
}
