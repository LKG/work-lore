package im.heart.front.web;


import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.PeriodicalPackage;
import im.heart.media.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Optional;

/**
 *
 * @author gg
 * @desc 版权申诉
 */
@Controller
public class CopyrightAppealController extends AbstractController {
	protected static final String apiVer = "/index";

	@Autowired
	private PeriodicalService periodicalService;

	@RequestMapping(value={"/copyrightAppeal",apiVer+"/copyrightAppeal"},method = RequestMethod.GET)
	public ModelAndView copyrightAppeal(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "bid", required = false) BigInteger bid,
			ModelMap model) {
		Optional<Periodical> optional = this.periodicalService.findById(bid);
		if(!optional.isPresent()){
			ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_MISSING);
			super.fail(model,responseError);
			return new ModelAndView(ERROR_PAGE);
		}
		Periodical periodical=optional.get();
		if(!CommonConst.FlowStatus.processed.equals(periodical.getStatus())){
			ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_MISSING);
			super.fail(model,responseError);
			return new ModelAndView(ERROR_PAGE);
		}
		if(!Status.enabled.equals(periodical.getCheckStatus())){
			ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_MISSING);
			super.fail(model,responseError);
			return new ModelAndView(ERROR_PAGE);
		}
		super.success(model,periodical);
		return new ModelAndView("front/copyrightAppeal");
	}

}
