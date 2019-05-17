package im.heart.cms.repository;

import im.heart.cms.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc 文章分类接口
 */
@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Long> ,JpaSpecificationExecutor<ArticleCategory> {


}
