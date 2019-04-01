package im.heart.frame.repository;

import im.heart.frame.entity.FrameDictItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * 数据字典子表 Crud 接口
 */
@Repository
public interface FrameDictItemRepository  extends JpaRepository<FrameDictItem, BigInteger> ,JpaSpecificationExecutor<FrameDictItem> {

}
