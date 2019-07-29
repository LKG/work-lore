package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
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
 * 材料上传记录表
 */
@Entity()
@Table(name = "media_periodical_log")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalLog implements AbstractEntity<BigInteger> {

	public enum PeriodicalLogType {

		upload(1, "upload", "文档上传"),
		convert(2, "convert", "文档转换pdf"),
		parser(3, "parser", "文档解析");



		public String code;
		public int value;
		public final String info;

		private PeriodicalLogType(int value, String code, String info) {
			this.code = code;
			this.value = value;
			this.info = info;
		}

		public static PeriodicalLogType findPeriodicalLogType(String code) {
			for (PeriodicalLogType periodicalType : PeriodicalLogType.values()) {
				if (periodicalType.code.equals(code)) {
					return periodicalType;
				}
			}
			return null;
		}

		public static PeriodicalLogType findPeriodicalLogType(int value) {
			for (PeriodicalLogType periodicalLogType : PeriodicalLogType.values()) {
				if (periodicalLogType.value == value) {
					return periodicalLogType;
				}
			}
			return null;
		}
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	
	@Column(length = 32, name = "USER_ID", nullable = false, unique = true, updatable = false)
	private BigInteger userId;
	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "LOG_DESC", nullable = false,length=80000)
	private String logDesc;
	@Column(name = "PERIODICAL_ID", nullable = false)
	private BigInteger periodicalId;

	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	@NotNull
	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
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
