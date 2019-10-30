package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.frame.entity.FrameDict;
import im.heart.media.entity.Periodical;
import im.heart.media.service.PeriodicalService;
import im.heart.media.vo.PeriodicalVO;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.shop.dto.OrderDto;
import im.heart.shop.dto.OrderItemDto;
import im.heart.shop.entity.Order;
import im.heart.shop.service.OrderService;
import im.heart.usercore.entity.FrameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController extends AbstractController {
    protected static final String apiVer = "/order";
    protected static final String CONFIRM_PAGE="shop/order-confirm";
    protected static final String PAY_PAGE="shop/pay";
    @Autowired
    private OrderService orderService;

    @Autowired
    private PeriodicalService periodicalService;

    /**
     *  提交订单
     * @param request
     * @return
     */
    @RequestMapping(value={apiVer+"/confirmOrderInfo"} ,method = RequestMethod.GET)
    public ModelAndView getOrderInfo(HttpServletRequest request,
                                     @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
                                     ModelMap model, BigInteger id) {
        Optional<Periodical> optional = this.periodicalService.findById(id);
        if(optional.isPresent()){
            super.success(model, new PeriodicalVO(optional.get()));
        }
        return new ModelAndView(CONFIRM_PAGE);
    }
    /**
     *  确认订单
     * @param request
     * @return
     */
    @RequestMapping(value={apiVer+"/payOrder"} ,method = RequestMethod.GET)
    public ModelAndView confirmOrderInfo(HttpServletRequest request,
                                         @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
                                         ModelMap model, Long orderId) {
        Optional<Order> optional = this.orderService.findById(orderId);
        if(optional.isPresent()){
            super.success(model, optional.get());
        }
        return new ModelAndView(PAY_PAGE);
    }
    /**
     * 创建订单
     * @param jsoncallback
     * @param token
     * @param orderDto
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = apiVer+"/subscribe",method = RequestMethod.POST)
    protected ModelAndView createOrder(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
          @RequestBody OrderDto orderDto,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) throws  Exception{
        FrameUser currentUser= SecurityUtilsHelper.getCurrentUser();
        BigInteger userId= currentUser.getUserId();
        List<OrderItemDto> items=orderDto.getItems();
        if(items==null||items.isEmpty()){
            super.fail(model,new ResponseError(WebError.REQUEST_PARAMETER_MISSING));
            return new ModelAndView(RESULT_PAGE);
        }
         orderDto.setBuyerName(currentUser.getNickName());
         orderDto.setBuyerMobile(currentUser.getUserPhone());
         orderDto.setUserId(userId);
        Order po=this.orderService.create(orderDto);
        super.success(model,po);
        return new ModelAndView(PAY_PAGE);
    }
}
