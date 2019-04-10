package im.heart.web;

import im.heart.core.web.AbstractController;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/payment")
public class PaymentController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping(value = "/choose",method = RequestMethod.GET)
	protected ModelAndView showLoginForm(HttpServletRequest request,
			ModelMap model) throws Exception {
		try {
		
			return new ModelAndView("payment/payment-choose", model);
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
			return new ModelAndView(RESULT_PAGE);
		}
	}


}
