package im.heart.cms.service;

import im.heart.cms.entity.Article;
import im.heart.core.service.CommonService;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @功能说明：文章操作接口
 * @作者 LKG
 */
public interface   ArticleService extends CommonService<Article, BigInteger>{
	
	public static final String BEAN_NAME = "articleService";
	/**
	 * 
	 * @功能说明：批量保存
	 * @param entities
	 * @return
	 */
	public List<Article>  saveAll(Iterable<Article> entities);


	public void addUpdateHitsTask(BigInteger id);

	public void updateHitsById(BigInteger id);
}
