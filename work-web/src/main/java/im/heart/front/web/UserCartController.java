//package im.heart.front.web;
//
//import im.heart.core.CommonConst;
//import im.heart.core.plugins.persistence.DynamicPageRequest;
//import im.heart.core.plugins.persistence.DynamicSpecifications;
//import im.heart.core.plugins.persistence.SearchFilter;
//import im.heart.core.web.AbstractController;
//import im.heart.security.utils.SecurityUtilsHelper;
//import im.heart.shop.entity.Cart;
//import im.heart.shop.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Collection;
//
//@Controller
//public class UserCartController extends AbstractController {
//    protected static final String apiVer = "/cart";
//    protected static final String VIEW_LIST="userinfo/user_cart";
//    @Autowired
//    private CartService cartService;
//    @RequestMapping(value={apiVer+"s"},method = RequestMethod.GET)
//    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
//                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
//                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
//                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
//                             @RequestParam(value = "sort", required = false) String sort,
//                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
//                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
//                             ModelMap model) {
//        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
//        filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, SecurityUtilsHelper.getCurrentUser().getUserId()));//查询
//        Specification<Cart> spec= DynamicSpecifications.bySearchFilter(filters, Cart.class);
//        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Cart.class);
//        Page<Cart> pag = this.cartService.findAll(spec, pageRequest);
//        super.success(model,pag);
//        return new ModelAndView(VIEW_LIST);
//    }
//    @RequestMapping(value={"/addToCart"},method = RequestMethod.GET)
//    public ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response,
//                                  ModelMap model) {
//        super.success(model);
//        return new ModelAndView("front/user_shop");
//    }
//    /**
//     * 添加购物车
//     *
//     * @param itemId
//     * @param num
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(apiVer+"/add/{itemId}")
//    public ModelAndView addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
//                                    ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//
//        super.success(model,true);
//        return new ModelAndView(VIEW_SUCCESS);
//    }
//}
