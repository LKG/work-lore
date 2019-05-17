package im.heart.cms.service;

import im.heart.cms.entity.ArticleCategory;
import im.heart.core.service.CommonService;

import java.math.BigInteger;

/**
 * 
 * @功能说明：文章分类操作接口
 * @作者 LKG
 */
public interface   ArticleCategoryService extends CommonService<ArticleCategory, Long>{
	
	public static final String BEAN_NAME = "articleCategoryService";

}
