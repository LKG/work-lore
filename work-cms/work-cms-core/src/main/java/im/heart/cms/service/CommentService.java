package im.heart.cms.service;

import im.heart.cms.entity.Comment;
import im.heart.core.service.CommonService;

import java.math.BigInteger;

/**
 * 
 * 评论操作接口
 * @作者 LKG
 */
public interface   CommentService extends CommonService<Comment, BigInteger>{
	
	public static final String BEAN_NAME = "commentService";
	
}
