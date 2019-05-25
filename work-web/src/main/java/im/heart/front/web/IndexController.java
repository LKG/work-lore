package im.heart.front.web;


import com.google.common.collect.Lists;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.entity.Article;
import im.heart.cms.entity.QArticle;
import im.heart.cms.service.ArticleService;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.QPeriodical;
import im.heart.media.service.PeriodicalService;
import im.heart.media.vo.PeriodicalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
@Slf4j
public class IndexController extends AbstractController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private PeriodicalService periodicalService;
	@RequestMapping(value={"/index","/",""},method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response,
								ModelMap model) {
		articlesTop(request,model);
		docsFree10(request,model);
		super.success(model);
		return new ModelAndView("index");
	}
	public void articlesTop(HttpServletRequest request,ModelMap model){
		QArticle qArticle=QArticle.article;
		Predicate predicate= qArticle.isPub.eq(Boolean.TRUE);
		predicate=ExpressionUtils.and(predicate,qArticle.isDeleted.eq(Boolean.FALSE));
		OrderSpecifier<Date> sortOrder = qArticle.pushTime.desc();
		List<ArticleDTO> pag = this.articleService.findAll(predicate, 13,sortOrder);
		model.put("articles",pag);
	}

	public void docsFree10(HttpServletRequest request,ModelMap model){
		QPeriodical qPeriodical= QPeriodical.periodical;
		Predicate predicate= qPeriodical.checkStatus.eq(Status.enabled);
		predicate=ExpressionUtils.and(predicate,qPeriodical.finalPrice.eq(new BigDecimal(0)));
		OrderSpecifier<Long> sortOrder = qPeriodical.downTimes.desc();
		List<Periodical> list = this.periodicalService.findAll(predicate, 10,sortOrder);
		model.put("freeDocs",list);
	}

}
