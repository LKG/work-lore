package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc :角色Repository
 */
@Repository
public interface FrameRoleRepository extends JpaRepository<FrameRole, BigInteger> ,JpaSpecificationExecutor<FrameRole> {
	/**
	 * 根据角色code 查询
	 * @param roleCode
	 * @return
	 */
	public FrameRole findByRoleCode(String roleCode);
}
