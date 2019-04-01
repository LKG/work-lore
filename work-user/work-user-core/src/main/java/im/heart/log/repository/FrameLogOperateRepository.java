package im.heart.log.repository;

import im.heart.log.entity.FrameLogOperate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc 操作日志CROD 接口
 */
public interface FrameLogOperateRepository extends JpaRepository<FrameLogOperate, BigInteger>,JpaSpecificationExecutor<FrameLogOperate> {



}
