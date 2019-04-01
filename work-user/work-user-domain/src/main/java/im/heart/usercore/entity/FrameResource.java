package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import im.heart.core.enums.Status;
import im.heart.core.utils.StringUtilsEx;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;
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
 * 资源表
 */
@Entity()
@Table(name = "dic_frame_resource")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameResource implements TreeEntity<BigInteger>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8658734097434305667L;
	public enum ResourceType {
		menu(1, "menu", "菜单"), button(2,"button", "按钮"),url(3, "url", "外部Url");
		public String code;
		public int intValue;
		public final String info;
		private ResourceType(int intValue, String code, String info) {
			this.code = code;
			this.intValue = intValue;
			this.info = info;
		}
		public static ResourceType findByIntValue(int intValue) {
			for (ResourceType resourceType : ResourceType.values()) {
				if (resourceType.intValue == intValue) {
					return resourceType;
				}
			}
			return ResourceType.menu;
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "RESOURCE_ID", nullable = false,updatable = false)
	private BigInteger resourceId;
	
	@NotBlank
	@Length(min=3,max = 32)
	@Column(length = 32, name = "RESOURCE_CODE", nullable = false,unique = true,  updatable = false)
	private String resourceCode;

	@NotBlank
	@Length(min=2,max = 64)
	@Column(length = 64, name = "RESOURCE_NAME", nullable = false)
	private String resourceName;

	@Column(length = 16, name = "RESOURCE_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType= ResourceType.menu;

	@NotBlank
	@Length(max = 128)
	@Column(length = 128, name = "RESOURCE_URL", nullable = false)
	private String resourceUrl;

	@NotBlank
	@Length(max = 32)
	@Column(length = 32, name = "RESOURCE_TARGET", nullable = false)
	private String resourceTarget;

	/**
	 * // 菜单描述
	 */
	@NotBlank
	@Length(max = 256)
	@Column(length = 256, name = "RESOURCE_DESC", nullable = false)
	private String resourceDesc;
	/**
	 * 菜单图标
	 */
	@NotBlank
	@Length(max = 128)
	@Column(length = 128, name = "RESOURCE_ICON", nullable = false)
	private String icon;
	/**
	 * 排序号
	 */
	@Min(0)
	@Column(name = "RESOURCE_SORT", nullable = false)
	private Integer resourceSort=0;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	@NotNull
	@Column(length = 16, name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status =Status.pending;

	@NotNull
	@Column(length = 32, name = "PARENT_ID", nullable = false)
	private BigInteger parentId=BigInteger.ZERO;
	
	@Formula(value = "(select model.resource_name from dic_frame_resource model where model.resource_id = parent_id)")
	private String parentName;
	
	@Column(length = 128, name = "PERMISSION_IDS")
	private String permissionIds;
	
	@JSONField(serialize=false)
	@Column(length = 512, name = "PERMISSIONS")
	private String permissionStr;
	
//	@Transient
//	List<FramePermissionVO> permissions=new ArrayList<FramePermissionVO>();
//	public List<FramePermissionVO> getPermissions() {
//		if(StringUtilsEx.isNotBlank(permissionStr)){
//			return JSON.parseArray(permissionStr, FramePermissionVO.class);
//		}
//		return permissions;
//	}
	/**
	 * // 父类Id
	 */
	@Column(length = 256, name = "PARENT_IDS")
	private String parentIds;
		
	/**
	 * //是否有子节点
	 */
	@Formula(value = "( exists(select 1 from dic_frame_resource model where model.parent_id = resource_id) )")
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
		return "/";
	}

	public String getParentName() {
		if(StringUtilsEx.isBlank(parentName)){
			return "root";
		}
		return parentName;
	}


	public String makeSelfParentIds() {
		return getParentIds() + getResourceId() + getSeparator();
	}


	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getParentIds() {
		return parentIds;
	}

	@Override
	public boolean isHasChildren() {
		return hasChildren;
	}

	public String getIcon() {
		return icon;
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
