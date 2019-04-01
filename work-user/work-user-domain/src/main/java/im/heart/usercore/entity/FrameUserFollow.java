package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
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
@Table(name = "dic_frame_user_follow")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameUserFollow implements AbstractEntity<BigInteger> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013579269873331336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;

	@NotNull
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	
	@Column(length = 32, name = "TYPE", nullable = false, updatable = false)
	private String type;

	@Column(length = 32, name = "ITEM_TITLE", nullable = false, updatable = false)
	private String itemTitle;
	@Column(length = 32, name = "ITEM_IMG_URL", nullable = false, updatable = false)
	private String itemImgUrl;

	@NotNull
	@Column(length = 32, name = "RELATE_ID", nullable = false, updatable = false)
	private BigInteger relateId;

	@Column(name = "STATUS", nullable = false, length = 16)
	@Enumerated(EnumType.STRING)
	private Status status = Status.enabled;
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

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
