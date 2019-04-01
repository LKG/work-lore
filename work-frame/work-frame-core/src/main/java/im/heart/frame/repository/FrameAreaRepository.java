package im.heart.frame.repository;

import im.heart.frame.entity.FrameArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gg
 * @desc 区域表 Crud接口
 */
@Repository
public interface FrameAreaRepository extends JpaRepository<FrameArea, String> ,JpaSpecificationExecutor<FrameArea> {

}
