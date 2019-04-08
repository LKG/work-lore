package im.heart.cms.repository;

import im.heart.cms.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("update Article model set hits=hits+1 WHERE model.id = :id")
    public void updateHitsById(@Param("id") BigInteger id);

    /**
     * 查询上一篇下一篇
     * @param id
     * @param categoryId
     * @return
     */
    @Query(value = "select model.id ,model.title from cms_article model where  ( model.id >:id  or model.id <:id ) and id <>:id and model.category_id = :categoryId and model.is_pub = 1 order by  model.id limit 2",nativeQuery = true)
    public List<Article> findNearId(@Param("id") BigInteger id, @Param("categoryId") BigInteger categoryId);
}
