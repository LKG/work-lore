package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameUserOrg;
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
 * @Desc : 用户机构关联 Repository
 */
@Repository
public interface FrameUserOrgRepository extends JpaRepository<FrameUserOrg, BigInteger> ,JpaSpecificationExecutor<FrameUserOrg>{
	/**
	 * 
	 * 根据用户id,查询用户拥有机构
	 * @param userId
	 * @return
	 */
	public List<FrameUserOrg> findByUserId(BigInteger userId);

	/**
	 *  根据用户id,删除用户机构
	 * @param userId
	 */
	@Modifying
	@Transactional(rollbackOn = Exception.class)
	@Query("DELETE FROM FrameUserOrg model WHERE model.userId = :userId")
	public void deleteByUserId(@Param("userId") BigInteger userId);

	/**
	 * 更新用户默认机构// 设置默认机构
	 * @param userId
	 * @param relateId
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackOn = Exception.class)
	@Query("UPDATE FrameUserOrg model SET  model.isDefault= (CASE  WHEN model.relateId = :relateId  THEN true ELSE false END)  WHERE  model.userId= :userId")
	public void updateUserDefaultOrg(@Param("userId") BigInteger userId, @Param("relateId") BigInteger relateId);
}
