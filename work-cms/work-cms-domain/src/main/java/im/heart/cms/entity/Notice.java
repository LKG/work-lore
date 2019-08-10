package im.heart.cms.entity;


import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;


/**
 * 
 * @作者： LKG  
 * 宣传图片信息
 */
@Entity
@Table(name = "cms_notice")
@DynamicUpdate()
@DynamicInsert()
@Data
public class Notice implements AbstractEntity<BigInteger>  {


	public enum NoticeType{
		image,music,video,unknown
	}
	public enum NoticeGroup{
		index,unknown
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "NOTICE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger noticeId;

	@NotNull
	@Length(max = 64)
	@Column(length = 64, name = "NOTICE_TITLE", nullable = false)
	private String noticeTitle ;
	
	@NotNull
	@Length(max = 10)
	@Column(length = 10, name = "NOTICE_TYPE", nullable = false)
	private String noticeType= NoticeType.image.toString() ;
	
	@Length(max = 15)
	@Column(length = 15, name = "NOTICE_GROUP")
	private String noticeGroup= NoticeGroup.index.toString() ;

	/**
	 * //活动简介
	 */
	@NotNull
	@Length(max = 128)
	@Column(length = 128, name = "NOTICE_WORD", nullable = false)
	private String noticeWord ;

	@NotNull
	@Column(length = 10, name = "NOTICE_SORT", nullable = false)
	private Integer noticeSort;
	
	
	@Length(max = 10)
	@Column(length = 10, name = "STATUS", nullable = false)
	private String status ;

	/**
	 * //活动展示宣传图片视频地址
	 */
	@NotNull
	@Length(max = 256)
	@Column(length = 256, name = "NOTICE_ADDR", nullable = false)
	private String noticeAddr ;

	/**
	 * 活动跳转路径
	 */
	@Length(max = 256)
	@URL
	@Column(length = 256, name = "NOTICE_URL")
	private String noticeUrl="" ;

	/**
	 * //活动描述信息
	 */
	@Length(max = 512)
	@Column(length = 512, name = "NOTICE_DESC")
	private String noticeDesc;

	/**
	 * 开始日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "START_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	/**
	 * 结束日期
	 */
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "END_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

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
	/**
	 * 判断是否已开始
	 *
	 * @return 是否已开始
	 */
	@Transient
	public boolean hasBegin() {
		return (getStartTime() == null) || new Date().after(getStartTime());
	}

	/**
	 * 判断是否已结束
	 *
	 * @return 是否已结束
	 */
	@Transient
	public boolean hasEnd() {
		return (getEndTime() != null) && new Date().after(getEndTime());
	}

}
