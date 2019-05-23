package im.heart.cms.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class ArticleDTO {
    private BigInteger id;
    private String title;
    private String author;
    private String source;
    private String type;
    private Date pushTime;
    private String summary;
    private Boolean allowComment=Boolean.FALSE;
    private BigInteger rateTimes=BigInteger.ZERO;
    Long hits;
    @Transient
    public String getShortTitle() {
        if (!StringUtils.isBlank(title) && (title.length() > 14)) {
            return title.substring(0, 14) + "...";
        }
        return title;
    }
}