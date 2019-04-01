package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc :用户关注 Repository
 */
@Repository
public interface FrameUserFollowRepository extends JpaRepository<FrameUserFollow, BigInteger> ,JpaSpecificationExecutor<FrameUserFollow> {
	
}
