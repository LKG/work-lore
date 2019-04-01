package im.heart.cms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/***
 * 
 * @author lkg
 * @Desc 广告位
 */
@Entity
@Table(name = "cms_ad_position")
@DynamicUpdate()
@DynamicInsert()
@Data
public class AdPosition implements AbstractEntity<BigInteger>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933931867900061343L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;

	/** 名称 */
	private String name;
	/** 宽度 */
	private Integer width;

	/** 高度 */
	private Integer height;

	/** 描述 */
	private String description;

	/** 模板 */
	private String template;
	private String clientType;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
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
