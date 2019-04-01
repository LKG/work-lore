package im.heart.media.repository;

import im.heart.media.entity.PeriodicalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * Periodical日志处理Repository
 */
@Repository
public interface PeriodicalLogRepository extends JpaRepository<PeriodicalLog, BigInteger> ,JpaSpecificationExecutor<PeriodicalLog> {

}
