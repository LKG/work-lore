package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * 用户机构关联表
 */
@Entity()
@Table(name = "dic_frame_user_org")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameUserOrg implements AbstractEntity<BigInteger> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3614248751144766845L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger relateId;
	@NotNull
	@Column(length = 20, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;

	@OneToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="ORG_ID" )
	@JSONField(serialzeFeatures={SerializerFeature.DisableCircularReferenceDetect})
	@NotFound(action= NotFoundAction.IGNORE)
	@Fetch(FetchMode.JOIN)
	private FrameOrg relateOrg;

	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "IS_DEFAULT", nullable = false,length=2)
	private Boolean isDefault=Boolean.FALSE;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

}
