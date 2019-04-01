package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.CommonConst;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import im.heart.core.utils.FileUtilsEx;
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
 * 上传数据包列表
 */
@Entity
@Table(name = "media_periodical_package")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalPackage implements AbstractEntity<BigInteger>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	
	@Column(name = "PACKAGE_TYPE", nullable = false,length=32,updatable = false)
	private String packageType;
	
	@Column(name = "PACKAGE_VERSION", nullable = false,length=32)
	private Integer packageVersion=0;
	
	@Column(name = "PACKAGE_NAME", nullable = false,length=64)
	private String packageName;

	@Transient
	private String title;
	
	@Column(name = "PACKAGE_CODE", nullable = false,length=32,updatable = false)
	private String packageCode;
	
	@Column(length = 32, name = "PERIODICAL_CONFIG_ID", nullable = false, updatable = false)
	private BigInteger periodConfigId;

	@Column(name = "CHECK_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status checkStatus = Status.pending;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private CommonConst.FlowStatus status = CommonConst.FlowStatus.initial;


	@Column(name = "DATA_URL", nullable = false,length=256)
	private String dataUrl="";
	
	@Column(name = "zip_url", nullable = false,length=256)
	private String zipUrl="";
	/**
	 * 数据包含总记录数
	 */
	@Column(name = "DATA_TOTAL", nullable = false)
	private Integer dataTotal=0;
	/**
	 *数据包大小
	 */
	@Column(name = "DATA_SIZE", nullable = false)
	private Long dataSize=0L;
	@Transient
	private String dataSizeHuman;
	@Transient
	private String zipSizeHuman;
	/**
	 * 压缩数据包大小
	 */
	@Column(name = "ZIP_SIZE", nullable = false)
	private Long zipSize=0L;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	@NotNull
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize = false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" )
	private Date modifyTime;
	
	@Column(name = "CITY_ID", nullable = false,length=64,updatable = false)
	private String cityId;

	@Column(name = "CITY_NAME", nullable = false,length=64,updatable = false)
	private String cityName;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
    }
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
	}

	public String getDataSizeHuman() {
		return FileUtilsEx.getHumanReadableFileSize(dataSize);
	}
	public String getZipSizeHuman() {
		return FileUtilsEx.getHumanReadableFileSize(zipSize);
	}

}
