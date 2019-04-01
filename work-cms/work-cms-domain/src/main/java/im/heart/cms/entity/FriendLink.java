package im.heart.cms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author lkg
 * @Desc : 友链表
 */
@Entity
@Table(name = "cms_friend_link")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "cmsFriendLinkSequenceGenerator", sequenceName = "cms_friend_link_sequence")
public class FriendLink implements AbstractEntity<BigInteger> {

	public enum CheckStatus {
		pending(-2, "pending", "未审核"), waiting(-1, "waiting", "审核中"), fail(0, "fail", "审核不通过"), success(1, "success",
				"审核通过");
		public String code;
		public int intValue;
		public final String info;

		CheckStatus(int intValue, String code, String info) {
			this.code = code;
			this.intValue = intValue;
			this.info = info;
		}

		public static CheckStatus findByIntValue(int intValue) {
			for (CheckStatus status : CheckStatus.values()) {
				if (status.intValue == intValue) {
					return status;
				}
			}
			return CheckStatus.fail;
		}
	}
	/**
	 * 类型
	 */
	public enum Type {
		/** 文本 */
		text,
		/** 图片 */
		image
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;

	@NotEmpty
	@NotBlank
	@Length(min = 1, max = 200)
	@Column(name = "NAME", nullable = false,length = 200)
	private String name;

	@NotEmpty
	@NotBlank
	@Length(min = 1, max = 200)
	@Column(name = "URL", nullable = false, length = 200)
	private String url;
	@NotEmpty
	@NotBlank
	@Length(min = 1, max = 200)
	@Column(name = "LOGO", nullable = false, length = 200)
	private String logo;
	@Column(name = "STATUS", nullable = false, length = 16)
	@Enumerated(EnumType.STRING)
	private Status status = Status.pending;
	
	@Column(name = "TYPE", nullable = false, length = 16)
	@Enumerated(EnumType.STRING)
	private Type type = Type.text;

	/**
	 * 审核认证状态，已审核，待审核，审核中，审核驳回
	 */
	@Column(nullable = false, name = "CHECK_STATUS", length = 16)
	@Enumerated(EnumType.STRING)
	private CheckStatus checkStatus = CheckStatus.pending;
	@Length(max = 200)
	@Column(name = "REMARK", nullable = false, length = 200)
	private String remark;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
	}

}
