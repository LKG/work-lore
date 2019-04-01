package im.heart.frame.repository;

import im.heart.frame.entity.FrameDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * 数据字典 Crud 接口
 */
@Repository
public interface FrameDictRepository extends JpaRepository<FrameDict, BigInteger> ,JpaSpecificationExecutor<FrameDict> {

}
