package im.heart.cms.repository;


import im.heart.cms.entity.AdPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc AdPosition接口
 */
@Repository
public interface AdPositionRepository extends JpaRepository<AdPosition, BigInteger> ,JpaSpecificationExecutor<AdPosition> {


}
