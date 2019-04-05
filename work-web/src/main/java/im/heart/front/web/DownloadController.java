package im.heart.front.web;

import com.alibaba.fastjson.JSON;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.utils.BaseUtils;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.core.web.utils.WebUtilsEx;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;

@Controller
public class DownloadController extends AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
    protected static final String apiVer = "/fd";
    @Autowired
    private PeriodicalService periodicalService;
    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(required = false) String filename,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap model) {
        FrameUserVO user= SecurityUtilsHelper.getCurrentUser();
        Periodical po = this.periodicalService.findById(id);
        //文件审核通过
        if(!Status.enabled.equals(po.getCheckStatus())){
            super.fail(model,new ResponseError(WebError.ACCESS_DENIED));
            return  new ModelAndView(RESULT_PAGE);
        }
        if(Boolean.FALSE.equals(po.getIsPub())){
            super.fail(model,new ResponseError(WebError.ACCESS_DENIED));
            return  new ModelAndView(RESULT_PAGE);
        }
        //校验用户是否有权限
        if(!user.isExpiry()){
            super.fail(model,new ResponseError(WebError.ACCESS_DENIED));
            return  new ModelAndView(RESULT_PAGE);
        }
        if(StringUtils.isBlank(filename)){
            filename=po.getPeriodicalName().concat(".").concat(po.getFileHeader());
        }
        //文件的真实路径
        response.addHeader("X-Accel-Redirect","D:\\var\\www\\uploads\\material\\20190316\\201903\\1003\\1003_10.png");
        response.addHeader(HttpHeaders.CONTENT_TYPE,"application/octet-stream; charset=utf-8");
        System.out.println(filename);
        BaseUtils.setFileDownloadHeader(request,response,filename);
        return new ModelAndView(RESULT_PAGE);
    }
}
