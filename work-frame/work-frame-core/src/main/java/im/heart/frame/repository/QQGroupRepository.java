package im.heart.frame.repository;

import im.heart.frame.entity.QQGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : QQGroup Crud接口
 */
@Repository
public interface QQGroupRepository extends JpaRepository<QQGroup, BigInteger> ,JpaSpecificationExecutor<QQGroup> {


}
