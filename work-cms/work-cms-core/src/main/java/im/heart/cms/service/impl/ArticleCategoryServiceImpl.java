package im.heart.cms.service.impl;

import im.heart.cms.entity.ArticleCategory;
import im.heart.cms.service.ArticleCategoryService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service(value = ArticleCategoryService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class ArticleCategoryServiceImpl extends CommonServiceImpl<ArticleCategory, BigInteger> implements ArticleCategoryService {

}
