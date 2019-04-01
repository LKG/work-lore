package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gg
 * 期刊配置表
 */
@Entity()
@Table(name = "media_periodical_config")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalConfig implements AbstractEntity<BigInteger>{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	
	@NotNull
	@Column(name = "PERIODICAL_NAME", nullable = false,length=128)
	private String name;
	
	@Column(length = 32, name = "YEAR", nullable = false, updatable = false)
	private Integer year;

	@Column(name = "PERIODICAL_TYPE", nullable = false,length=128,updatable = false)
	private String type;
	
	@Column(name = "PERIODICAL_DESC", nullable = false,length=512)
	private String periodicalDesc;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;
	@NotNull
	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" )
	private Date modifyTime;
	
	@Column(name = "CITY_ID", nullable = false, length = 128, updatable = false)
	private String cityId;

	@Formula(value = "(select model.area_name from dic_frame_area model where model.area_code = city_id)")
	private String cityName;

	@Column(name = "CITY_IDS", nullable = false, length = 1024, updatable = false)
	private String cityIds;
	
	@Column(name = "CITY_NAMES", nullable = false, length = 1024, updatable = false)
	private String cityNames;
	
	@Formula(value = "( exists(select 1 from media_periodical_package model where model.periodical_config_id = id) )")
	private boolean hasPackage=false;
	/**
	 * //是否有上传记录
	 */
	@Formula(value = "( exists(select 1 from media_periodical model where model.periodical_config_id = id) )")
	private boolean hasUpload=false;

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
