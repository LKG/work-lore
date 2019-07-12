package im.heart.cms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.utils.FreeMarkerUtils;
import lombok.Data;
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
 * @Desc 文章实体类
 */
@Entity()
@Table(name = "cms_article")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_article_sequence")
public class Article implements AbstractEntity<BigInteger>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3901099490416617775L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Article.class);

	/** 点击数缓存名称. */
	public static final String HITS_CACHE_NAME = "articleHits";
	/** 点击数缓存更新间隔时间. */
	public static final int HITS_CACHE_INTERVAL = 600000;
	private static final Integer MAX_TITLE_LENGTH = 18;
	/** 内容分页长度 . */
	private static final int PAGE_CONTENT_LENGTH = 800;

	/** 内容分页符 . */
	private static final String PAGE_BREAK_SEPARATOR = "<hr class=\"pageBreak\" />";

	/** 段落分隔符配比. */
	private static final Pattern PARAGRAPH_SEPARATOR_PATTERN = Pattern.compile("[,;\\.!?，；。！？]");

	/** 静态路径. */
	private static String staticPath="";
	
	@Id
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	/** 标题 . */
	@NotEmpty
	@NotBlank
	@Length(min=1,max = 200)
	@Column(name = "TITLE", nullable = false,updatable = false, length=200)
	private String title;

	/** 作者. */
	@Length(max = 200)
	@Column(name = "AUTHOR", nullable = false,updatable = false)
	private String author="";

	/**
	 * 文章来源
	 */
	@Length(max = 200)
	@Column(name = "SOURCE", nullable = false,updatable = false)
	private String source="";

	/** 内容 . */
	@NotBlank
	@Length(min=3)
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "SUMMARY")
	private String summary;

	/** 页面标题. */
	@Column(name = "SEO_TITLE", nullable = false)
	@Length(max = 512)
	private String seoTitle="";

	/** 页面关键词. */
	@Length(max = 512)
	@Column(name = "SEO_KEYWORDS", nullable = false)
	private String seoKeywords="";

	/** 页面描述 . */
	@Length(max = 512)
	@Column(name = "SEO_DESCRIPTION", nullable = false)
	private String seoDescription="";

	@Column(name = "URL", nullable = false)
	private String url;
	/**
	 * 原始地址 不序列化
	 */
	@JSONField(serialize = false)
	@Column(name = "OBTAIN_URL", nullable = false)
	private String obtainUrl;

	/** 是否发布. */
	@NotNull
	@Column(name = "IS_PUB", nullable = false)
	private Boolean isPub=Boolean.FALSE;

	/** 是否置顶. */
	@NotNull
	@Column(name = "IS_TOP",nullable = false)
	private Boolean isTop=Boolean.FALSE;

	/** 点击数 . */
	@Column(name = "HITS", nullable = false, length=20)
	private Long hits=0L;
	@Column(name = "IS_DELETED", nullable = false, length=20)
	private Boolean isDeleted=Boolean.FALSE;

	@Column(name = "ALLOW_COMMENT")
	private boolean allowComment=Boolean.FALSE;
	
	@Column(name = "RATE_TIMES")
	private BigInteger rateTimes=BigInteger.ZERO;
	
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	/** 页码 . */
	@Transient
	private Integer pageNumber;
	
	@Column(name = "CATEGORY_ID", nullable = false, length=20)
	private Long categoryId;

	@Column(name = "CATEGORY_CODE", nullable = false, length=20)
	private String categoryCode;

	@Formula(value = "(select model.name from cms_article_category model where model.id = category_id)")
	private String categoryName;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "PUSH_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pushTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
		if(pushTime==null){
			pushTime = new Date();
		}
    }
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
		if(isPub){
			pushTime = new Date();
		}
    }
	/**
	 * 获取内容.
	 * 
	 * @return 内容
	 */

	public String getContent() {
		if (pageNumber != null) {
			String[] pageContents = getPageContents();
			if (pageNumber < 1) {
				pageNumber = 1;
			}
			if (pageNumber > pageContents.length) {
				pageNumber = pageContents.length;
			}
			return pageContents[pageNumber - 1];
		} else {
			return content;
		}
	}

	/**
	 * 设置页面关键词.
	 * 
	 * @param seoKeywords
	 *            页面关键词
	 */
	public void setSeoKeywords(String seoKeywords) {
		if (seoKeywords != null) {
			seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
		}
		this.seoKeywords = seoKeywords;
	}

	/**
	 * 获取访问路径
	 * 
	 * @return 访问路径
	 */
	@Transient
	public String getPath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", getId());
		model.put("createTime", getCreateTime());
		model.put("modifyTime", getModifyTime());
		model.put("title", getTitle());
		model.put("seoTitle", getSeoTitle());
		model.put("seoKeywords", getSeoKeywords());
		model.put("seoDescription", getSeoDescription());
		model.put("pageNumber", getPageNumber());
		try {
			return FreeMarkerUtils.renderString(staticPath, model);
		} catch (Exception e) {
			LOGGER.warn("[Article]-IOException " + e.getMessage());
		}
		return null;
	}

	/**
	 * 获取文本内容
	 * 
	 * @return 文本内容
	 */
	@Transient
	public String getText() {
		if (getContent() != null) {
			return Jsoup.parse(getContent()).text();
		}
		return null;
	}

	/**
	 * 获取分页内容
	 * 
	 * @return 分页内容
	 */
	@Transient
	public String[] getPageContents() {
		if (StringUtils.isEmpty(content)) {
			return new String[] { "" };
		}
		if (content.contains(PAGE_BREAK_SEPARATOR)) {
			return content.split(PAGE_BREAK_SEPARATOR);
		} else {
			List<String> pageContents = new ArrayList<String>();
			Document document = Jsoup.parse(content);
			List<Node> children = document.body().childNodes();
			if (children != null) {
				int textLength = 0;
				StringBuffer html = new StringBuffer();
				for (Node node : children) {
					if (node instanceof Element) {
						Element element = (Element) node;
						html.append(element.outerHtml());
						textLength += element.text().length();
						if (textLength >= PAGE_CONTENT_LENGTH) {
							pageContents.add(html.toString());
							textLength = 0;
							html.setLength(0);
						}
					} else if (node instanceof TextNode) {
						TextNode textNode = (TextNode) node;
						String text = textNode.text();
						String[] contents = PARAGRAPH_SEPARATOR_PATTERN
								.split(text);
						Matcher matcher = PARAGRAPH_SEPARATOR_PATTERN
								.matcher(text);
						for (String content2 : contents) {
							String contentTemp = content2;
							if (matcher.find()) {
								contentTemp += matcher.group();
							}
							html.append(contentTemp);
							textLength += contentTemp.length();
							if (textLength >= PAGE_CONTENT_LENGTH) {
								pageContents.add(html.toString());
								textLength = 0;
								html.setLength(0);
							}
						}
					}
				}
				String pageContent = html.toString();
				if (StringUtils.isNotEmpty(pageContent)) {
					pageContents.add(pageContent);
				}
			}
			return pageContents.toArray(new String[pageContents.size()]);
		}
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	@Transient
	public int getTotalPages() {
		return getPageContents().length;
	}

	@Transient
	public String getShortTitle() {
		if (!StringUtils.isBlank(title) && (title.length() > MAX_TITLE_LENGTH)) {
			return title.substring(0, MAX_TITLE_LENGTH) + "...";
		}
		return title;
	}

}
