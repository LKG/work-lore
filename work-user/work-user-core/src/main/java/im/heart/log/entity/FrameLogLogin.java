package im.heart.log.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @desc : 访问日志表
 */
@Entity()
@Table(name = "dic_frame_log_login")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameLogLogin implements AbstractEntity<BigInteger> {
	public enum LogType {
		login(1, "login", "登录"), 
		logout(2, "logout", "登出"), 
		;
		public String code;
		public int intValue;
		public final String info;

		LogType(int intValue, String code, String info) {
			this.code = code;
			this.intValue = intValue;
			this.info = info;
		}
		public static LogType findByIntValue(int intValue) {
			for (LogType logType : LogType.values()) {
				if (logType.intValue == intValue) {
					return logType;
				}
			}
			return LogType.login;
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7421909296950512439L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "ID", nullable = false, unique = true, updatable = false)
	private BigInteger id;
	
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	@NotBlank
	@Size(min = 5, max = 32)
	@Length(max = 32)
	@Column(length = 32, name = "USER_NAME", nullable = false,updatable = false)
	private String userName;

	/**
	 * 日志类型，1登录，2登出
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "LOG_TYPE", nullable = false,updatable = false)
	private LogType logType;

	@Column(length = 128, name = "USER_IP_INFO",updatable = false)
	private String userIpInfo;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "USER_HOST" ,updatable = false, nullable = false)
	private String userHost;
	
	@Column(name = "USER_AGENT" ,updatable = false, nullable = false)
    private String userAgent="";
	
    @Column(name = "SYSTEM_HOST" ,updatable = false, nullable = false)
    private String systemHost;
    
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

}
