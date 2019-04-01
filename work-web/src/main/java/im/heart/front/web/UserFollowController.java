package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.log.entity.FrameLogLogin;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameUserFollow;
import im.heart.usercore.service.FrameUserFollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 *
 * @作者 LKG
 * @功能说明：收藏商品
 */
@Controller
public class UserFollowController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(UserFollowController.class);
	protected static final String apiVer = "/userinfo";
	protected static final String VIEW_LIST="userinfo/user-follow";
	protected static final String  IMG_FILE_PATH= CommonConst.STATIC_UPLOAD_ROOT;
	@Autowired
	private FrameUserFollowService frameUserFollowService;

	@RequestMapping(value={apiVer+"/follows"})
	public ModelAndView lists(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                              @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                              @RequestParam(value = "sort", required = false,defaultValue = "createTime") String sort,
                              @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                              @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                              ModelMap model) {
		final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
		filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, SecurityUtilsHelper.getCurrentUser().getUserId()));
		Specification<FrameUserFollow> spec= DynamicSpecifications.bySearchFilter(filters, FrameUserFollow.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(CommonConst.Page.DEFAULT_PAGE, CommonConst.Page.DEFAULT_SIZE*2,sort,order, FrameLogLogin.class);
		Page<FrameUserFollow> pag = this.frameUserFollowService.findAll(spec, pageRequest);
		model.put("imgPath",IMG_FILE_PATH);
		super.success(model,pag);
		return new ModelAndView(VIEW_LIST);
	}

}
