package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * 用户关系表
 */
@Entity()
@Table(name = "dic_frame_user_relate")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameUserRelate implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013579269873331336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger relateId;

	@NotNull
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	
	@Column(length = 32, name = "RELATE_TYPE", nullable = false, updatable = false)
	private String relateType;
	
	@NotNull
	@Column(length = 32, name = "RELATE_USER_ID", nullable = false, updatable = false)
	private BigInteger relateUserId;
	
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

}
