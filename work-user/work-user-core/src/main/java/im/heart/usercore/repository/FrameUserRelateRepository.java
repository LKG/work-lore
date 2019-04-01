package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserRelate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : 用户关系表  Repository
 */
@Repository
public interface FrameUserRelateRepository extends JpaRepository<FrameUserRelate, BigInteger> ,JpaSpecificationExecutor<FrameUserRelate> {
	
}
