package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @Desc : 用户角色关联 Repository
 */
@Repository
public interface FrameUserRoleRepository extends JpaRepository<FrameUserRole, BigInteger>  ,JpaSpecificationExecutor<FrameUserRole>{
	/**
	 * 
	 * 根据用户id,查询用户拥有角色
	 * @param userId
	 * @return
	 */
	public List<FrameUserRole> findByUserId(BigInteger userId);


	/**
	 * 根据用户id,删除用户拥有角色记录
	 * @param userId
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM FrameUserRole model WHERE model.userId = :userId")
	public void deleteByUserId(@Param("userId") BigInteger userId);
}
