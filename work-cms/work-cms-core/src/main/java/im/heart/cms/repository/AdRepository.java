package im.heart.cms.repository;


import im.heart.cms.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc Ad接口
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, BigInteger> ,JpaSpecificationExecutor<Ad> {


}
