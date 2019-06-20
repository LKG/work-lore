package im.heart.search.web;

import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import im.heart.search.repository.EsProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gg
 * @desc  搜索控制
 */
@Controller
@RequestMapping("es")
public class EsIndexController extends AbstractController {
    @Autowired
    EsProductRepository productRepository;
    @RequestMapping(value = "/test")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        logger.info("......................................");
        Iterable list=this.productRepository.findAll();
        logger.info("...................list..................."+list);
        return new ModelAndView();
    }
}
