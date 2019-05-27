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
