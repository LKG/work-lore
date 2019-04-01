package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author lkg
 * 权限信息表
 */
@Entity()
@Table(name = "dic_frame_permission")
@DynamicUpdate()
@Data
public class FramePermission implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4399736672828174299L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "PERMISSION_ID", nullable = false,updatable = false)
	private BigInteger permissionId;
	
	@NotBlank
	@Length(min=3,max = 64)
	@Column(length = 64, name = "PERMISSION_CODE", nullable = false, updatable = false)
	private String permissionCode;

	@NotBlank
	@Length(min=2,max = 64)
	@Column(length = 64, name = "PERMISSION_NAME", nullable = false)
	private String permissionName;
	
	@JSONField(serialize = false)
	@NotBlank
	@Length(max = 64)
	@Column(length = 64, name = "PERMISSION_DESC", nullable = false)
	private String permissionDesc;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@JSONField(serialize = false)
	@NotBlank
	@Length(max = 16)
	@Column(length = 16, name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status=Status.enabled;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

	
}
