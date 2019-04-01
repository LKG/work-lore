package im.heart.usercore.repository;

import im.heart.usercore.entity.FramePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : FramePermission Repository
 */
@Repository
public interface FramePermissionRepository extends JpaRepository<FramePermission, BigInteger> ,JpaSpecificationExecutor<FramePermission> {

}
