package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.usercore.enums.IdentityType;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @Desc : 认证客户端信息表，存放第三方信息
 */
@Data
@DynamicInsert()
@DynamicUpdate()
public class FrameClient implements AbstractEntity<BigInteger> {

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
	/**
	 * 客户端id
	 */
	@Column(name = "CLIENT_ID", nullable = false, updatable = false)
	private String clientId;
	/**
	 * 客户端密钥
	 */
	@Column(name = "CLIENT_SECRET", nullable = false, updatable = false)
	private String clientSecret;

	/**
	 * 授权类型
	 */
	@Column(name = "GRANT_TYPES", nullable = false)
	private String grantTypes;
	/**
	 * 回调地址
	 */
	@Column(name = "redirect_uri", nullable = false)
	private String redirectUri;

	@Column(name = "scope", nullable = false)
	private String scope;

	/**
	 * 权限
	 */
	@Column(name = "authorities", nullable = false)
	private String authorities;
	/**
	 * 令牌过期秒数
	 */
	@Column(name = "ACCESS_TOKEN_VALIDITY", nullable = false)
	private Long accessTokenValidity;


	/**
	 * 资源集合
	 */
	@Column(name = "RESOURCE_IDS", nullable = false)
	private String resourceIds;

	/**
	 * 附加说明
	 */
	@Column(name = "additionalInformation", nullable = false)
	private String additionalInformation;
	/**
	 * 自动授权
	 */
	@Column(name = "AUTO_APPROVE", nullable = false)
	private String autoApprove;


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
