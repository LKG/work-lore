package im.heart.search.web;

import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import im.heart.search.entity.EsProduct;
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
    protected ModelAndView test(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        logger.info("......................................");
        Iterable list=this.productRepository.findAll();
        logger.info("...................list..................."+list);
        success(model,list);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/add")
    protected ModelAndView add(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        logger.info("......................................");

        for(int i=0;i<100;i++){
            EsProduct esProduct=EsProduct.builder().productCategoryId(Long.valueOf(i)).name("test"+i)
                    .build();
            this.productRepository.save(esProduct);
        }
        for(int i=0;i<1;i++){
            EsProduct esProduct=EsProduct.builder().productCategoryId(Long.valueOf(i)).name("中国"+i)
                    .build();
            this.productRepository.save(esProduct);
        }
        return new ModelAndView("index");
    }
}
