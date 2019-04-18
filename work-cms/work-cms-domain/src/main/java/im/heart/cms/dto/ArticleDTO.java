package im.heart.cms.dto;

import lombok.Data;

import java.math.BigInteger;
@Data
public class ArticleDTO {
    BigInteger id;
    String title;
    String author;
    Long hits;
}