package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity()
@Table(name = "media_periodical_img")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalImg  implements AbstractEntity<BigInteger> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 894003370492147805L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;

	@Column(length = 32, name = "PERIODICAL_ID", nullable = false,  updatable = false)
	private BigInteger periodicalId;
	@Column(name = "PERIODICAL_CODE", nullable = false,length=64)
	private String periodicalCode;
	
	
	@Column(name = "PERIODICAL_TYPE", nullable = false,length=64)
	private String periodicalType;
	
	@Column(name = "PAGE_NUM", nullable = false,length=64)
	private Integer pageNum;

	@Column(name = "PATH_URL", nullable = false,length=256)
	private String pathUrl;
	@Column(name = "IMG_URL", nullable = false,length=256)
	private String imgUrl;

	@Column(name = "CHECK_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status checkStatus= Status.pending;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize = false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	
	@NotNull
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize = false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" )
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
