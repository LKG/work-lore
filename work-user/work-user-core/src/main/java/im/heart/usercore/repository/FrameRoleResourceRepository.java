package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameRoleResource;
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
 * @Desc :用户角色关联 Repository
 */
@Repository
public interface FrameRoleResourceRepository extends JpaRepository<FrameRoleResource, BigInteger>,JpaSpecificationExecutor<FrameRoleResource>  {
	
	public List<FrameRoleResource> findByRoleCode(String roleCode);

	public List<FrameRoleResource> findByRoleIdIn(BigInteger... roleIds);
	public List<FrameRoleResource> findByRoleIdIn(Iterable<BigInteger> roleIds);
	public List<FrameRoleResource> findByRoleCodeIn(Iterable<String> roleCodes);

	public List<FrameRoleResource> findByRoleCodeIn(String... roleCodes);
	@Modifying
	@Transactional(rollbackOn = Exception.class)
	@Query("DELETE FROM FrameRoleResource model WHERE model.roleId = :roleId")
	public void deleteByRoleId(@Param("roleId") BigInteger roleId);
}
