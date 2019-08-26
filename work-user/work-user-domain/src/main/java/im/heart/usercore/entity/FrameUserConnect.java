package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.IdentityType;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @Desc : 用户connect 信息表，存放第三方信息
 */
@Entity()
@Table(name = "dic_frame_user_connect")
@Data
@DynamicInsert()
@DynamicUpdate()
public class FrameUserConnect implements AbstractEntity<BigInteger> {
	/**
     * 关联id编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "CONNECT_ID", nullable = false, unique = true, updatable = false)
	private BigInteger connectId;
	/**
     *  用户id编号
	 */
	@Column(length = 20, name = "USER_ID", nullable = false)
	private BigInteger userId;
	@Column(length = 20, name = "ACCESS_TOKEN", nullable = false)
	private String accessToken;
	@Column(length = 20, name = "REFRESH_TOKEN", nullable = false)
	private String refreshToken;

	@Column( name = "EXPIRES_IN", nullable = false)
	private int expiresIn;

	/**
	 * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN.
	 * 本接口在scope参数为snsapi_base时不再提供unionID字段。
	 */
	@Column(length = 128, name = "UNION_ID")
	private String unionId;
	/**
	 * 认证类型，wehcat ,qq ,weibo,
	 */
	@Column(length = 128, name = "IDENTITY_TYPE")
	private IdentityType identityType;

	@Column( name = "MESSAGE")
	private String message;

	@Column(length = 128, name = "OPEN_ID")
	private String openId;
	/**
     * // 创建日期
	 */
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	/**
	 * 修改日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME")
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
