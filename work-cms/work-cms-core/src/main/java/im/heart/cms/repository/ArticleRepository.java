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
    @Query("select id ,title from Article model where  ( model.id >:id  or model.id <:id ) and id <>:id and model.categoryId = :categoryId and model.isPub = true order by  model.id ")
    public Page<Article> findNearId(@Param("id") BigInteger id, @Param("categoryId") BigInteger categoryId, Pageable pageable);
}
