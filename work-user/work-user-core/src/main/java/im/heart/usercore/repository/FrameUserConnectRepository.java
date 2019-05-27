package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : FrameUserConnect Repository
 */
@Repository
public interface FrameUserConnectRepository extends QuerydslPredicateExecutor<FrameUserConnect>, JpaRepository<FrameUserConnect, BigInteger>, JpaSpecificationExecutor<FrameUserConnect> {

}
