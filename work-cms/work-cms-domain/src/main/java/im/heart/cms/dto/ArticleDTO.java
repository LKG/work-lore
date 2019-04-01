package im.heart.cms.dto;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.utils.FreeMarkerUtils;
import lombok.Data;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 
 * @author lkg
 * @Desc 文章Dto
 */
@Value
public class ArticleDTO implements AbstractEntity<BigInteger>{

	private BigInteger id;
	private String title;

	private String author="";

	private String source="";

	private String summary;

	/** 页面标题. */
	private String seoTitle="";

	/** 页面关键词. */
	private String seoKeywords="";

	/** 页面描述 . */
	private String seoDescription="";

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "TYPE", nullable = false)
	private String type;
	/** 是否发布. */
	private Boolean isPub=Boolean.FALSE;

	/** 是否置顶. */
	private Boolean isTop=Boolean.FALSE;

	/** 点击数 . */

	private Long hits=0L;

	private Boolean isDeleted=Boolean.FALSE;

	private boolean allowComment=Boolean.FALSE;

	private BigInteger rateTimes=BigInteger.ZERO;

	private BigInteger userId;
	/** 页码 . */
	@Transient
	private Integer pageNumber;

	private BigInteger categoryId;

	private String categoryName;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date pushTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	private Date modifyTime;


}
