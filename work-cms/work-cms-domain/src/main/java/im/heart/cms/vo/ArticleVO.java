package im.heart.cms.vo;

import im.heart.cms.entity.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ArticleVO extends Article {

    private Boolean isFollow=Boolean.FALSE;

    public ArticleVO(Article po){
        BeanUtils.copyProperties(po, this);
    }


}
