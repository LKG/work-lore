package im.heart.frame.repository;

import im.heart.frame.entity.FrameTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc : FrameTask Crud 接口
 */
@Repository
public interface FrameTaskRepository extends JpaRepository<FrameTask, BigInteger> ,JpaSpecificationExecutor<FrameTask> {
	/**
	 * 批量删除
	 * @param ids
	 */
	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("DELETE FROM FrameTask model WHERE model.taskId  in (:ids)")
	public void delete(@Param("ids") BigInteger[] ids);
}
