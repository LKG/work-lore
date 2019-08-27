package im.heart.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

@Entity()
@Table(name = "dic_frame_log_operate")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameLogOperate implements AbstractEntity<BigInteger> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7882801623296987581L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	
	@NotBlank
	@Size(min = 5, max = 32)
	@Max(32)
	@Column(length = 32, name = "USER_NAME", nullable = false,updatable = false)
	private String userName;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "REQUEST_TIME" ,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	
	@Column(name = "params" ,updatable = false, nullable = false)
	private String params;
	
	@Column(name = "type" ,updatable = false, nullable = false)
	private String type;
	
	@Column(name = "content" ,updatable = false, nullable = false)
	private String content;
	
	@Column(name = "USER_AGENT" ,updatable = false, nullable = false)
    private String userAgent;
    @Column(name = "USER_HOST" ,updatable = false, nullable = false)
    private String userHost;
    @Column(name = "SYSTEM_HOST" ,updatable = false, nullable = false)
    private String systemHost;
    
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

}
