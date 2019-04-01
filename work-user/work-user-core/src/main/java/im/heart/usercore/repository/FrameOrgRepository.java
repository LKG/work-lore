package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : 机构表CROD 接口
 */
@Repository
public interface FrameOrgRepository extends JpaRepository<FrameOrg, BigInteger> ,JpaSpecificationExecutor<FrameOrg> {

}
