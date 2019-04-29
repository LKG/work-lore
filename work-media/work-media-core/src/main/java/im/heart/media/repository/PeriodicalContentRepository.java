package im.heart.media.repository;

import im.heart.media.entity.PeriodicalContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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
}
