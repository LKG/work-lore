package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import im.heart.core.enums.Status;
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
 * @desc 机构实体类
 */
@Entity
@Table(name = "dic_frame_org")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameOrg implements TreeEntity<BigInteger> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORG_ID", nullable = false, updatable = false, length = 20)
	private BigInteger id;

	/**
	 * 标志log
	 */
	@NotNull
	@Column(name = "ORG_LOGO", length = 128)
	private String logo = "";

	/**
	 * 地区CODE
	 */
	@NotNull
	@Column(name = "AREA_CODE", nullable = false, length = 32)
	private String areaCode;

	/**
	 * 机构号
	 */
	@NotNull
	@Length(min=2, max=32)
	@Column(name = "ORG_CODE", nullable = false, updatable = false, length = 32)
	private String orgCode;

	/**
	 * 机构名称
	 */
	@NotBlank(groups = {})
	@Length(min=2, max=128)
	@Column(name = "ORG_NAME", nullable = false, length = 128)
	private String name;
	/**
	 * 机构地址
	 */
	@Column(name = "ORG_ADDRESS", length = 128)
	private String address;

	/**
	 * 联系人
	 */
	@Column(name = "ORG_CONTACTS", length = 64)
	private String contacts;

	/**
	 * 联系人电话 固话
	 */
	@Column(name = "TEL_PHONE", length = 64)
	private String telPhone;
	/**
	 *  联系人电话
	 */
	@NotBlank(groups = {})
	@Column(name = "MOBILE_PHONE", length = 64)
	private String mobilePhone;

	/**
	 * 机构类型
	 */
	@NotBlank(groups = {})
	@Column(name = "ORG_TYPE", updatable = false, nullable = false, length = 32)
	private String type;

	/**
	 * 机构分类
	 */
	@Column(name = "ORG_CATEGORY", length = 128)
	private String category;

	@Column(name = "STATUS", nullable = false, length = 16)
	@Enumerated(EnumType.STRING)
	private Status status = Status.pending;

	@JSONField(serialize = false)
	@Min(1)
	@Column(name = "LEVEL", nullable = false)
	private Integer level = 1;
	@NotNull
	@Column(name = "PARENT_ID", length = 20, nullable = false)
	private BigInteger parentId = BigInteger.ZERO;

	@Column(name = "parent_ids")
	private String parentIds="0";
	/**
	 * // 父类名称
	 */
	@JSONField(serialize = false)
	@Transient
	private String parentName = "无";
	/**
	 * //是否有子节点
	 */
	@Formula(value = "( exists(select 1 from dic_frame_org model where model.parent_id = org_id) )")
	private boolean hasChildren;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false, name = "CREATE_TIME", updatable = false)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false, name = "MODIFY_TIME")
	private Date modifyTime;

	@JSONField(serialize = false)
	@Column(name = "REMARK", nullable = false, length = 256)
	private String remark;

	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
	}

	@Override
	public boolean isRoot() {
		BigInteger parentId = this.getParentId();
		if (parentId != null && BigInteger.ZERO.equals(parentId)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLeaf() {
		if (isRoot() || isHasChildren()) {
			return false;
		}
		return true;
	}
}
