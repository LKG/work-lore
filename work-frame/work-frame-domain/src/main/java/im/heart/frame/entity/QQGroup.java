package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @desc :模板类
 */
@Entity
@Table(name = "QQ_group")
@DynamicUpdate(true)
@Data
public class QQGroup implements AbstractEntity<BigInteger> {

	@Id
	@Column(name = "QQ_NUM", nullable = false,updatable = false, length=64)
	private BigInteger qqNum;
	
	@NotBlank
	@Column(name = "NAME", nullable = false, length = 128)
	private String name;

	@NotBlank
	@Column(name = "QQ_TYPE", nullable = false, length = 128)
	private String qqType;
	@NotBlank
	@Column(name = "DESC",length = 512)
	private String desc;
	@Column(name = "QQ_LEVEL", nullable = false,length=64)
	private Integer qqLevel;

	@Column(name = "QQ_TOTAL", nullable = false, length=64)
	private Integer qqTotal;

	@Column(name = "PEOPLE_TOTAL", nullable = false,length=64)
	private Integer peopleTotal;
	/** 是否发布. */
	@Column(name = "IS_PUB", nullable = false)
	private Boolean isPub=Boolean.FALSE;
	@Column(name = "KEYWORD",length = 512)
	private String keyword;

	@Column(name = "IMG_URL",length = 512)
	private String imgUrl;

	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME")
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
