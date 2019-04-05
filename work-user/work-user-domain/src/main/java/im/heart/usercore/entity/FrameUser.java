package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * 用户表
 */
@Entity()
@Table(name = "dic_frame_user")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameUser implements AbstractEntity<BigInteger> {

	public static int minAccountLength=5;
	public static int maxAccountLength=30;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658884594841390509L;
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
	@Column(length = 20, name = "USER_ID", nullable = false, unique = true, updatable = false)
	private BigInteger userId;

	/**
	 * 登录帐号
	 */
	@NotBlank
	@Size(min = 5, max = 32)
	@Length(max = 32)
	@Column(length = 32, name = "USER_NAME", nullable = false, unique = true, updatable = false)
	private String userName;
	/**
	 * 账号类型
	 */
	@Column(length = 32, name = "USER_TYPE", nullable = false, unique = true, updatable = false)
	private String userType;

	/**
	 * 用户手机号，可用于登录
	 */
	@NotBlank
	@Length(min = 6, max = 13)
	@Column(length = 32, name = "USER_PHONE", nullable = false)
	private String userPhone;
	
	@JSONField(serialize = false)
	@Length(max = 128)
	@Column(length = 128, name = "SALT_KEY", nullable = false)
	private String saltKey;
	/**
	 * 短信验证码 非数据库字段
	 */
	@Transient
	@Size(min = 5, max = 15)
	@JSONField(serialize = false)
	private String phoneCode;

	/**
	 *  昵称
	 */
	@Length(max = 128)
	@Column(length = 128, name = "NICK_NAME")
	private String nickName = "";

	/**
	 * //用户邮箱，可用于登录
	 */
	@Email
	@Length(max = 128)
	@Column(length = 128, name = "USER_EMAIL")
	private String userEmail;
	
	@NotBlank
	@Size(min = 6, max = 32)
	@JSONField(serialize = false)
	@Column(length = 128, name = "PASS_WORD", nullable = false)
	private String passWord;

	@Column(length = 32, name = "SEX", nullable = false)
	private Integer sex;

	@Column(name = "USER_LEVEL", nullable = false)
	private Integer userLevel = 1;

	@Column(name = "IS_VIP", nullable = false)
	private Boolean isVip = Boolean.FALSE;


	public  Boolean isExpiry(){
		if(Boolean.TRUE.equals(isVip)&&expiryTime!=null){
			return new DateTime(expiryTime).isAfterNow();
		}
		return Boolean.FALSE;
	}

	/**
	 * // 确认密码 非数据库字段
	 */
	@Transient
	@Size(min = 6, max = 15)
	@JSONField(serialize = false)
	private String retryPassWord;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "modify_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "EXPIRY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Future()
	private Date expiryTime;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "LAST_LOGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	@Length(max = 64)
	@Column(length = 64, name = "LAST_LOGIN_IP", nullable = false)
	private String lastLoginIP = "";
	
	@Length(max = 256)
	@Column(length = 256, name = "HEAD_IMG_URL", nullable = false)
	private String headImgUrl ;

	@Column(name = "USER_POINT", nullable = false)
	private BigInteger userPoint = BigInteger.ZERO;
	
	@Column(nullable = false, name = "STATUS",length=16)
	@Enumerated(EnumType.STRING)
	private Status status = Status.pending;

	/**
	 * // 用户审核认证状态，已审核，待审核，审核中，审核驳回
	 */
	@Column(nullable = false, name = "CHECK_STATUS" ,length=16)
	@Enumerated(EnumType.STRING)
	private CheckStatus checkStatus = CheckStatus.pending;

	/**
	 * // 真实名称
	 */
	@Length(max = 128)
	@Column(length = 128, name = "REAL_NAME")
	private String realName = "";
	/**
	 * // 渠道
	 */
	@Length(max = 128)
	@Column(length = 128, name = "USER_CHANNEL", updatable = false)
	private String userChannel = "";
	
	@Length(min = 15, max = 32)
	@Column(length = 32, name = "ID_CARD")
	@Pattern(regexp="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$")
	private String idCard;

	@OneToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="DEFAULT_ORG_ID" )//关联的表为org表，其主键是id
    @JSONField(serialzeFeatures={SerializerFeature.DisableCircularReferenceDetect})
	@NotFound(action= NotFoundAction.IGNORE)
    @Fetch(FetchMode.JOIN)
	private FrameOrg relateOrg;


	@Length(max = 256)
	@Column(length = 256, name = "REMARK")
	@JSONField(serialize = false)
	private String remark="";
	
/*	private Set<FrameUserAttr> userAttrs;*/

	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
	}



	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
	}
	/**
	 * 
	 * 获取salt
	 * @return
	 */
	@JSONField(serialize = false)
	public String getCredentialsSalt() {
		return this.userName + this.saltKey;
	}

}
