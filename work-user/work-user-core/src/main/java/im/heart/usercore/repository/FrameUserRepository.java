package im.heart.usercore.repository;


import im.heart.usercore.entity.FrameUser;
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
 * @Desc : 用户Repository
 */
@Repository
public interface FrameUserRepository extends JpaRepository<FrameUser, BigInteger> ,JpaSpecificationExecutor<FrameUser> {
	/**
	 * 
	 * 根据用户名查找用户信息
	 * @param userName
	 * @return
	 */
	public FrameUser findByUserName(String userName);
	
	/**
	 * 
	 * 根据邮箱查找
	 * @param userEmail
	 * @return
	 */
	public FrameUser findByUserEmail(String userEmail);
	
	/**
	 * 
	 * 根据电话号码查找
	 * @param userPhone
	 * @return
	 */
	public FrameUser findByUserPhone(String userPhone);

	/**
	 * // 设置头像
	 * @param userId
	 * @param headImgUrl
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackFor = Exception.class)
	@Query("UPDATE FrameUser model SET  model.headImgUrl= :headImgUrl WHERE  model.userId= :userId  ")
	public void updateUserHeadImgUrl(@Param("userId") BigInteger userId, @Param("headImgUrl") String headImgUrl);

	/**
	 * // 设置默认机构
	 * @param userId
	 * @param defaultOrgId
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackFor = Exception.class)
	@Query("UPDATE FrameUser model SET  model.relateOrg.id= :defaultOrgId WHERE  model.userId= :userId  ")
	public void updateUserDefaultOrg(@Param("userId") BigInteger userId, @Param("defaultOrgId") BigInteger defaultOrgId);
}
