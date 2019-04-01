package im.heart.cms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "cms_comment")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "cmsCommentSequenceGenerator", sequenceName = "cms_comment_sequence")
public class Comment implements AbstractEntity<BigInteger> {
	
	public enum CheckStatus {
		pending(-2, "pending", "未审核"), 
		waiting(-1, "waiting", "审核中"), 
		fail(0,	"fail", "审核不通过"), 
		success(1, "success", "审核通过");
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "COMMENT_ID", nullable = false, unique = true, updatable = false)
	private BigInteger commentId;
	/**
	 * //父类节点ID
	 */
	@NotNull
	@Column(name = "PARENT_ID",length=32,nullable = false)
	private BigInteger parentId=BigInteger.ZERO;
	
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	@NotBlank
	@Length(min=3)
	@Column(name = "CONTENT", nullable = false)
	private String content;
	
	@Column(name = "USER_HOST" ,updatable = false, nullable = false)
	private String userHost;
	/**
	 * 审核认证状态，已审核，待审核，审核中，审核驳回
	 */
	@Column(nullable = false, name = "CHECK_STATUS" ,length=16)
	@Enumerated(EnumType.STRING)
	private CheckStatus checkStatus = CheckStatus.pending;
	
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
