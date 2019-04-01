package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : FrameUserConnect Repository
 */
@Repository
public interface FrameUserConnectRepository extends JpaRepository<FrameUserConnect, BigInteger>, JpaSpecificationExecutor<FrameUserConnect> {

}
