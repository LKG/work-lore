package im.heart.media.repository;

import im.heart.media.entity.PeriodicalPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * PeriodicalPackage处理Repository
 */
@Repository
public interface PeriodicalPackageRepository extends JpaRepository<PeriodicalPackage, BigInteger> ,JpaSpecificationExecutor<PeriodicalPackage> {

}
