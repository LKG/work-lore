package im.heart.cms.service.impl;

import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.entity.Article;
import im.heart.cms.repository.ArticleRepository;
import im.heart.cms.service.ArticleService;
import im.heart.core.service.impl.CommonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service(value = ArticleService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class ArticleServiceImpl extends CommonServiceImpl<Article, BigInteger> implements ArticleService {
	protected static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	@Autowired
	private ArticleRepository articleRepository;
	@Override
	public List<Article> saveAll(Iterable<Article> entities) {
		return this.articleRepository.saveAll(entities);
	}

	@Override
	public Page<Article> findAll(Specification<Article> spec, Pageable pageable){
		return this.articleRepository.findAll(spec,pageable);
	}
	@Override
	public void updateHitsById(BigInteger id) {
		this.articleRepository.updateHitsById(id);
	}
	@Async
	@Override
	public void addUpdateHitsTask(BigInteger id) {
		this.updateHitsById(id);
	}
	@Cacheable(value="article",key="'article'+#id.toString()+'_'+#id.toString()")
	@Override
	public List<ArticleDTO> queryNearById(BigInteger id, BigInteger categoryId) {
		List<ArticleDTO> projections=this.articleRepository.queryNearById(id,categoryId);
		for (ArticleDTO projection:projections){
			logger.info("@@@@@@@@@@@@@@@:{},LastName:{},projection:{}",projection.getTitle());
		}
		System.out.println("###########################################");
		return projections;
	}

}
