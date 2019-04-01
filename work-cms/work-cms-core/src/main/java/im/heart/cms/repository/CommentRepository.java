package im.heart.cms.repository;


import im.heart.cms.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @desc 评论接口
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, BigInteger> ,JpaSpecificationExecutor<Comment> {


}
