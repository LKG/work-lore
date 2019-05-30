package im.heart.media.repository;

import im.heart.media.entity.PeriodicalLog;
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
 * Periodical日志处理Repository
 */
@Repository
public interface PeriodicalLogRepository extends JpaRepository<PeriodicalLog, BigInteger> ,JpaSpecificationExecutor<PeriodicalLog> {
    /**
     * 根据期刊Id 删除期刊信息
     * @param periodicalId
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query("DELETE FROM PeriodicalLog model WHERE model.periodicalId = :periodicalId")
    public void deleteByPeriodicalId(@Param("periodicalId") BigInteger periodicalId);
}
