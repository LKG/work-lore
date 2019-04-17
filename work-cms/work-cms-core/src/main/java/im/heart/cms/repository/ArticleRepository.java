package im.heart.cms.repository;

import im.heart.cms.dto.ArticleProjection;
import im.heart.cms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author gg
 * @desc 文章接口
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, BigInteger>, JpaSpecificationExecutor<Article> {
    /**
     * 更新点击次数
     * @param id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE Article model SET hits=hits+1 WHERE model.id = :id")
    public void updateHitsById(@Param("id") BigInteger id);

    /**
     * 查询上一篇下一篇
     * @param id
     * @param categoryId
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT model.id AS id ,model.title AS title FROM cms_article model WHERE  ( model.id >:id  or model.id <:id ) AND model.id <>:id AND model.category_id = :categoryId AND model.is_pub = 1 ORDER BY  model.id LIMIT 2")
    public List<ArticleProjection> queryNearById(@Param("id") BigInteger id, @Param("categoryId") BigInteger categoryId);

}
