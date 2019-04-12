package im.heart.common.web;

import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorPageController extends AbstractController implements ErrorController  {
    private static final String ERROR_PATH = "/error";
    @RequestMapping(ERROR_PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response,
                                ModelMap model){
        super.error(model,new ResponseError(WebError.REQUEST_EXCEPTION));
        return new ModelAndView(CommonConst.RequestResult.PAGE_ERROR);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
