package im.heart.cms.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "cms_ad")
@DynamicUpdate()
@DynamicInsert()
@Data
@SequenceGenerator(name = "cmsAdSequenceGenerator", sequenceName = "cms_ad_sequence")
public class Ad implements AbstractEntity<BigInteger>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3747868433205203113L;
	/** 点击数缓存名称. */
	public static final String HITS_CACHE_NAME = "adHits";
	/** 点击数缓存更新间隔时间. */
	public static final int HITS_CACHE_INTERVAL = 600000;
	/**
	 * 类型
	 */
	public enum Type {
		/** 文本 */
		// text,
		/** 图片 */
		image,
		/** flash */
		flash,
		/** 视频，音频 */
		video,
	}

	/**
	 * 广告状态
	 * 
	 * @author hengchen
	 */
	public enum TypeOfAd {
		/** 未上架 */
		unactive,
		/** 正上架 */
		active,
		/** 已下架 */
		actived,
		/** 强制下架 */
		forceActiced,
	}

	/**
	 * 广告状态
	 *
	 * @author hengchen
	 */
	public enum AdState {
		UN_SUPPORT(-1, "未知类型"), UN_ACTIVE(0, "未上架"), ACTIVE(1, "正上架"), 
		ACTIVED(2, "已下架"), FORCE_ACTIVED(3, "已下架");
		public int intValue;
		private final String desc;

		private AdState(int intValue, String desc) {
			this.intValue = intValue;
			this.desc = desc;
		}

		public int getIntValue() {
			return intValue;
		}

		public void setIntValue(int intValue) {
			this.intValue = intValue;
		}

		public String getDesc() {
			return desc;
		}

		public static AdState findByIntValue(int intValue) {
			for (AdState adState : AdState.values()) {
				if (adState.intValue == intValue) {
					return adState;
				}
			}
			return UN_SUPPORT;
		}
	}

	/**
	 * 广告类型
	 * 
	 * @author wenhuiwang
	 *
	 */
	public enum ClientType {
		/** 网页端 **/
		web,
		/** 手机端 **/
		mobile
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "AD_ID", nullable = false, unique = true, updatable = false)
	private BigInteger adId;
	
	/** 标题 */
	@NotBlank
	@Column(name = "AD_TITLE", nullable = false)
	private String title;

	/** 客户端类型 */
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false,name = "CLIENT_TYPE")
	private ClientType clientType;

	/** 类型 */
	@NotNull
	@Column(name = "AD_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	/** 类型 */
	@NotNull
	@Column(name = "AD_SORT", nullable = false)
	private Integer adSort;


	/** 打开方式 */
	@NotNull
	@Column(name = "ad_target", nullable = false)
	private String adTarget;

	/** 内容 */
	@NotNull
	@Column(name = "AD_CONTENT", nullable = false)
	@Lob
	private String content;

	/** 路径 */
	@NotNull
	@Column(name = "AD_PATH", nullable = false)
	private String path;

	/** 起始日期 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "START_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	/** 结束日期 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "END_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	/** 点击数 . */
	@Column(name = "HITS", nullable = false, length=20)
	private Long hits=0L;
	/** 链接地址 */
	@Column(name = "URL", nullable = false, updatable = false)
	private String url;

	/** 广告位/关联的表为广告位表，其主键是id */
	@NotNull
	@Column(name = "POSITION_ID", nullable = false, updatable = false)
	private BigInteger positionId;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;

	/** 商品列表 */
	@Transient
	private String productList;
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
