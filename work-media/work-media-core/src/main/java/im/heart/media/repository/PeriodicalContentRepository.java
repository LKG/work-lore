package im.heart.media.repository;

import im.heart.media.entity.PeriodicalContent;
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
 * PeriodicalContent处理Repository
 */
@Repository
public interface PeriodicalContentRepository extends JpaRepository<PeriodicalContent, BigInteger> ,JpaSpecificationExecutor<PeriodicalContent> {

    /**
     * 根据periodicalId 查询内容
     * @param periodicalId
     * @return
     */
    List<PeriodicalContent> findByPeriodicalId(BigInteger periodicalId);

    /**
     * 根据期刊Id 删除期刊信息
     * @param periodicalId
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query("DELETE FROM PeriodicalContent model WHERE model.periodicalId = :periodicalId")
    public void deleteByPeriodicalId(@Param("periodicalId") BigInteger periodicalId);
}
