package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * 材料分类表
 */

@Entity()
@Table(name = "media_periodical_category")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalCategory implements TreeEntity<BigInteger> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1163397145222684789L;
	
	public PeriodicalCategory(){
		
	}
	public PeriodicalCategory(BigInteger categoryId, String categoryCode, String categoryName, BigInteger parentId, Integer level, Status status){
		this.categoryId=categoryId;
		this.categoryCode=categoryCode;
		this.categoryName=categoryName;
		this.parentId=parentId;
		this.level=level;
		this.status=status;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "CATEGORY_ID", nullable = false, unique = true, updatable = false)
	private BigInteger categoryId;
	
	@NotNull
	@Column(name = "CATEGORY_NAME", nullable = false,length=128)
	private String categoryName;
	
	@Column(name = "CATEGORY_CODE", nullable = false,updatable = false, length=32)
	private String categoryCode;
	
	@NotNull
	@Column(length = 32, name = "PARENT_ID", nullable = false)
	private BigInteger parentId;
	@Column(name = "STATUS", nullable = false, length = 32)
	@Enumerated(EnumType.STRING)
	private Status status ;
	@JSONField (serialize=false)
	@Column(name = "LEVEL", nullable = false)
	private Integer level=1;
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" )
	private Date modifyTime;
	
	@Column(name = "REMARK", nullable = false, length=512)
	private String remark;
	@Formula(value = "(select model.category_name from media_periodical_category model where model.category_id = parent_id)")
	private String parentName="";
	/**
	 * //是否有子节点
	 */
	@Formula(value = "(select count(*) from media_periodical_category model where model.parent_id = category_id)")
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
		// TODO Auto-generated method stub
		return null;
	}


	public String makeSelfParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoot() {
		if (this.getParentId() != null
				&& BigInteger.ZERO.equals(this.getParentId())) {
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
