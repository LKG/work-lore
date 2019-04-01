package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * 数据字典明细表
 */
@Entity()
@Table(name = "dic_frame_dict_item")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameDictItem implements TreeEntity<BigInteger> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ITEM_ID", nullable = false, unique = true, updatable = false)
	private BigInteger itemId;

	@NotBlank
	@Column(name = "ITEM_CODE", nullable = false,length=64)
	private String itemCode;
	@NotBlank
	@Column(name = "ITEM_NAME", nullable = false,length=64)
	private String itemName;

	@NotBlank
	@Column(name = "ITEM_VALUE", nullable = false,length=64)
	private String itemValue;

	@NotNull
	@Column(name = "DICT_Id", nullable = false,length=32)
	private BigInteger dictId;

	@Column(name = "DICT_CODE", nullable = false,length=64)
	private String dictCode;

	@Column(name = "ITEM_DESC", nullable = false,length=512)
	private String itemDesc;
	/**
	 * // 父类Id
	 */
	@Column(length = 20, name = "PARENT_ID",nullable = false)
	private BigInteger parentId=BigInteger.ZERO;
	/**
	 * 父类名称
	 */
	@Transient
	private String parentName="";
	
	@JSONField (serialize=false)
	@Min(0)
	@Column(name = "LEVEL", nullable = false)
	private Integer level=0;
	
	@NotNull
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" ,updatable = false)
	private Date modifyTime;
	
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		if(parentId==null){
			parentId=BigInteger.ZERO;
		}
		modifyTime = new Date();
    }
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
    }
	@Formula(value = "( exists(select 1 from dic_frame_dict_item model where model.parent_id = item_id) )")
	private boolean hasChildren;
	

	public String getSeparator() {
		return "/";
	}


	public String makeSelfParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigInteger getParentId() {
		return parentId;
	}

	public String getParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIcon() {
		return "";
	}

	@Override
	public boolean isRoot() {
		BigInteger parentId =this.getParentId();
		if (parentId != null&& BigInteger.ZERO.equals(parentId)) {
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
