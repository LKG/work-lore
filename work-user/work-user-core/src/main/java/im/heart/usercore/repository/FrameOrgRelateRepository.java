package im.heart.usercore.repository;

import im.heart.usercore.entity.FrameOrgRelate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : 机构关联表，设置机构非直属关联关系  Repository
 */
@Repository
public interface FrameOrgRelateRepository extends JpaRepository<FrameOrgRelate, BigInteger> ,JpaSpecificationExecutor<FrameOrgRelate> {
	
}
