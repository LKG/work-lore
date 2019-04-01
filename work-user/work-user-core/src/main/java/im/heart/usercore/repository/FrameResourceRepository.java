package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc :资源表Repository
 */
@Repository
public interface FrameResourceRepository extends JpaRepository<FrameResource, BigInteger> ,JpaSpecificationExecutor<FrameResource> {
	/**
	 * 
	 * 根据ResourceCode名查找FrameResource信息
	 * @param resourceCode
	 * @return
	 */
	public FrameResource findByResourceCode(String resourceCode);
}
