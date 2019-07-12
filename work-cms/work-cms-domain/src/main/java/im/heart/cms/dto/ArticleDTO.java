package im.heart.cms.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ArticleDTO {
    private BigInteger id;
    private String title;
    private String author;
    private String source;
    private String categoryName;
    private Date pushTime;
    private String summary;
    private Boolean allowComment=Boolean.FALSE;
    private BigInteger rateTimes=BigInteger.ZERO;
    private Long hits;
   public ArticleDTO(BigInteger id){
        this.id=id;
    }
    @Transient
    public String getShortTitle() {
        if (!StringUtils.isBlank(title) && (title.length() > 14)) {
            return title.substring(0, 14) + "...";
        }
        return title;
    }
}