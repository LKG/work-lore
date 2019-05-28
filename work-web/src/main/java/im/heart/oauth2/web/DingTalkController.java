package im.heart.oauth2.web;

import im.heart.oauth2.AuthService;
import im.heart.oauth2.impl.DingTalkAuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

public class DingTalkController   extends AbstractAuthController {
    private Logger logger = LoggerFactory.getLogger(DingTalkController.class);


    @Autowired()
    @Qualifier(DingTalkAuthServiceImpl.BEAN_NAME)
    private AuthService dingTalkAuthService;
    /**
     * 访问登陆页面，然后会跳转到 QQ 的登陆页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dingTalkLoginPage",method = RequestMethod.GET)
    public ModelAndView qqLogin() throws Exception {
        final String secretState = "secret" + new Random().nextInt(999_999);
        String uri = this.dingTalkAuthService.getAuthorizationUrl(secretState);
        return new ModelAndView(redirectToUrl(uri));
    }

}
