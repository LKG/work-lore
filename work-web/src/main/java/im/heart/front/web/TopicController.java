package im.heart.front.web;


import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.entity.Article;
import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author gg
 * @desc 专题
 */
@Controller
public class TopicController extends AbstractController {
	protected static final String apiVer = "/index";
	protected static final String VIEW_LIST="front/topic/topic";
	protected static final String VIEW_DETAILS="front/topic/topic_details";

	@RequestMapping(value={"/topic",apiVer+"/topic"}  ,method = RequestMethod.GET)
	public ModelAndView groups(HttpServletRequest request, HttpServletResponse response,
							   @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
							   @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
							   @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE*20+"") Integer size,
							   @RequestParam(value = "sort", required = false,defaultValue = "qqTotal") String sort,
							   @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.ORDER_DESC) String order,
							   @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
							   ModelMap model) {
		super.success(model);
		return new ModelAndView(VIEW_LIST);
	}

	@GetMapping(value = apiVer+"/{id}")
	protected ModelAndView findById(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@PathVariable BigInteger id,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			HttpServletRequest request,
			ModelMap model) {
		return new ModelAndView(VIEW_DETAILS);
	}
}
