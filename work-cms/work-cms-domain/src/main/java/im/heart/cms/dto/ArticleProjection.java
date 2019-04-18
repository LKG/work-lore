package im.heart.cms.dto;

import java.math.BigInteger;

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
     * 点击次数
     * @return
     */
    Long getHits();
}