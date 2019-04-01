package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.shop.entity.Order;
import im.heart.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Collection;

@Controller
public class UserOrderController extends AbstractController {
    protected static final String apiVer = "/userinfo/order";
    protected static final String VIEW_LIST="userinfo/user-order";
    @Autowired
    private OrderService orderService;
    @RequestMapping(value={apiVer+"s"},method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
        BigInteger userId= SecurityUtilsHelper.getCurrentUser().getUserId();
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ,userId));
        Specification<Order> spec= DynamicSpecifications.bySearchFilter(filters, Order.class);
        PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Order.class);
        Page<Order> pag = this.orderService.findAll(spec, pageRequest);
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }
    /**
     * 创建订单
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(apiVer+"/add/{itemId}")
    public ModelAndView addOrderItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
                                     ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        super.success(model,true);
        return new ModelAndView(VIEW_SUCCESS);
    }
    /**
     *
     * @Desc：取消订单
     * @param jsoncallback
     * @param token
     * @param orderId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = apiVer+"/{orderId}/cancel",method = RequestMethod.POST)
    protected ModelAndView cancelOrder(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
            @PathVariable BigInteger orderId,
            HttpServletRequest request,
            ModelMap model) {
        super.success(model,true);
        BigInteger userId= SecurityUtilsHelper.getCurrentUser().getUserId();
        Order order=this.orderService.findById(orderId);
        if(order!=null&& Order.OrderStatus.unprocessed.equals(order.getOrderStatus())&&order.getUserId().equals(userId)){
            order.setOrderStatus(Order.OrderStatus.invalid);
            this.orderService.save(order);
        }
        return new ModelAndView(VIEW_SUCCESS);
    }
}
