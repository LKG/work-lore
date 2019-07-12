
package im.heart.cms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 文章分类
 * @author lkg
 *
 */
@Entity
@Table(name = "cms_article_category")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "cmsArticleCategorySequenceGenerator", sequenceName = "cms_article_category_sequence")
public class ArticleCategory implements TreeEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3870304630493086027L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private Long id;
	
	/** 名称 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false,name="NAME")
	private String name;
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false,name="CODE")
	private String code;
	/** 页面标题. */
	@Column(name = "SEO_TITLE", nullable = false)
	private String seoTitle;

	/** 页面关键词. */
	@Length(max = 200)
	@Column(name = "SEO_KEYWORDS", nullable = false)
	private String seoKeywords;

	@Length(max = 200)
	@Column(name = "image_url", nullable = false)
	private String imageUrl;


	/** 页面描述 . */
	@Length(max = 200)
	@Column(name = "SEO_DESCRIPTION", nullable = false)
	private String seoDescription;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	/** 层级 */
	@JSONField (serialize=false)
	@Column(name = "LEVEL", nullable = false)
	private Integer level;

	/**
	 * //父类节点ID
	 */
	@NotNull
	@Column(name = "PARENT_ID",length=32,nullable = false)
	private BigInteger parentId=BigInteger.ZERO;

	@Formula(value = "( exists(select 1 from cms_article_category model where model.parent_id = id) )")
	private boolean hasChildren;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
    }
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
    }

	public String getSeparator() {
		return TREE_PATH_SEPARATOR;
	}
	
	public String makeSelfParentIds() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHasChildren() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isRoot() {
		if (this.getParentId() != null&& BigInteger.ZERO.equals(this.getParentId())) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isLeaf() {
		if (isRoot()) {
			return false;
		}
		if (isHasChildren()) {
			return false;
		}
		return true;
	}
}
