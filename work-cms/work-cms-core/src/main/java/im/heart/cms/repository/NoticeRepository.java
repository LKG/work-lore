package im.heart.cms.repository;

import im.heart.cms.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc 通知公告 接口
 */
@Repository
public interface NoticeRepository  extends JpaRepository<Notice, BigInteger> ,JpaSpecificationExecutor<Notice> {

}
