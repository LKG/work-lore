package im.heart.cms.dto;

import java.math.BigInteger;
import java.util.Date;

public interface ArticleProjection{
    /**
     * id
     * @return
     */
    BigInteger getId();

    /**
     * 标题
     * @return
     */
    String getTitle();

    /**
     * 作者
     * @return
     */
    String getAuthor();

    /**
     * 类型
     * @return
     */
    String getType();

    /**
     * 发布时间
     * @return
     */
    Date getPushTime();

    /**
     * 是否允许评论
     * @return
     */
    Boolean getAllowComment();
    /**
     * 点击次数
     * @return
     */
    Long getHits();
}