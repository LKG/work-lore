package im.heart.front.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class IndexController {
    @RequestMapping(value={"/index","/",""},method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) {
        return new ModelAndView("index");
    }
}
