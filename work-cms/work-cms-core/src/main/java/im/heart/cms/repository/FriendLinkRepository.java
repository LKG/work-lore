package im.heart.cms.repository;


import im.heart.cms.entity.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc FriendLink 接口
 */
@Repository
public interface FriendLinkRepository extends JpaRepository<FriendLink, BigInteger> ,JpaSpecificationExecutor<FriendLink> {


}
